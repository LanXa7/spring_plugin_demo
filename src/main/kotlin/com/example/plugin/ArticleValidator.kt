package com.example.plugin

import com.example.enums.ValidatorType
import com.example.http.request.ArticleRequest
import com.example.http.resp.ValidationResult
import org.springframework.core.annotation.Order
import org.springframework.plugin.core.Plugin
import org.springframework.stereotype.Component

interface ArticleValidator : Plugin<ValidatorType> {
    fun validate(req: ArticleRequest): ValidationResult
}

@Component
@Order(0)
class TitleValidator : ArticleValidator {
    override fun supports(validatorType: ValidatorType): Boolean {
        return validatorType == ValidatorType.TITLE
    }

    override fun validate(req: ArticleRequest): ValidationResult {
        if (req.title.isBlank()) {
            return ValidationResult(false, "标题不能为空")
        }
        if (req.title.length > 100) {
            return ValidationResult(false, "标题不能超过100字符")
        }
        return ValidationResult(true, null)
    }
}

@Component
@Order(1)
class ContentValidator : ArticleValidator {
    private val bannedWords = listOf("唱跳", "rap", "篮球")

    override fun supports(validatorType: ValidatorType): Boolean {
        return validatorType == ValidatorType.CONTENT
    }

    override fun validate(req: ArticleRequest): ValidationResult {
        bannedWords.forEach { word ->
            if (req.content.contains(word)) {
                return ValidationResult(false, "内容包含敏感词: $word")
            }
        }
        return ValidationResult(true, null)
    }
}

@Component
@Order(2)
class TagsValidator : ArticleValidator {
    override fun supports(validatorType: ValidatorType): Boolean {
        return validatorType == ValidatorType.TAGS
    }

    override fun validate(req: ArticleRequest): ValidationResult {
        if (req.tags.isEmpty()) {
            return ValidationResult(false, "至少需要一个标签")
        }
        return ValidationResult(true, null)
    }
}