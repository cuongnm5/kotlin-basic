package com.example.privatechat.signin

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class SignInPresenter (private var viewInterface: SignInContract.ViewInterface) : SignInContract.PresenterInterface {

    override fun onPressSignUp() {
        viewInterface.onPressSignUp()
    }

    override fun start() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            viewInterface.onSignInSuccess()
        }
    }

    override fun signIn(username: String, password: String) {
        viewInterface.displayLoading()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
            .addOnSuccessListener {
                viewInterface.hideLoading()
                viewInterface.onSignInSuccess()
            }.addOnFailureListener {
                Log.d("AppLog", it.message)
                viewInterface.hideLoading()
                viewInterface.onSignInError()
            }
    }

}