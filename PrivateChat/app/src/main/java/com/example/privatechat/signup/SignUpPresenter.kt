package com.example.privatechat.signup

import android.util.Log
import com.example.privatechat.callback.CompletableObserver
import com.example.privatechat.services.MessageApiService
import com.example.privatechat.services.UserApiService
import java.lang.Exception

class SignUpPresenter (private val viewInterface: SignUpContract.ViewInterface) :SignUpContract.PresenterInterface {
    override fun signUp(name: String, username: String, password: String) {

        viewInterface.displayLoading()
        UserApiService.signUp(name, username, password, observer = object: CompletableObserver {
            override fun onCompleted() {
                viewInterface.hideLoading()
                viewInterface.onSignUpSuccess()
            }

            override fun onError(ex: Exception) {
                Log.d("AppLog", "Error:" + ex.message)
                viewInterface.hideLoading()
                viewInterface.onSignUpFalse()
            }

        })
    }
}