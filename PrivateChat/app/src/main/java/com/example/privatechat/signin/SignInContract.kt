package com.example.privatechat.signin

import java.lang.Exception

class SignInContract {

    interface PresenterInterface {
        fun start();
        fun signIn(username: String, password: String)
        fun onPressSignUp()
    }

    interface ViewInterface {
        fun displayLoading()
        fun hideLoading()
        fun onSignInSuccess()
        fun onSignInError()
        fun onPressSignUp()
    }
}