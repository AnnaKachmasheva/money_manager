package com.example.moneymanager.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moneymanager.model.convertets.DateConverter
import com.example.moneymanager.model.enums.FrequencyRegularPayment
import com.example.moneymanager.model.enums.TransactionType
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
@Entity(tableName = "regular_payments")
@TypeConverters(DateConverter::class)
data class RegularPaymentModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "regular_payment_id") val id: Int,
    @ColumnInfo(name = "regular_payment_name") var name: String?,
    @ColumnInfo(name = "regular_payment_amount") var amount: Double,
    @ColumnInfo(name = "regular_payment_type") var type: TransactionType?,
    @Embedded var account: AccountModel?,
    @Embedded var category: CategoryModel?,
    var startDate: LocalDate?,
    var endDate: LocalDate?,
    var frequency: FrequencyRegularPayment?,
    var isActive: Boolean = true
) : Parcelable