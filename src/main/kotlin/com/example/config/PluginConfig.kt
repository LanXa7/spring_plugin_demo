package com.example.config

import com.example.enums.PayType
import com.example.enums.ValidatorType
import com.example.plugin.ArticleValidator
import com.example.plugin.PayPlugin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.plugin.core.PluginRegistry
import org.springframework.plugin.core.config.EnablePluginRegistries

@Configuration
@EnablePluginRegistries(
    PayPlugin::class,
    ArticleValidator::class
)
class PluginConfig {

    @Bean
    fun payPluginRegister(payPlugins: List<PayPlugin>): PluginRegistry<PayPlugin, PayType> =
        PluginRegistry.of(payPlugins)

    @Bean
    fun validatorRegistry(validators: List<ArticleValidator>): PluginRegistry<ArticleValidator, ValidatorType> =
        PluginRegistry.of(validators)

}