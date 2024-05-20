package com.example.bottomnav1.data.contact1

import com.example.bottomnav1.data.DatabaseResult
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

            snapshot.children.forEach { childSnapshot ->
                contact = childSnapshot.getValue(Contact::class.java)
                contact?.id = childSnapshot.key
            }
            contact
        } catch (e: Exception) {
            // Handle errors appropriately
            null
        }
    }
     suspend fun getContacts(contactUUID: String) : Flow<DatabaseResult<List<Contact?>>> = callbackFlow {
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
    fun update(editContact: Contact, userAuthUUID: String) {
        val contactId = editContact.id.toString() //retrieved for sub folder key
        editContact.id = String() //Clear so not saved inside folder
        database.child(userAuthUUID).child(contactId).setValue(editContact)
    }

    fun delete(contact: Contact) = database.child(contact.id.toString()).removeValue()
}
