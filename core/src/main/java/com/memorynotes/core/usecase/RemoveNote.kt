package com.memorynotes.core.usecase

import com.memorynotes.core.data.Note
import com.memorynotes.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}