package com.example.tools.models


import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Movie(

    @JsonIgnore
    var movieId: String? = null,
    override var rating: Float? = null,
    override var synopsis: String? = null,
    override var title: String? = null,
    override var year: Int? = null
) : Parcelable, BaseModel