package com.example.spendtrack

sealed class AddSpendEvent {
    data class EnteredAmount(val value: String): AddSpendEvent()
    data class EnteredDescription(val value: String): AddSpendEvent()
    object SaveSpend: AddSpendEvent()
}
