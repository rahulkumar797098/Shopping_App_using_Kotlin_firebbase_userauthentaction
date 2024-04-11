package com.example.city.Model

import android.os.Parcel
import android.os.Parcelable

data class RecommendationModel(
    var title:String = "",
    var description :String = "" ,
    var picUrl :ArrayList<String> = ArrayList() ,
    var size : ArrayList<String> = ArrayList() ,
    var price :Double = 0.0 ,
    var rating :Double = 0.0 ,
    var numberInCart :Int = 0
):Parcelable{
    constructor(parcel : Parcel):this(
        parcel.readString().toString(),
        parcel.readString().toString() ,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble() ,
        parcel.readDouble()

    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
       p0.writeString(title)
       p0.writeString(description)
        p0.writeStringList(picUrl)
        p0.writeStringList(size)
        p0.writeDouble(price)
        p0.writeDouble(rating)
    }
    companion object CREATOR : Parcelable.Creator<RecommendationModel> {
        override fun createFromParcel(parcel: Parcel): RecommendationModel {
            return RecommendationModel(parcel)
        }
        override fun newArray(size: Int): Array<RecommendationModel?> {
            return arrayOfNulls(size)
        }
    }
}
