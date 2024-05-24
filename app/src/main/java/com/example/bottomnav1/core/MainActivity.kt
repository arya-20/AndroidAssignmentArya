package com.example.bottomnav1.core

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.bottomnav1.presentation.navigation.NavigationGraph
import com.example.bottomnav1.presentation.theme.BottomNav1Theme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.properties.Delegates

class MainActivity : ComponentActivity() {


    private lateinit var databaseReference: DatabaseReference
    private var connected by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNav1Theme {
                Surface( // A surface container using the 'background' color from the theme
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavigationGraph()
                }
            }
        }
        databaseReference = FirebaseDatabase.getInstance().reference

        val connectionListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                connected = snapshot.getValue(Boolean::class.java) ?: false
                if (connected) {
                    Log.d("Firebase", "Connected to Firebase Realtime Database")
                } else {
                    Log.d("Firebase", "Disconnected from Firebase Realtime Database")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database error: ${error.message}")
            }
        }
        databaseReference.child(".info/connected").addValueEventListener(connectionListener)
    }
}