package com.adammuniz.kaia.util

interface UseCase<in I, out O> : ((I) -> O) {
    override fun invoke(p1: I): O
}