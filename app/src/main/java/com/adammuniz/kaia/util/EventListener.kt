package com.adammuniz.kaia.util

interface EventListener<in E : Event> {
    fun onEvent(event: E)
}