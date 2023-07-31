package com.shdwraze.chi.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.shdwraze.chi.ShibeApplication
import com.shdwraze.chi.data.ShibeRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ShibeUiState {
    data class Success(val photos: List<String>) : ShibeUiState

    object Error : ShibeUiState

    object Loading : ShibeUiState
}

class ShibeViewModel(
    private val shibeRepository: ShibeRepository
) : ViewModel() {

    var shibeUiState: ShibeUiState by mutableStateOf(ShibeUiState.Loading)
        private set

    init {
        getShibePhotos()
    }

    fun getShibePhotos() {
        viewModelScope.launch {
            shibeUiState = try {
                val result = shibeRepository.getShibePhotos()
                ShibeUiState.Success(shibeRepository.getShibePhotos())
            } catch (e: IOException) {
                ShibeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ShibeApplication)
                val shibeRepository = application.container.shibeRepository
                ShibeViewModel(shibeRepository = shibeRepository)
            }
        }
    }
}