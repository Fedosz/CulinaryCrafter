package com.example.culinarycrafter.viewmodels.events

interface UserViewModelEvents {

    fun onShowMessage(message: String)
    fun doLogin(login: String)
    fun doLogout()

}