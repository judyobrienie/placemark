package org.wit.placemark.models

//data class PlacemarkModel(var title: String = "", var description: String = "")

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//using id to idenify placemarks for edit purposes
@Parcelize
data class PlacemarkModel(var id: Long = 0,
                          var title: String = "",
                          var image: String = "",
                          var description: String = "") : Parcelable




