package com.example.moneymanager.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat
import com.example.moneymanager.ui.accounts.AccountsViewModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentAccountsBinding

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}