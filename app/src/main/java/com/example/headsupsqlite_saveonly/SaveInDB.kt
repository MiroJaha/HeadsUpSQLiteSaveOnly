package com.example.headsupsqlite_saveonly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.muddz.styleabletoast.StyleableToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaveInDB : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var nameEntry: EditText
    private lateinit var taboo1Entry: EditText
    private lateinit var taboo2Entry: EditText
    private lateinit var taboo3Entry: EditText
    private lateinit var saveButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.save_in_db)

        dbHelper= DBHelper(this)
        nameEntry = findViewById(R.id.nameEntry)
        taboo1Entry = findViewById(R.id.taboo1Entry)
        taboo2Entry = findViewById(R.id.taboo2Entry)
        taboo3Entry = findViewById(R.id.taboo3Entry)
        saveButton = findViewById(R.id.saveButton)
        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        saveButton.setOnClickListener {
            if (checkEntry()) {
                addNewCelebrity()
            }
            else
                StyleableToast.makeText(this,"Please Enter Valid Values!!",R.style.mytoast).show()
            clear()
        }
    }

    private fun checkEntry(): Boolean {
        return when {
            nameEntry.text.isBlank() -> false
            taboo1Entry.text.isBlank() -> false
            taboo2Entry.text.isBlank() -> false
            else -> taboo3Entry.text.isNotBlank()
        }
    }

    private fun addNewCelebrity() {
        val check = dbHelper.saveNotes(
            nameEntry.text.toString(),
            taboo1Entry.text.toString(),
            taboo2Entry.text.toString(),
            taboo3Entry.text.toString()
        )
        val wrongCode: Long = -1
        if (check != wrongCode) {
            StyleableToast.makeText(this, "Saved Successfully!!\n$check", R.style.mytoast)
                .show()
        } else
            StyleableToast.makeText(this, "Something Went Wrong!!\n$check", R.style.mytoast).show()
    }

    private fun clear() {
        nameEntry.text.clear()
        taboo1Entry.text.clear()
        taboo2Entry.text.clear()
        taboo3Entry.text.clear()
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}