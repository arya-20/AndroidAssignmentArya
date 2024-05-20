package com.example.bottomnav1.data.auth

import android.util.Log
import com.example.bottomnav1.data.Response
import com.example.bottomnav1.data.contact1.Contact
import com.example.bottomnav1.data.contact1.ContactRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

interface AuthRepo {
    val currentUser: FirebaseUser?
    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): Response<Boolean>

    suspend fun sendEmailVerification(): Response<Boolean>

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): Response<Boolean>

    suspend fun sendPasswordResetEmail(email: String): Response<Boolean>

    fun signOut()
}

class AuthRepository(private val auth: FirebaseAuth,
    private val contactRepo: ContactRepository): AuthRepo {
    override val currentUser get() = auth.currentUser

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String): Response<Boolean> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()

            val currentUser = authResult.user

            if (currentUser != null) {
                currentUser.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(email)
                        .build()
                )
                    .await()

                val newContact = Contact(
                    email = email,
                    password = password
                )
                val userAuthUUID = currentUser.uid
                contactRepo.add(newContact, userAuthUUID)
                Log.d("new Contact", "$newContact")
            }

                Response.Success(true)
        }
        catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun sendEmailVerification(): Response<Boolean> {
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String): Response<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Response<Boolean> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun signOut() = auth.signOut()
}