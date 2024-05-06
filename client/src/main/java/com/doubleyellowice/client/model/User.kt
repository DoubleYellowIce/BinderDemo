package com.example.binderdemo.model

import android.os.Parcel
import android.os.Parcelable

data class User(var name: String?, var address: String?) : Parcelable {
    constructor(parcel: Parcel) : this(null, null) {
        readFromParcel(parcel)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name ?: "")
        dest.writeString(address ?: "")
    }

    fun readFromParcel(parcel: Parcel) {
        name = parcel.readString()
        address = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
