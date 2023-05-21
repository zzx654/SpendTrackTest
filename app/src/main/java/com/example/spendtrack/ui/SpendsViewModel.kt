package com.example.spendtrack.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendtrack.data.Spend
import com.example.spendtrack.data.SpendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SpendsViewModel@Inject constructor(
    private val repository: SpendsRepository
) : ViewModel() {

    private val _state = mutableStateOf(emptyList<Spend>())
    val state: State<List<Spend>> = _state

    private var getSpendsJob: Job? = null

    init {
        getSpends()
    }

    private fun getSpends() {
        getSpendsJob?.cancel()
        getSpendsJob = repository.getLast20Spends()
            .onEach { spends ->
                _state.value = spends
            }
            .launchIn(viewModelScope)
    }


}