package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {

    //Create a private temporary list for adding Shoe objects to
    private val _shoes: MutableList<Shoe> = mutableListOf()

    //Create the MutableLiveDate for the Shoe list
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    //Create the LiveData that will be observed by the Fragment
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    //Initialize a variable 'newShoe' to take values from the UI
    var newShoe: Shoe = Shoe("", 0.0, "", "")

    private val _lastShoe = MutableLiveData<Shoe>()

    val lastShoe: LiveData<Shoe>
        get() = _lastShoe

    init {

    }

    //Add shoe function called directly from SAVE button
    fun addNewShoe(shoe: Shoe) {
        //Add the Shoe to the temp. list
        _shoes.add(shoe)
        //Add the values to the MutableLiveData
        _shoeList.value = _shoes
        _lastShoe.value= shoeList.value?.last()
    }


}