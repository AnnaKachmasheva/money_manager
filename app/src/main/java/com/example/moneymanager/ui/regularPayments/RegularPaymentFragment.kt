package com.example.moneymanager.ui.regularPayments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentRegularPaymentBinding

class RegularPaymentFragment : Fragment() {

    private var _binding: com.example.sp_v2.databinding.FragmentRegularPaymentBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegularPaymentBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_regular_payment, container, false)

        val nameRegularPayment = view.findViewById<TextView>(R.id.textPaymentName)
        val typeRegularPayment = view.findViewById<TextView>(R.id.textPaymentType)
        val amountRegularPayment = view.findViewById<TextView>(R.id.textPaymentAmount)
        val accountNameRegularPayment = view.findViewById<TextView>(R.id.textPaymentAccount)
        val categoryNameRegularPayment = view.findViewById<TextView>(R.id.textPaymentCategoryName)
        val categoryIconRegularPayment = view.findViewById<ImageView>(R.id.iconCategory)
        val startDateRegularPayment = view.findViewById<TextView>(R.id.textPaymentStartDate)
        val endDateRegularPayment = view.findViewById<TextView>(R.id.textPaymentEndDate)
        val frequencyRegularPayment = view.findViewById<TextView>(R.id.textPaymentFrequency)

        nameRegularPayment.text = arguments?.getString("name")
        typeRegularPayment.text = arguments?.getString("type")
        amountRegularPayment.text = arguments?.getFloat("amount").toString()
        accountNameRegularPayment.text = arguments?.getString("accountName")
        categoryNameRegularPayment.text = arguments?.getString("categoryName")
        arguments?.getInt("categoryIcon")?.let { categoryIconRegularPayment.setImageResource(it) }
        startDateRegularPayment.text = arguments?.getString("startDate")
        endDateRegularPayment.text = arguments?.getString("endDate")
        frequencyRegularPayment.text = arguments?.getString("frequency")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<View>(R.id.toolbar).ge.visibility = View.VISIBLE
//        view.findViewById<View>(R.id.delete).visibility = View.VISIBLE

    }
}