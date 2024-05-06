package com.example.bottomnav1.data

data class DatabaseState<T>(
    val data: List<T?> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String=String()
)

data class BulkPrepButtons(
    val buttonNames: List<String>
)