package com.example.service

import com.example.enums.ValidatorType
import com.example.http.request.ArticleRequest
import com.example.http.resp.ValidationResult
import com.example.plugin.ArticleValidator
import org.springframework.plugin.core.PluginRegistry
import org.springframework.stereotype.Service

@Service
class ArticleValidationService(
    private val validatorRegistry: PluginRegistry<ArticleValidator, ValidatorType>
) {
    fun validateArticle(request: ArticleRequest): ValidationResult {
        validatorRegistry.getPlugins()
            .forEach { validator ->
                val result = validator.validate(request)
                if (!result.isValid) {
                    return result
                }
            }
        return ValidationResult(true, null)
    }
}