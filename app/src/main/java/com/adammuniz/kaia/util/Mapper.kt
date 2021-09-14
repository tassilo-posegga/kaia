package com.adammuniz.kaia.util

interface Mapper<in I, out O> : ((I) -> O) {
    override fun invoke (input: I): O
}