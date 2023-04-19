package com.example.challengechapter4_dianpurnamasari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter4_dianpurnamasari.databinding.ActivityMainBinding
import com.example.challengechapter4_dianpurnamasari.room.NoteData
import com.example.challengechapter4_dianpurnamasari.room.NoteDatabase
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Collections

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter
    var noteDB : NoteDatabase? = null
    private lateinit var noteViewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteDB = NoteDatabase.getInstance(this)

        noteVM()

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.getAllNote()



        noteViewModel.getAllNoteObservers().observe(this, Observer {
            noteAdapter.setNoteData(it as ArrayList<NoteData>)
        })

        binding.btnAdd.setOnClickListener{
            startActivity(Intent(this,AddNoteActivity::class.java))
        }



    }

    private fun noteVM() {
        noteAdapter = NoteAdapter(ArrayList())
        binding.rvNote.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = noteAdapter
        noteAdapter.notifyDataSetChanged()
    }

    fun getAllNote(){

        GlobalScope.launch {
            var data = noteDB?.noteDao()?.getDataNote()
            runOnUiThread{
                noteAdapter = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = noteAdapter
            }
        }

    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = noteDB?.noteDao()?.getDataNote()
            runOnUiThread {
                noteAdapter = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = noteAdapter
            }
        }
    }

}