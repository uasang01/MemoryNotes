package com.example.memorynotes.framework

import com.memorynotes.core.usecase.*

data class UseCases(
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount,
)