package com.udacity.shoestore.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//The loginViewModel handles all code relating to the login screen. Since there is
//no specific login action, it will be used to simply verify the values of username and
//password are not empty

class LoginViewModel : ViewModel() {

    //Define Boolean to determine the login state (using encapsulation)
    private var _loginSuccessful = MutableLiveData<Boolean>()

    val loginSuccessful: LiveData<Boolean>
        get() = _loginSuccessful

    //Track if this is a new account or an existing account
    private var _newAccountAdded = MutableLiveData<Boolean>()

    val newAccountAdded: LiveData<Boolean>
        get() = _newAccountAdded


    //Set up variables to determine if the fields were completed or not. As these are not
    // observed by the UI controller they can be private Boolean
    private var _usernameBlank: Boolean = true

    private var _passwordBlank: Boolean = true

    init {
        //Initialize the login state to false to represent the user not being
        //logged in when the activity first starts
        //Set the username and password blank variables to be true by default
        _loginSuccessful.value = false
        _newAccountAdded.value = false
        _usernameBlank = true
        _passwordBlank = true

    }

    fun checkLoginDetails(username: String, password: String) {
        if (username != "") {
            _usernameBlank = false
        }

        if (password != "") {
            _passwordBlank = false
        }
        //Login will be successful if both username and password are completed
        _loginSuccessful.value = !(_passwordBlank || _usernameBlank)

    }

    fun addNewAccount() {
        //Perform actions here to create a new account if this option was chosen
        //As there is no login function, this just sets the indicator to true
        _newAccountAdded.value = true
    }

}