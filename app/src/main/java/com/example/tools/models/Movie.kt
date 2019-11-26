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
//    @JsonProperty("rating")
    var rating: Float? = null,
//    @JsonProperty("synopsis")
    var synopsis: String? = null,
//    @JsonProperty("title")
    var title: String? = null,
//    @JsonProperty("year")
    var year: Int? = 0
) : Parcelable