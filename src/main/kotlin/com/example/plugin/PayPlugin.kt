package com.example.plugin

import com.example.enums.PayType
import com.example.ext.log
import com.example.http.request.PayRequest
import org.springframework.core.annotation.Order
import org.springframework.plugin.core.Plugin
import org.springframework.stereotype.Component

interface PayPlugin : Plugin<PayType> {
    fun process(req: PayRequest): String
}



@Component
@Order(0)
class AliPayPlugin : PayPlugin {
    override fun supports(delimiter: PayType): Boolean {
        return delimiter == PayType.ALI_PAY
    }

    override fun process(req: PayRequest): String {
        val result = "支付宝支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
}

@Component
@Order(1)
class WechatPayPlugin : PayPlugin {
    override fun supports(delimiter: PayType): Boolean {
        return delimiter == PayType.WECHAT_PAY
    }

    override fun process(req: PayRequest): String {
        val result = "微信支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
}

@Component
@Order(2)
class BankCardPlugin : PayPlugin {
    override fun supports(delimiter: PayType): Boolean {
        return delimiter == PayType.BANK_CARD_PAY
    }

    override fun process(req: PayRequest): String {
        val result = "银行卡支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
}

@Component
@Order(3)
class CreditCardPlugin : PayPlugin {
    override fun supports(delimiter: PayType): Boolean {
        return delimiter == PayType.CREDIT_CARD_PAY
    }

    override fun process(req: PayRequest): String {
        val result = "信用卡支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
}

@Component
@Order(4)
class OtherPayPlugin : PayPlugin {
    override fun supports(delimiter: PayType): Boolean {
        return delimiter !in PayType.entries
    }

    override fun process(req: PayRequest): String {
        val result = "其它支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
}
