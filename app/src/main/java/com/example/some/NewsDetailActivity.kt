package com.example.some

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val newsTitle: TextView = findViewById(R.id.newsDetailTitle)
        val newsContent: TextView = findViewById(R.id.newsDetailContent)

        // Получение данных из интента
        val title = intent.getStringExtra("newsTitle")
        val content = intent.getStringExtra("newsContent")

        newsTitle.text = title
        newsContent.text = content
    }
}