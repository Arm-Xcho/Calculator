package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonFragment = ButtonViewsFragment.newInstance()
        val textFragment = TextViewsFragment.newInstance()
        buttonFragment.calcBtnClicked = textFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.btn_fragment, buttonFragment)
            .replace(R.id.tv_fragment, textFragment)
            .commit()
    }
}