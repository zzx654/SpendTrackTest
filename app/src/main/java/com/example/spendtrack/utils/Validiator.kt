package com.example.spendtrack

object Validator {
    fun validateInput(amount: Int, desc: String): Boolean {
        return !(amount <=0 || desc.isEmpty())
    }
}