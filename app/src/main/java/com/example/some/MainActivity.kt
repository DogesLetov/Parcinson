package com.example.some

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var messageText: EditText
    private lateinit var sendButton: Button
    private lateinit var messagesRecyclerView: RecyclerView
    private lateinit var messageAdapter: MessageAdapter
    private val messageList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Инициализация элементов интерфейса
        messageText = findViewById(R.id.messageText)
        sendButton = findViewById(R.id.sendButton)
        messagesRecyclerView = findViewById(R.id.messageRecyclerView)

        // Настройка RecyclerView
        messageAdapter = MessageAdapter(messageList)
        messagesRecyclerView.layoutManager = LinearLayoutManager(this)
        messagesRecyclerView.adapter = messageAdapter

        // Обработка нажатия кнопки отправки сообщения
        sendButton.setOnClickListener {
            val message = messageText.text.toString().trim()
            if (message.isNotEmpty()) {
                messageList.add(message)
                messageAdapter.notifyItemInserted(messageList.size - 1)
                messagesRecyclerView.scrollToPosition(messageList.size - 1)
                messageText.text.clear()
            }
        }

        // Обработка переходов между окнами
        val buttonNews: Button = findViewById(R.id.buttonNews)
        buttonNews.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }

        val buttonUserInfo: Button = findViewById(R.id.buttonUserInfo)
        buttonUserInfo.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }

        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)

        // Отслеживание изменения Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val isKeyboardVisible = insets.isVisible(WindowInsetsCompat.Type.ime()) // Проверяем видимость клавиатуры
            linearLayout.visibility = if (isKeyboardVisible) View.GONE else View.VISIBLE // Скрываем/показываем LinearLayout
            insets
        }
    }
}

