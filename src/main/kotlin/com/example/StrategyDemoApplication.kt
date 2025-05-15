package com.example

import com.example.config.SpELBeanNameGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
    nameGenerator = SpELBeanNameGenerator::class
)
class StrategyDemoApplication

fun main(args: Array<String>) {
    runApplication<StrategyDemoApplication>(*args)
}
