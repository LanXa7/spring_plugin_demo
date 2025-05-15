package com.example.service

import com.example.enums.PayType
import com.example.http.request.PayRequest
import com.example.plugin.PayPlugin
import com.example.pay.PayStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.plugin.core.PluginRegistry
import org.springframework.stereotype.Service

@Service
class PayService(
    private val payStrategyMap: Map<String, PayStrategy<PayRequest, String>>,
    @Autowired
    private val payPluginRegistry: PluginRegistry<PayPlugin, PayType>
) {
    fun pay(req: PayRequest): String {
        val channel = req.payType.type
        val payStrategy = payStrategyMap[channel]
        if (payStrategy == null) {
            throw UnsupportedOperationException("暂不支持的支付方式")
        }
        return payStrategy.handler(req)
    }

    fun processPay(req: PayRequest): String {
        val plugin = payPluginRegistry.getPluginFor(req.payType)
            .orElseThrow {
                UnsupportedOperationException("不支持的支付类型: ${req.payType}")
            }
        return plugin.process(req)
    }
}