package com.example.privatechat.services

import android.util.Log
import com.example.privatechat.callback.CompletableObserver
import com.example.privatechat.callback.SingleObserver
import com.example.privatechat.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object UserApiService {
    fun getUserById(id: String, observer: SingleObserver<User>) {
        FirebaseDatabase.getInstance().getReference("users").child(id)
            .addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(err: DatabaseError) {
                    observer.onError(err.toException())
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(User::class.java);
                    if (user != null) {
                        observer.onCompleted(user)
                    }
                }

            })
    }

    fun signUp(name: String, username: String, password: String, observer: CompletableObserver) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
            .addOnSuccessListener {
                FirebaseDatabase.getInstance().getReference("users").child(it.user?.uid.toString())
                    .setValue(User(name)).addOnSuccessListener {
                        observer.onCompleted()
                    }.addOnFailureListener {
                        observer.onError(it)
                    }
            }.addOnFailureListener {
                observer.onError(it)
            }
    }
}