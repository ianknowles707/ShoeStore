package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    //Create the ViewModel object for the LoginViewModel
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        //Get the ViewModel from the ViewModelProvider
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel

        //Call the functions to set up actions for button clicks
        loginAction()
        createNewAccount()

        return binding.root
    }


    //Function handles the response to clicking the login button, including the call
    //to the viewModel to check the login details
    private fun loginAction() {
        //Set listener on the login button
        binding.buttonLogin.setOnClickListener {
            //Call the viewModel to check if the login was OK, pass in values entered by user
            loginCheck()
            //If username and password not empty, navigate to welcome screen
            if (viewModel.loginSuccessful.value == true) {
                actionNavigation()
            } else {
                //Tell user the fields cannot be empty
                incompleteDataWarn()
            }
        }
    }

    //Function handles the response to clicking the create new account button, including the call
    //to the viewModel to check the login details
    private fun createNewAccount() {
        //Set listener on the login button
        binding.buttonCreateNew.setOnClickListener {
            //Call the viewModel to check if the login was OK, pass in values entered by user
            loginCheck()
            //If username and password not empty, navigate to welcome screen
            if (viewModel.loginSuccessful.value == true) {
                //Call viewModel to action the account creation logic
                viewModel.addNewAccount()
                actionNavigation()
            } else {
                incompleteDataWarn()
            }
        }
    }

    //Call the viewModel function to check is login is OK
    private fun loginCheck() {
        viewModel.checkLoginDetails(
            binding.editTextUsername.text.toString(),
            binding.editTextPassword.text.toString()
        )
    }

    //Generate a Toast if login data is incomplete
    private fun incompleteDataWarn() {
        //Tell user the fields cannot be empty
        Toast.makeText(
            context,
            "Username and password cannot be blank",
            Toast.LENGTH_SHORT
        ).show()
    }

    //Perform the navigation
    fun actionNavigation() {
        view?.findNavController()
            ?.navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                    viewModel.newAccountAdded.value!!,
                    binding.editTextUsername.text.toString()
                )
            )

    }
}
