package com.ayokunle.sanwoolu.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface SanwoOluDispatcher {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}