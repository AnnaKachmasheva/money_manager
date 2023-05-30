package com.example.moneymanager.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "accounts")
data class AccountModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_id") val id: Int,
    @ColumnInfo(name = "account_name") var name: String?,
    @ColumnInfo(name = "account_amount") var amount: Double = 0.0,
    var isIncludeInTotalBalance: Boolean = true
) : Parcelable {
    fun minusAmount(amount: Double) {
        this.amount -= amount
    }

    fun plusAmount(amount: Double) {
        this.amount += amount
    }
}