package com.example.http.request

import com.example.enums.PayType

data class PayRequest(
    val payType: PayType,
    val orderNo: String,
)