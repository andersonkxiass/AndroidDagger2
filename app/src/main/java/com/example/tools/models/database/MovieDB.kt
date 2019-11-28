package com.example.tools.models.database


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tools.models.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieDB(

    @PrimaryKey
    var movieId: String,
    override var rating: Float? = 0f,
    override var synopsis: String? = "",
    override var title: String? = "",
    override var year: Int? = 0
) : Parcelable, BaseModel