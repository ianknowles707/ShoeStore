package com.udacity.shoestore.models

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        cancelAction()

        saveAction()

        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    //Set observer to check for a change to the list of shoes, and navigate back to
    //ShoeList destination
    private fun saveAction() {
        viewModel.shoeList.observe(viewLifecycleOwner, {
                    view?.findNavController()
                ?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        })
    }

    //Link the CANCEL button directly to the navigation action (no other action required)
    private fun cancelAction() {
        binding.buttonCancel.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
    }


}