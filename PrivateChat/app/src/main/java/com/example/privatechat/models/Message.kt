package com.example.privatechat.models

class Message {
    var content: String = ""
        get() = field
        set(value) {field = value}
    var senderId: String = ""
        get() = field
        set(value) {field = value}

    constructor()

    constructor(content: String, senderId: String) {
        this.content = content
        this.senderId = senderId
    }
}