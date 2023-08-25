package com.example.myapplication.model

data class ItemModel(
    val id: String,
    val image: String,
    val hits: Int,
    val alias: String,
    val title: String,
    val lastChapterDate: Long
)
