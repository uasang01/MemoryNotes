package com.example.memorynotes.framework

import com.memorynotes.core.usecase.AddNote
import com.memorynotes.core.usecase.GetAllNotes
import com.memorynotes.core.usecase.GetNote
import com.memorynotes.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
)