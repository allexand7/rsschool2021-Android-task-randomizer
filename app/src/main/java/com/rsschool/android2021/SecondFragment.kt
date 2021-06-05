package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    private var previousRes: Int? = 0
    private var listener: IonActionPerformedSecondFragment? = null

    private var backButton: Button? = null
    private var result: TextView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as IonActionPerformedSecondFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        var resValue = generate(min,max).toString()
        result?.text = resValue

        backButton?.setOnClickListener {
            // TODO: implement back
            previousRes = result?.text.toString().toInt()
            listener?.onActionPerformedSecondFragment(previousRes!!)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            listener?.onActionPerformedSecondFragment(previousRes!!)
        }
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
//        if (min == max || min > max || min != 0 || max != 0) {
//            return 0
//        } else {
            return (min..max).random()
//        }

    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args
            // TODO: implement adding arguments

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }

//    override fun onBackPressed(): Int {
//        TODO("Not yet implemented")
//        return result?.text.toString().toInt()
//    }

    interface IonActionPerformedSecondFragment {
        fun onActionPerformedSecondFragment(resValue: Int)
    }
}