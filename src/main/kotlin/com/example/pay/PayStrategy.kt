package com.example.pay

import com.example.http.request.PayRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

interface PayStrategy<T, R> {
    fun handler(req: T): R
}

@Component("#{T(com.example.enums.PayType).ALI_PAY.type}")
class AliPayStrategy : PayStrategy<PayRequest, String> {
    override fun handler(req: PayRequest): String {
        val result = "支付宝支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
    companion object{
        private val log = KotlinLogging.logger {}
    }
}

@Component("#{T(com.example.enums.PayType).WECHAT_PAY.type}")
class WechatStrategy : PayStrategy<PayRequest, String> {
    override fun handler(req: PayRequest): String {
        val result = "微信支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
    companion object{
        private val log = KotlinLogging.logger {}
    }
}

@Component("#{T(com.example.enums.PayType).BANK_CARD_PAY.type}")
class BankCardStrategy : PayStrategy<PayRequest, String> {
    override fun handler(req: PayRequest): String {
        val result = "银行卡支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
    companion object{
        private val log = KotlinLogging.logger {}
    }
}

@Component("#{T(com.example.enums.PayType).CREDIT_CARD_PAY.type}")
class CreditCardStrategy : PayStrategy<PayRequest, String> {
    override fun handler(req: PayRequest): String {
        val result = "信用卡支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
    companion object{
        private val log = KotlinLogging.logger {}
    }
}

@Component("other")
class OtherPayStrategy : PayStrategy<PayRequest, String> {
    override fun handler(req: PayRequest): String {
        val result = "其它支付: " + req.orderNo
        log.info { "result: $result" }
        return result
    }
    companion object{
        private val log = KotlinLogging.logger {}
    }
}