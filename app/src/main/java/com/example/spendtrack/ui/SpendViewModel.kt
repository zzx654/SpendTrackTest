package com.example.spendtrack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendtrack.data.Spend
import com.example.spendtrack.data.SpendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SpendViewModel @Inject constructor(
    private val repository: SpendsRepository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFLow = _eventFlow.asSharedFlow()

    fun addSpend(amount: Int, description: String) = viewModelScope.launch {
        repository.addSpend(Spend(Date(), amount, description))
    }
    suspend fun getLast20Spends() = repository.getLast20Spends()

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }


}