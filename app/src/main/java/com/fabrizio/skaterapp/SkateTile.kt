package com.fabrizio.skaterapp

import java.io.Serializable

data class SkateTile(
    val id:String = "",
    val title:String = "",
    val description:String = "",
    val descriptionLong:String = "",
    val buttonText:String = "",
    val headerImageResId:Int =0,
    val headerImageUrl:String= "",
    val skaterUrl:String= "",
    var isFavorite:Boolean= false
)