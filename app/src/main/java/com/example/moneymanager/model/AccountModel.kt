package com.example.moneymanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "accounts")
data class AccountModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String?,
    var amount: Double = 0.0,
    var isIncludeInTotalBalance: Boolean = true
) : Parcelable