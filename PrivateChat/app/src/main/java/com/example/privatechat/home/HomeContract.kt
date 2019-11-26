package com.example.privatechat.home

import com.example.privatechat.models.Message

class HomeContract {
    interface PresenterInterface {
        fun getAllMessages()
        fun sendMessage(content: String)
        fun signOut()
    }

    interface ViewInterface {
        fun displayMessage(data: List<Message>)
        fun onSignOut()
    }
}