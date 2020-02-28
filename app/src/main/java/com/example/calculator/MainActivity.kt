package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_display.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), OnFragmentKeyboardListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun sendMessage(message: String, type: Boolean) {
        val manage = supportFragmentManager
        val fragmentDisplay = manage.findFragmentById(R.id.fragmentDisplay) as Fragment_Display
        fragmentDisplay.setTextDisplay(message, type)
    }
}
