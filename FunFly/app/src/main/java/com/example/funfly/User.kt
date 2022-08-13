package com.example.funfly

class User {
    var name:String = ""
    var phoneNumber:String = ""
    var catPoint:String = ""
    var email:String = ""
    var password:String=""

    constructor(name:String, phoneNumber:String, catPoint:String, email:String, password:String){
        this.name = name
        this.phoneNumber = phoneNumber
        this.catPoint = catPoint
        this.email = email
        this.password=password
    }
}