package com.udacity.shoestore.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    //Create a binding object for use outside of onCreateView
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        //Set up listeners for the button to navigate to next screen
        navigateToNext()

        //Get the arguments passed from the LoginFragment
        val args = WelcomeFragmentArgs.fromBundle(requireArguments())

        //Call function to update the text on the screen using the information passed
        //from the login screen
        showWelcome(args.username, args.newAccount)

        return binding.root
    }

    private fun navigateToNext() {
        binding.button.setOnClickListener {
            view?.findNavController()?.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
        }
    }

    private fun showWelcome(user: String, new: Boolean) {

        if(new){
            binding.textViewWelcome.text = getString(R.string.welcome_new, user)
        } else {
            binding.textViewWelcome.text = getString(R.string.welcome_login, user)
        }
        binding.textViewContinue.text = getString(R.string.proceed_text)
    }

}