package com.udacity.shoestore.models

//Simplified Shoe Class containing only what is needed for this project. Size is defined as
//a String but can easily be converted to int or double if needed - since the EditText
//fields need String it is simpler to hold it as a String
data class Shoe(
    var name: String,
    var size: String,
    var company: String,
    var description: String
)