package com.example.privatechat.home

import com.example.privatechat.callback.CompletableObserver
import com.example.privatechat.services.MessageApiService
import com.example.privatechat.callback.SingleObserver
import com.example.privatechat.models.Message
import com.example.privatechat.signin.SignInContract
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class HomePresenter(private val viewInterface: HomeContract.ViewInterface) : HomeContract.PresenterInterface {
    override fun signOut() {
        FirebaseAuth.getInstance().signOut()
        viewInterface.onSignOut()
    }

    val list = mutableListOf<Message>()

    override fun sendMessage(content: String) {
        val message = Message(content, FirebaseAuth.getInstance().currentUser?.uid.toString())
        list.add(message)
        MessageApiService.sendMessage(list, observer = object : CompletableObserver {
            override fun onCompleted() {
                viewInterface.displayMessage(list)
            }

            override fun onError(ex: Exception) {

            }

        })
    }

    override fun getAllMessages() {
        MessageApiService.getAllMessages(observer = object : SingleObserver<List<Message>>{
            override fun onCompleted(result: List<Message>) {
                list.clear()
                list.addAll(result)
                viewInterface.displayMessage(result)
            }

            override fun onError(e: Exception) {

            }

        })
    }

}