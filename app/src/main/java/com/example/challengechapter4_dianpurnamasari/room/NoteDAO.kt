package com.example.challengechapter4_dianpurnamasari.room

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {
    @Insert
    fun insertData(noteData: NoteData)

    @Query ("SELECT * FROM NoteData ORDER BY id DESC")
    fun getDataNote() : List<NoteData>

    @Delete
    fun deleteNote(note: NoteData)

    @Update
    fun updateNote(note: NoteData)
}