package com.example.controller

import com.example.http.request.PayRequest
import com.example.service.PayService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pay")
class PayController(
    private val payService: PayService,
) {
    @PostMapping
    fun pay(@RequestBody payRequest: PayRequest): String =
        payService.pay(payRequest)
}