package com.example.some

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@SuppressLint("UseSwitchCompatOrMaterialCode")
class UserInfoActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var themeSwitch: Switch
    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var profileImageView: ImageView
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_user_info)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonNews: ImageButton = findViewById(R.id.buttonNews)
        buttonNews.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }

        val buttonChat: ImageButton = findViewById(R.id.buttonChat)
        buttonChat.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        nameTextView = findViewById(R.id.nameTextView)
        ageTextView = findViewById(R.id.textViewAge)
        genderTextView = findViewById(R.id.textViewGender)
        phoneTextView = findViewById(R.id.textViewNumber)

        val buttonChange = findViewById<Button>(R.id.buttonChange)
        buttonChange.setOnClickListener {
            val intent = Intent(this, UserInfoChange::class.java)
            intent.putExtra("name", nameTextView.text.toString())
            intent.putExtra("age", ageTextView.text.toString())
            intent.putExtra("gender", genderTextView.text.toString())
            intent.putExtra("phone", phoneTextView.text.toString())
            intent.putExtra("imageUri", currentImageUri)
            startActivityForResult(intent, 1)
        }
        themeSwitch = findViewById(R.id.switch1)
        themeSwitch.isChecked = isDarkMode // Устанавливаем текущее состояние

        // Слушатель на переключение Switch
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveThemePreference(isChecked) // Меняем тему
            applyTheme(isChecked)
            recreate() // Перерисовываем текущую Activity
        }
    }
    // Метод для установки темы
    private fun saveThemePreference(isDarkMode: Boolean) {
        sharedPreferences.edit().putBoolean("dark_mode", isDarkMode).apply()
    }

    private fun applyTheme(isDarkMode: Boolean) {
        val mode = if (isDarkMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            nameTextView.text = data.getStringExtra("name")
            ageTextView.text = "Ваш возраст: ${data.getStringExtra("age")}"
            genderTextView.text = "Пол: ${data.getStringExtra("gender")}"
            phoneTextView.text = "Номер телефона: ${data.getStringExtra("phone")}"
            currentImageUri = data.getParcelableExtra("imageUri")
            currentImageUri?.let { profileImageView.setImageURI(it)}
        }
    }
}