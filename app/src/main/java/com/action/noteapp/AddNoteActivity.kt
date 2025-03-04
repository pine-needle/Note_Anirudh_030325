package com.action.noteapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.action.noteapp.Data.Note
import com.action.noteapp.viewModel.NoteViewModel
import com.action.noteapp.viewModel.NoteViewModelFactory

class AddNoteActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        // Access UI elements using findViewById
        val editTextTitle = findViewById<EditText>(R.id.editText_title)
        val editTextContent = findViewById<EditText>(R.id.editText_content)
        val buttonSave = findViewById<Button>(R.id.button_save)

        // Set up the Save button click listener
        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val content = editTextContent.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val note = Note(title = title, content = content)
                noteViewModel.insert(note)
                finish()
            } else {
                if (title.isEmpty()) {
                    editTextTitle.error = "Title is required"
                }
                if (content.isEmpty()) {
                    editTextContent.error = "Content is required"
                }
            }
        }
    }
}