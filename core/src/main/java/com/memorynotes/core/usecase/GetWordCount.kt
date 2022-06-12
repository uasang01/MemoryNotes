package com.memorynotes.core.usecase

import com.memorynotes.core.data.Note

class GetWordCount {
    operator fun invoke(note: Note) = getCount(note.title) + getCount(note.content)

    private fun getCount(str: String) = str.length
    // 단어 수
//        str.split(" ", "\n")
//            .filter {
//                it.contains(Regex(".*[a-zA-Z].*"))
//            }
//            .count()
}