package com.example.controller

import com.example.http.request.ArticleRequest
import com.example.service.ArticleValidationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/article")
class ArticleController(
    private val validationService: ArticleValidationService
) {
    @PostMapping
    fun publishArticle(@RequestBody request: ArticleRequest): String {
        val validationResult = validationService.validateArticle(request)
        if (!validationResult.isValid) {
            return validationResult.errorMessage!!
        }
        return "文章发布成功"
    }
}