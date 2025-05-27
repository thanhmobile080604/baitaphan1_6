package com.example.baitaphan4_5

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class StudentModel(val id: Int?=-1,
                        val name: String?="",
                        val mssv: String?="",
                        val sdt: String?="",
                        val email: String?=""): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(mssv)
        parcel.writeString(sdt)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StudentModel> {
        override fun createFromParcel(parcel: Parcel): StudentModel {
            return StudentModel(parcel)
        }

        override fun newArray(size: Int): Array<StudentModel?> {
            return arrayOfNulls(size)
        }
    }

}