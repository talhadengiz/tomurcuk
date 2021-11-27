package com.talhadengiz.tomurcuk.data.model

import java.io.Serializable

data class Requirement(
    val title:String = "",
    val total:String = "",
    val location:String="",
    var status:Boolean=false
):Serializable