package ru.bethel.domain.model

data class Chapter(
    val title: String, val audioURL: String, val subTitles: List<SubTitle>
)

data class SubTitle(val title: String, val startEndRange: String)