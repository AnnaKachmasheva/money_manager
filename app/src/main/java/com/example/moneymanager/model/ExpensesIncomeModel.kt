package com.example.moneymanager.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moneymanager.model.enums.TransactionType
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
@Entity(tableName = "expenses_incomes")
class ExpensesIncomeModel(
    @PrimaryKey(autoGenerate = true) override var id: Long,
    override var type: TransactionType,
    override var amount: Double,
    override var date: LocalDate,
    override var note: String,
    @Embedded val account: AccountModel?,
    @Embedded val category: CategoryModel?
//    @Relation(entityColumn = "photo_address")
//    val photo: List<PhotoModel>? = null,
) : TransactionModel(id, type, amount, date, note), Parcelable {}