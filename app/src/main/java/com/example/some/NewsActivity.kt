package com.example.some

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsActivity : AppCompatActivity() {
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val newsList: MutableList<NewsItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // Инициализация RecyclerView для новостей
        newsRecyclerView = findViewById(R.id.newsRecyclerView)
        newsAdapter = NewsAdapter(newsList) { newsItem ->
            val intent = Intent(this, NewsDetailActivity::class.java)
            intent.putExtra("newsTitle", newsItem.title)
            intent.putExtra("newsContent", newsItem.content)
            startActivity(intent)
        }
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.adapter = newsAdapter

        // Заполнение списка новостей
        newsList.add(NewsItem("Новое исследование", "Исследователи из Казанского Медицинского Института выявили новые факторы развития болезни Паркинсона."))
        newsList.add(NewsItem("Бета тест новой версии приложения", "Успейте записаться на бета тест новой версии приложения."))
        newsList.add(NewsItem("Новое исследование", "Исследователи из Казанского Медицинского Института выявили новые факторы развития болезни Паркинсона."))
        newsList.add(NewsItem("Бета тест новой версии приложения", "Успейте записаться на бета тест новой версии приложения."))
        newsList.add(NewsItem("Новое исследование", "Исследователи из Казанского Медицинского Института выявили новые факторы развития болезни Паркинсона."))
        newsList.add(NewsItem("Бета тест новой версии приложения", "Успейте записаться на бета тест новой версии приложения."))
        newsList.add(NewsItem("Новое исследование", "Исследователи из Казанского Медицинского Института выявили новые факторы развития болезни Паркинсона."))
        newsList.add(NewsItem("Бета тест новой версии приложения", "Успейте записаться на бета тест новой версии приложения."))
        newsAdapter.notifyDataSetChanged()

        // Обработка переходов между окнами
        val buttonNews: ImageButton = findViewById(R.id.buttonNews)
        buttonNews.setOnClickListener {
            // Остаёмся на текущем экране новостей
        }

        val buttonChat: ImageButton = findViewById(R.id.buttonChat)
        buttonChat.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonUserInfo: ImageButton = findViewById(R.id.buttonUserInfo)
        buttonUserInfo.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }
    }
}

// Класс для элемента новости
data class NewsItem(val title: String, val content: String)