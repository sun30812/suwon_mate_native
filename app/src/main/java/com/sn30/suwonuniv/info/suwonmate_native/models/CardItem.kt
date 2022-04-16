package com.sn30.suwonuniv.info.suwonmate_native.models

data class CardItem(val iconId: Int, val title: String, val content: String)
data class SimpleCardItem(val title: String, val content: String)
data class ClassCardItem(val subjectName: String, val lectureName: String, val extraInfo: String)
data class InfoCardItem(val title: String, val content: String, val link: String)