package com.example.moneymanager.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.preference.PreferenceFragmentCompat
import com.example.moneymanager.ui.categories.CategoriesViewModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentCategoriesBinding
import com.example.sp_v2.databinding.FragmentSettingsBinding

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }


}