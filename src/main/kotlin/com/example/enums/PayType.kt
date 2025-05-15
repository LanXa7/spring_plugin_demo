package com.example.enums

enum class PayType(
    val type: String,
    val value: String
) {
    ALI_PAY("alipay", "支付宝支付"),
    BANK_CARD_PAY("bankCard", "银行卡支付"),
    CREDIT_CARD_PAY("creditCard", "信用卡支付"),
    WECHAT_PAY("wechat", "微信支付");
}