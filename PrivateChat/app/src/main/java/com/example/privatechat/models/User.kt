package com.example.privatechat.models

class User {

    var name: String = ""
        get() = field
        set(value) {field = value}

    constructor()

    constructor(name: String) {
        this.name = name
    }
}