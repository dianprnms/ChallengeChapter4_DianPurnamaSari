package com.example.challengechapter4_dianpurnamasari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengechapter4_dianpurnamasari.databinding.ActivityAddNoteBinding
import com.example.challengechapter4_dianpurnamasari.room.NoteData
import com.example.challengechapter4_dianpurnamasari.room.NoteDatabase
//import com.example.room.databinding.ActivityAddNoteBinding
//import com.example.room.room.NoteData
//import com.example.room.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddNoteBinding
    var noteDB : NoteDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteDB = NoteDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            addData()
        }
    }

    fun addData(){
        //memanggil method insert data
        GlobalScope.async {
            var title = binding.noteTitle.text.toString()
            var content = binding.noteContent.text.toString()
            var tgl = binding.dateNote.text.toString()
            noteDB?.noteDao()?.insertData(NoteData(0,title,content,tgl))
        }
        finish()
    }
}