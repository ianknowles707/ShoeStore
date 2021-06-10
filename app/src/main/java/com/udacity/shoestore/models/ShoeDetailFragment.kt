package com.udacity.shoestore.models

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private val viewModel: ShoeListViewModel by activityViewModels()

    private lateinit var newShoe: Shoe

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
        addShoe()

        //binding.shoeListViewModel = viewModel

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun addShoe() {
        binding.buttonTest.setOnClickListener {
            val displayShoe = viewModel.shoeList.value?.elementAt(0)
            if (displayShoe != null) {
                Toast.makeText(
                    context,
                    "Name: ${displayShoe.name}" +
                            "Company: ${displayShoe.company}" +
                            "Size: ${displayShoe.size}" +
                            "Description: ${displayShoe.description}",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }

    private fun cancelAction() {
        binding.buttonCancel.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
    }


}