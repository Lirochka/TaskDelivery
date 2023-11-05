package com.example.common

interface Mapper<F,T> {

    fun mapFrom(from:F):T
}