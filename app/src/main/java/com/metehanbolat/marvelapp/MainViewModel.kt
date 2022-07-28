package com.metehanbolat.marvelapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehanbolat.marvelapp.data.Marvel
import com.metehanbolat.marvelapp.data.MarvelApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: MarvelApi
): ViewModel() {

    private val _state = mutableStateOf(MarvelState())
    val state: State<MarvelState> = _state

    fun getHero(hero: String) {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    marvel = api.getHero(hero),
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.e("MainViewModel", "getHero: ", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class MarvelState(
        val marvel: List<Marvel>? = null,
        val isLoading: Boolean = false
    )
}
