package com.sn30.suwonuniv.info.suwonmate_native.models

import java.io.Serializable

data class CardItem(val iconId: Int, val title: String, val content: String)
data class SimpleCardItem(val title: String, val content: String)
data class InfoCardItem(val title: String, val content: String, val link: String)
data class ClassDetailInfo(
    val targetGrade: String,
    val targetDepart: String,
    val targetMajor: String,
    val subjectCode: String,
    val openYear: String,
    val subjectKind: String,
    val point: String,
    val gender: String,
    val name: String,
    val workGrade: String,
    val location: String,
    val region: String,
    val language: String,
    val promise: String,
    val way: String,
): Serializable