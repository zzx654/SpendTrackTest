package com.example.spendtrack.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendtrack.AddSpendEvent
import com.example.spendtrack.Validator
import com.example.spendtrack.data.InvalidSpendException
import com.example.spendtrack.data.Spend
import com.example.spendtrack.data.SpendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class AddSpendViewModel @Inject constructor(
    private val repository: SpendsRepository
) : ViewModel() {

    private val _spendAmount = mutableStateOf("")
    val spendAmount: State<String> = _spendAmount

    private val _spendDescription = mutableStateOf("")
    val spendDescription: State<String> = _spendDescription

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    suspend fun addSpend(amount: String, description: String) {
        if(amount.isBlank())
            throw InvalidSpendException("소비량을 입력해주세요")
        if(description.isBlank())
            throw InvalidSpendException("소비에 대한 설명을 입력해주세요")
        if(!Validator.validateInput(amount.toInt(),description))
            throw InvalidSpendException("올바른 소비량을 입력해주세요")

        repository.addSpend(Spend(Date(), amount.toInt(), description))
    }

    fun onEvent(event: AddSpendEvent) {
        when(event) {
            is AddSpendEvent.EnteredAmount -> {
                _spendAmount.value = event.value
            }
            is AddSpendEvent.EnteredDescription -> {
                _spendDescription.value = event.value
            }
            is AddSpendEvent.SaveSpend -> {
                viewModelScope.launch {
                    try{
                        addSpend(
                            amount = spendAmount.value,
                            description = spendDescription.value,
                        )
                        _eventFlow.emit(UiEvent.SaveSpend)
                    } catch(e: InvalidSpendException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "저장에 실패했습니다"
                            )
                        )

                    }
                }

            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveSpend: UiEvent()
    }
}