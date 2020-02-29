package com.example.calculator

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_display.*
import kotlinx.android.synthetic.main.fragment_keyboard.*
import kotlinx.android.synthetic.main.fragment_keyboard.view.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.lang.RuntimeException

class Fragment_Keyboard : Fragment() {

    var listener: OnFragmentKeyboardListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_keyboard, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //numbers
        btnNum0.setOnClickListener { listener!!.sendMessage("0", true) }
        btnNum1.setOnClickListener { listener!!.sendMessage("1", true) }
        btnNum2.setOnClickListener { listener!!.sendMessage("2", true) }
        btnNum3.setOnClickListener { listener!!.sendMessage("3", true) }
        btnNum4.setOnClickListener { listener!!.sendMessage("4", true) }
        btnNum5.setOnClickListener { listener!!.sendMessage("5", true) }
        btnNum6.setOnClickListener { listener!!.sendMessage("6", true) }
        btnNum7.setOnClickListener { listener!!.sendMessage("7", true) }
        btnNum8.setOnClickListener { listener!!.sendMessage("8", true) }
        btnNum9.setOnClickListener { listener!!.sendMessage("9", true) }

        //operators
        btnAC.setOnClickListener { listener!!.sendMessage("AC", false) }
        btnBackspace.setOnClickListener { listener!!.sendMessage("+/-", false) }
        btnPercent.setOnClickListener { listener!!.sendMessage("%", false) }
        btnDiv.setOnClickListener { listener!!.sendMessage("/", false) }
        btnMul.setOnClickListener { listener!!.sendMessage("*", false) }
        btnSub.setOnClickListener { listener!!.sendMessage("-", false) }
        btnSum.setOnClickListener { listener!!.sendMessage("+", false) }
        btnResult.setOnClickListener { listener!!.sendMessage("=", false) }
        btnDot.setOnClickListener { listener!!.sendMessage(".", false) }
        btnLeft.setOnClickListener { listener!!.sendMessage("(", false) }
        btnRight.setOnClickListener { listener!!.sendMessage(")", false) }
        btnSin.setOnClickListener { listener!!.sendMessage("sin(", false) }
        btnCos.setOnClickListener { listener!!.sendMessage("cos(", false) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentKeyboardListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentKeyboardListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
