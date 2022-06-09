package com.memorynotes.core.usecase

import com.memorynotes.core.data.Note
import com.memorynotes.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}