package com.udacity.shoestore.models

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    //Add a variable to hold the item layout for each new Shoe
    private lateinit var shoeLayout: View

    //Add the shoeListViewModel and make it accessible to both Detail and ShoeList Fragments
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        //Set menu to this Fragment
        setHasOptionsMenu(true)

        //Add ViewModel to this Fragment
        addNewShoe()

        displayShoeList()

        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    private fun displayShoeList() {

        //Set observer to check for update to teh viewModel data
        viewModel.shoeList.observe(viewLifecycleOwner, {

            Log.i("Count:", viewModel.shoeList.value!!.size.toString())
            viewModel.shoeList.value?.forEach {
                //Inflate and add new layout item for the last shoe entry

                shoeLayout =
                    LayoutInflater.from(context).inflate(
                        R.layout.shoe_item,
                        binding.layoutListShoes,
                        false
                    )
                binding.includedListItem.textViewCompany.text = it.company
                binding.includedListItem.textViewName.text = it.name
                binding.includedListItem.textViewSize.text = it.size.toString()
                binding.includedListItem.textViewDescription.text = it.description
                binding.includedListItem.textViewSizeLabel.text =
                    getString(R.string.shoe_size_label)
                //Add the new layout element
                binding.layoutListShoes.addView(shoeLayout)
            }


        })

    }

    //Check for FAB click to navigate to the detail Fragment
    private fun addNewShoe() {
        binding.fabAddShoe.setOnClickListener {

            view?.findNavController()
                ?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
    }

    //Set up the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    //Define the actions on selecting the menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}