package com.example.some

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("DEPRECATION")
class UserInfoChange : AppCompatActivity() {

    private lateinit var editImageView: ImageView
    private var selectedImageUri: Uri? = null

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_user_info_change)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val currentName = intent.getStringExtra("name")


        val editName = findViewById<EditText>(R.id.editTextTextName)
        val editAge = findViewById<EditText>(R.id.editTextTextAge)
        val editPhone = findViewById<EditText>(R.id.editTextTextNumber)
        val buttonSave = findViewById<Button>(R.id.buttonEdit)
        val genderRadioGroup: RadioGroup = findViewById(R.id.genderRadioGroup)
        val buttonChooseImage = findViewById<Button>(R.id.buttonChooseImage)

        buttonChooseImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        editName.setText(currentName)
        selectedImageUri?.let { editImageView.setImageURI(it) }


        buttonSave.setOnClickListener {
            val resultIntent = Intent()
            val selectedGenderId = genderRadioGroup.checkedRadioButtonId
            val selectedGenderText = if (selectedGenderId != -1) { // Проверяем, что что-то выбрано
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "" // Если ничего не выбрано, отправляем пустую строку или делаем обработку ошибки
            }
            resultIntent.putExtra("name", editName.text.toString())
            resultIntent.putExtra("age", editAge.text.toString())
            resultIntent.putExtra("gender", selectedGenderText)
            resultIntent.putExtra("phone", editPhone.text.toString())
            resultIntent.putExtra("imageUri", selectedImageUri)
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Закрытие текущей активности
        }
    }
    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            editImageView.setImageURI(selectedImageUri)
        }
    }
}