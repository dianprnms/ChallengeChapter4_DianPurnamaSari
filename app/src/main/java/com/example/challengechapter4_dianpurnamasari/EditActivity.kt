package com.example.challengechapter4_dianpurnamasari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challengechapter4_dianpurnamasari.databinding.ActivityEditBinding
import com.example.challengechapter4_dianpurnamasari.room.NoteData
import com.example.challengechapter4_dianpurnamasari.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    var dbNote : NoteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbNote = NoteDatabase.getInstance(this)

        //ambil data yang dikrim dari adapter
        var getDataNote = intent.getSerializableExtra("dataedit") as NoteData

        //set data yang dikirim ke dalam editText
        binding.titleEdit.setText(getDataNote.title)
        binding.contentEdit.setText(getDataNote.content)
        binding.idNote.setText(getDataNote.id.toString())

        //klik edit, data akan diedit
        binding.btnEditNote.setOnClickListener{
            editNote()
        }
    }

    fun editNote(){
        var idNote = binding.idNote.text.toString().toInt()
        var title = binding.titleEdit.text.toString()
        var note = binding.contentEdit.text.toString()
        var tgl = binding.tglEdit.text.toString()


        GlobalScope.async {
            var edit = dbNote?.noteDao()?.updateNote(NoteData(idNote, title, note, tgl))
            runOnUiThread{
                Toast.makeText(this@EditActivity, "Data Berhasil Diedit", Toast.LENGTH_LONG)
                    .show()
            }
            finish()
        }
    }
}