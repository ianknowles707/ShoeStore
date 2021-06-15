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

    //Temp variables to allocate data from EditText fields
    var shoeName: String  = ""
    var shoeCompany: String  = ""
    var shoeSize: String = ""
    var shoeDescription: String = ""


    //Add shoe function called directly from SAVE button
    fun addNewShoe(name: String, size: String, company: String, desc: String) {
        val newShoe = Shoe(name, size, company, desc)
        //Add the Shoe to the temp. list
        _shoes.add(newShoe)
        //Add the values to the MutableLiveData
        _shoeList.value = _shoes

    }


}