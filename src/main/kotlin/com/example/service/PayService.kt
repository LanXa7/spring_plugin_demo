package com.example.service

import com.example.http.request.PayRequest
import com.example.pay.PayStrategy
import org.springframework.stereotype.Service

@Service
class PayService(
    private val payStrategyMap: Map<String, PayStrategy<PayRequest, String>>
) {
    fun pay(req: PayRequest): String {
        val channel = req.payType.type
        val payStrategy = payStrategyMap[channel]
        if (payStrategy == null) {
            throw UnsupportedOperationException("暂不支持的支付方式")
        }
        return payStrategy.handler(req)
    }
}