package com.example.http.resp

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String?
)
