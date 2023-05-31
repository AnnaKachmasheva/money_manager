package com.example.moneymanager.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moneymanager.model.enums.TransactionType
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
@Entity(tableName = "transfers")
class TransferModel(
    @PrimaryKey(autoGenerate = true) override var id: Long,
    override var type: TransactionType,
    override var amount: Double,
    override var date: LocalDate,
    override var note: String,
    @Embedded(prefix = "from_") val accountFrom: AccountModel?,
    @Embedded(prefix = "to_") val accountTo: AccountModel?,
) : TransactionModel(id, type, amount, date, note), Parcelable
