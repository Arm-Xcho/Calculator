package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val tvHistory:TextView = findViewById(R.id.tv_history)

        intent.getStringExtra(TextViewsFragment.EXTRA_1)?.let {
            tvHistory.text = it
        }

    }
}