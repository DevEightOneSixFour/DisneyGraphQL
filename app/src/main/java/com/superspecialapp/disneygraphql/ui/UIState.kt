package com.superspecialapp.disneygraphql.ui

import com.superspecialapp.disneygraphql.utils.CustomException

sealed class UIState {
    object Loading: UIState()
    class Error(val error: CustomException): UIState()
    class Success<T>(val data: T): UIState()
}
