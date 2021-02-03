package com.ayokunle.sanwoolu.utils

fun safeInt(value: Int?) = value ?: 0
fun safeString(value: String?) = value ?: "N/A"
fun <T> safeList(value: List<T>?) = value ?: emptyList()