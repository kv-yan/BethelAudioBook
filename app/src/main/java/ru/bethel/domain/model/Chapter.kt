package ru.bethel.domain.model

import android.os.Parcel
import android.os.Parcelable

data class SubTitle(
    val title: String,
    val startPosition: Float,
    val startTime: Int,
    val startEndRange: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readFloat() ?: 0f,
        parcel.readInt() ?: 0,
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeFloat(startPosition)
        parcel.writeInt(startTime)
        parcel.writeString(startEndRange)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<SubTitle> {
        override fun createFromParcel(parcel: Parcel): SubTitle {
            return SubTitle(parcel)
        }

        override fun newArray(size: Int): Array<SubTitle?> {
            return arrayOfNulls(size)
        }
    }
}

data class Chapter(
    val title: String,
    val audioURL: String,
    val subTitles: List<SubTitle>,
    var localPath: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(SubTitle.CREATOR) ?: emptyList(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(audioURL)
        parcel.writeTypedList(subTitles)
        parcel.writeString(localPath)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Chapter> {
        override fun createFromParcel(parcel: Parcel): Chapter {
            return Chapter(parcel)
        }

        override fun newArray(size: Int): Array<Chapter?> {
            return arrayOfNulls(size)
        }
    }
}
