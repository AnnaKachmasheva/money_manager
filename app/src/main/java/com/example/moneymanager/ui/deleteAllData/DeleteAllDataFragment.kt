package com.example.moneymanager.ui.deleteAllData

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moneymanager.data.AppDatabase
import com.example.sp_v2.databinding.FragmentDeleteAllDataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteAllDataFragment : Fragment() {

    private var _binding: FragmentDeleteAllDataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteAllDataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.deleteAllDataButton.setOnClickListener {
            openDialog()
        }

        return root
    }

    private fun openDialog() {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Delete data?")
        alertDialogBuilder.setPositiveButton(
            "Yes"
        ) { dialog, which ->
            emptyDatabase()
            dialog.cancel()

            Toast.makeText(requireContext(), "Data successfully deleted!", Toast.LENGTH_LONG)
                .show()
        }
        alertDialogBuilder.setNegativeButton(
            "No"
        ) { dialog, which ->
            dialog.cancel()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun Fragment.emptyDatabase() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) {
                AppDatabase.getDatabase(requireActivity()).clearAllTables()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}