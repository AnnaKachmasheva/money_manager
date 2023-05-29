package com.example.moneymanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "categories")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String?,
    var color: String?,
    var icon: Int = 0
) : Parcelable