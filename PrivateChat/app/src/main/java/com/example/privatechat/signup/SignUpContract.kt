package com.example.privatechat.signup

class SignUpContract {

    interface PresenterInterface {
        fun signUp(name: String, username: String, password: String)
    }

    interface ViewInterface {
        fun displayLoading()
        fun hideLoading()
        fun onSignUpSuccess()
        fun onSignUpFalse()
    }

}