package com.example.config

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.annotation.AnnotationBeanNameGenerator
import org.springframework.expression.common.TemplateParserContext
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.stereotype.Component

class SpELBeanNameGenerator: AnnotationBeanNameGenerator() {

    private val parser: SpelExpressionParser = SpelExpressionParser()

    override fun generateBeanName(
        definition: BeanDefinition,
        registry: BeanDefinitionRegistry
    ): String {
        val name = (definition as AnnotatedBeanDefinition).metadata
            .getAnnotationAttributes(Component::class.java.name)
            ?.get("value").toString()
        return if (name.startsWith("#{")) {
            val templateParserContext = TemplateParserContext("#{", "}")
            val expression = parser.parseExpression(name, templateParserContext)
            expression.getValue(String::class.java).toString()
        } else {
            super.generateBeanName(definition, registry)
        }
    }
}