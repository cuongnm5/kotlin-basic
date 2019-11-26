package com.example.privatechat.callback

import java.lang.Exception

interface CompletableObserver {
    fun onCompleted()
    fun onError(ex: Exception)
}