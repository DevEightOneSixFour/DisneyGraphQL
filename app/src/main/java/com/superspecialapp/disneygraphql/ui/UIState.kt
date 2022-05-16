package com.superspecialapp.disneygraphql.ui

sealed class UIState {
    object Loading: UIState()
    class Error(val error: Exception): UIState()
    class Success<T>(val data: T): UIState()
}
