package ru.bethel.domain.model

import android.os.Parcel
import android.os.Parcelable

data class BookHead(
    val name: String,
    val chapters: List<Chapter>,
    val bookIndex: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Chapter.CREATOR) ?: emptyList(),
        parcel.readInt() ?: 0
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(chapters)
        parcel.writeInt(bookIndex)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<BookHead> {
        override fun createFromParcel(parcel: Parcel): BookHead {
            return BookHead(parcel)
        }

        override fun newArray(size: Int): Array<BookHead?> {
            return arrayOfNulls(size)
        }
    }
}


