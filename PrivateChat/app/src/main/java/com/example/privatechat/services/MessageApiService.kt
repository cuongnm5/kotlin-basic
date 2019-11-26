package com.example.privatechat.services

import com.example.privatechat.callback.CompletableObserver
import com.example.privatechat.callback.SingleObserver
import com.example.privatechat.models.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object MessageApiService {

    fun getAllMessages(observer: SingleObserver<List<Message>>) {
        FirebaseDatabase.getInstance().getReference("messages").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(e: DatabaseError) {
                observer.onError(e.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Message>()
                snapshot.children.forEach {
                    val mess = it.getValue(Message::class.java)
                    if (mess != null) {
                        list.add(mess)
                    }
                }
                observer.onCompleted(list)
            }

        })
    }

    fun sendMessage(list: List<Message>, observer: CompletableObserver) {
        FirebaseDatabase.getInstance().getReference("messages").setValue(list)
            .addOnSuccessListener {
                observer.onCompleted()
            }.addOnFailureListener {
                observer.onError(it)
            }
    }
}