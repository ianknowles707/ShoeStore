package com.udacity.shoestore.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding


class InstructionFragment : Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instruction,
            container,
            false
        )
        showInstructions()
        navigateToNext()

        return binding.root
    }

    private fun showInstructions() {
        binding.textViewInstructions.text =getString(R.string.instructions)
        binding.textViewTitle.text=getString(R.string.title_instructions)
    }

    private fun navigateToNext() {
        binding.buttonNext.setOnClickListener {
            view?.findNavController()?.navigate(InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment())
        }
    }

}