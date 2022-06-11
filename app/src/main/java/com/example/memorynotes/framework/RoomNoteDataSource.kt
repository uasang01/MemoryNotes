package com.example.memorynotes.framework

import android.content.Context
import com.example.memorynotes.framework.db.DatabaseService
import com.example.memorynotes.framework.db.NoteEntity
import com.memorynotes.core.data.Note
import com.memorynotes.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context): NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()
    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))
    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()
    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }
    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}