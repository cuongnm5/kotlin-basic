package com.example.privatechat.callback

import java.lang.Exception

interface SingleObserver<T> {
    fun onCompleted(result: T)
    fun onError(e: Exception)
}