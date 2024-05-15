package com.example.bottomnav1.data.contact1

import com.example.bottomnav1.data.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface ContactRepo{
    fun delete(contact: Contact): Task<Void>

    fun add(contact: Contact, contactUUID: String)

    suspend fun getContactForUser(userId: String): Contact?

    fun edit(contact: Contact, contactUUID: String)

    suspend fun getAll(contactUUID: String): Flow<DatabaseResult<List<Contact?>>>
}

class ContactRepository(private val contactDAO: ContactDAO) : ContactRepo {
    override suspend fun getContactForUser(userId: String): Contact? {
        return contactDAO.getContactForUser(userId)
    }
    override fun delete(contact: Contact) = contactDAO.delete(contact)

    override fun add(contact: Contact, contactUUID: String) { contactDAO.insert(contact, contactUUID)}


    override fun edit(contact: Contact, contactUUID: String) { contactDAO.update(contact, contactUUID)}

    override suspend fun getAll(contactUUID: String): Flow<DatabaseResult<List<Contact?>>> {
        return contactDAO.getContacts(contactUUID)
    }
}