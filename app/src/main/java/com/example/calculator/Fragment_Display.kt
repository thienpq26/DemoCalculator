package com.example.calculator

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_display.*
import kotlinx.android.synthetic.main.fragment_display.view.*
import kotlinx.android.synthetic.main.fragment_keyboard.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class Fragment_Display : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sharedPreferences =
            activity!!.getSharedPreferences("data_result", Context.MODE_PRIVATE)
        var result = sharedPreferences.getString("result", null)
        if (result != null) {
            txtOne.text = result
        }
    }

    fun setTextDisplay(message: String, type: Boolean) {
        if (!type) {
            if (message.compareTo("=") == 0) {
                if (txtOne.text.isEmpty() || txtTwo.text.isEmpty()) {
                    if (!txtOne.text.toString().contains("sin") && !txtOne.text.toString().contains(
                            "cos"
                        )
                    ) {
                        Toast.makeText(context, "Input number, please!!", Toast.LENGTH_SHORT).show()
                    } else if (!txtOne.text.toString().contains(")")) {
                        Toast.makeText(context, "Input number, please!!", Toast.LENGTH_SHORT).show()
                    } else {
                        txtTwo.text = "0+"
                        calculating()
                        txtThree.text = ""
                    }
                } else if (txtOne.text.toString().compareTo("%") != 0) {
                    calculating()
                } else {
                    txtThree.text = ""
                    var x = txtTwo.text.toString().toDouble()
                    txtTwo.text = txtTwo.text.toString() + "%"
                    txtOne.text = "= " + (x / 100)
                }
            } else if (message.compareTo("AC") == 0) {
                txtOne.text = ""
                txtTwo.text = ""
                txtThree.text = ""
            } else if (message.compareTo("+/-") == 0) {
                txtOne.text = txtOne.text.toString().substring(0, txtOne.text.length - 1)
            } else if (message.compareTo(".") == 0) {
                if (txtOne.text.isEmpty()) {
                    Toast.makeText(context, "Error number!", Toast.LENGTH_SHORT).show()
                } else {
                    txtOne.append(message)
                }
            } else if (txtOne.text.toString().contains("(")) {
                txtOne.append(message)
            } else if (message.compareTo(")") == 0) {
                txtOne.append(message)
            } else if (txtOne.text.toString().compareTo("/") == 0 || txtOne.text.toString().compareTo(
                    "*"
                ) == 0 || txtOne.text.toString().compareTo("-") == 0 || txtOne.text.toString().compareTo(
                    "+"
                ) == 0
            ) {
                txtOne.append(message)
            } else {
                txtThree.text = ""
                if (txtOne.text.toString().contains("=")) {
                    txtTwo.text =
                        txtOne.text.substring(txtOne.text.indexOf("=") + 1, txtOne.text.length)
                } else {
                    txtTwo.text = txtOne.text.toString()
                }
                txtOne.text = message
            }
        } else {
            txtOne.append(message)
        }
    }

    fun calculating() {
        try {
            txtThree.text = txtTwo.text.toString()
            txtTwo.text = txtOne.text.toString()
            val expression =
                ExpressionBuilder(txtThree.text.toString() + txtTwo.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) txtOne.text = "= " + longResult.toString()
            else txtOne.text = "= " + result.toString()
        } catch (e: Exception) {
            Log.d("d", "exceptionMessage: " + e.message)
        }
    }

    fun setAC() {
        txtOne.text = ""
        txtTwo.text = ""
        txtThree.text = ""
    }

    fun saveResult() {
        var result = txtOne.text.toString().substring(2, txtOne.text.length)
        val sharedPreferences =
            activity!!.getSharedPreferences("data_result", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("result", result)
        editor.commit()
    }
}