package com.example.moneymanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moneymanager.model.CategoryModel.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "background_color") var color: String?,
    @ColumnInfo(name = "icon_resource") var icon: Int = 0
) {
    companion object {
        const val TABLE_NAME = "categories"
    }
}