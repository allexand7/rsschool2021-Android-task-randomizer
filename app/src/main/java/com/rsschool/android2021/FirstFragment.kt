package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import java.lang.NumberFormatException

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var listener: IListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as IListener
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        val min = view.findViewById<EditText>(R.id.min_value)
        val max = view.findViewById<EditText>(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"




        generateButton?.setOnClickListener {
            val maxInt = Int.MAX_VALUE
            try {
                val minResValue = min.text.toString().toInt()
                val maxResValue = max.text.toString().toInt()
                Log.d("FirstFragment", "$minResValue and $maxResValue")
                if (maxResValue > minResValue) {
                    listener?.onActionClickForSecondFragment(minResValue, maxResValue)
                } else {
                    showToast(R.string.toast_error_incorrect_value)
                }
            } catch (e: Exception) {
                showToast( R.string.toast_error_fields)
                Log.d("TAG", "$e")
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    private fun showToast(@StringRes stringRes: Int) {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show()
    }

    interface IActionPerformedListener {
        fun onActionPerformed(min: Int, max: Int)
    }

//    fun checkEmpty(minValue: String, maxValue: String): Boolean {
//        if (minValue.isEmpty() || maxValue.isEmpty()) {
//            showToast(R.string.toast_error_incorrect_value)
//            return false
//        }
//        return true
//    }
}