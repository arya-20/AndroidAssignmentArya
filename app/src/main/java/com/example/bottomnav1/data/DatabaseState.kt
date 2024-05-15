package com.example.bottomnav1.data

data class DatabaseState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val errorMessage: String=String()
)
