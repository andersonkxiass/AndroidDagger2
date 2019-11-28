package com.example.tools.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
@Entity(tableName = "movies")
data class Movie(

    @JsonIgnore
    @PrimaryKey
    var movieId: String = "",
    var rating: Float? = null,
    var synopsis: String? = null,
    var title: String? = null,
    var year: Int? = null
) : Parcelable