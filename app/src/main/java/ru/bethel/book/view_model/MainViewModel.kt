package ru.bethel.book.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.Chapter
import ru.bethel.domain.model.oldTestament

class MainViewModel() : ViewModel() {
    val currentBook = mutableStateOf<BookHead>(oldTestament[0])
    val currentChapter = mutableStateOf<Chapter>(currentBook.value.chapters[0])
}