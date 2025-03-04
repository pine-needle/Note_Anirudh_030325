package com.action.noteapp

import android.app.Application
import com.action.noteapp.Data.NoteDatabase
import com.action.noteapp.Data.NoteRepository

class NoteApplication : Application() {

    val database by lazy { NoteDatabase.getDatabase(this) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}