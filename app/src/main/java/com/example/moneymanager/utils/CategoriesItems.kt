package com.example.moneymanager.utils

import com.example.moneymanager.model.CategoryModel
import com.example.sp_v2.R

object CategoriesItems {

    val CategoriesItems = arrayListOf(
        CategoryModel(icon = R.drawable.ic_profile, name = "Profile"),
        CategoryModel(icon = R.drawable.ic_home, name = "Home"),
        CategoryModel(icon = R.drawable.ic_charts, name = "Charts"),
        CategoryModel(icon = R.drawable.ic_accounts, name = "Accounts"),
        CategoryModel(icon = R.drawable.ic_categories, name = "Categories"),
        CategoryModel(icon = R.drawable.ic_regular_payments, name = "Regular payments"),
        CategoryModel(icon = R.drawable.ic_settings, name = "Settings"),
        CategoryModel(icon = R.drawable.ic_privacy_policy, name = "Privacy policy"),
        CategoryModel(icon = R.drawable.ic_about_us, name = "About us")
    )

}