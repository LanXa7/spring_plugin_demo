package com.example.ext

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging

val Any.log: KLogger
    get() = KotlinLogging.logger(this::class.qualifiedName ?: "UnknownLogger")