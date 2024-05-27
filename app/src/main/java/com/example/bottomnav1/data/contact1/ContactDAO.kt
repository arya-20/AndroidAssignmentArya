package com.example.bottomnav1.data.contact1

import android.content.ContentValues.TAG
import android.util.Log
import com.example.bottomnav1.data.DatabaseResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ContactDAO(private val database: DatabaseReference) {

    suspend fun getContactForUser(userId: String): Contact? {
        return try {
            val snapshot = database.child(userId).get().await()
            var contact: Contact? = null

            if (snapshot.exists() && snapshot.hasChildren()) {
                snapshot.children.forEach { childSnapshot ->
                    val contactData = childSnapshot.getValue(Contact::class.java)
                    if (contactData != null) {
                        contact = contactData
                        contact?.id = childSnapshot.key
                    }
                }
                Log.d(TAG, "Contact for user $userId retrieved: $contact")
            } else {
                Log.d(TAG, "No contact found for user $userId")
            }

            contact
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching contact for user $userId: ${e.message}", e)
            null
        }
    }

    private fun getCurrentUserAuthId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

    suspend fun getCurrentContact(): Contact? {

        val currentUserAuthId =
            getCurrentUserAuthId() // Implement this method to fetch the current user's authentication ID
        return try {
            val snapshot = database.child(currentUserAuthId!!).get()
                .await() // Fetch contact using user's authentication ID
            snapshot.getValue(Contact::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateContact(contact: Contact) {
        val currentUserAuthId = getCurrentUserAuthId()// Ensure contact ID is not null
        if (currentUserAuthId != null) {
            try {
                database.child(currentUserAuthId).setValue(contact).await()
            } catch (e: Exception) {
                // Handle update failure
            }
        } else {
        }
    }

    suspend fun getContactById(contactId: String): Contact? {
        return try {
            val snapshot =
                database.child(contactId).get().await() // Get the contact by ID
            snapshot.getValue(Contact::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getContacts(contactUUID: String): Flow<DatabaseResult<List<Contact?>>> =
        callbackFlow {
            trySend(DatabaseResult.Loading)
            database.child(contactUUID).keepSynced(true)

            val event = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val contacts = ArrayList<Contact>()
                    for (childSnapshot in snapshot.children) {
                        val contact = childSnapshot.getValue(Contact::class.java)
                        contact!!.id = childSnapshot.key.toString()
                        contacts.add(contact)
                    }
                    trySend(DatabaseResult.Success(contacts))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(DatabaseResult.Error(Throwable(error.message)))
                }
            }
            database.child(contactUUID).addValueEventListener(event)
            awaitClose { close() }
        }

    fun insert(newContact: Contact, userAuthUUID: String) {
        database.child(userAuthUUID).setValue(newContact)
    }

    suspend fun update(editContact: Contact) {
        val currentUserAuthId = getCurrentUserAuthId()
        if (currentUserAuthId != null) {
            try {
                database.child(currentUserAuthId).setValue(editContact).await()
            } catch (e: Exception) {}
        } else {
        }
    }


    fun delete(contact: Contact) = database.child(contact.id.toString()).removeValue()
}
