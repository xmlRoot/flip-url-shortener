package com.flip.urlshorterner.common.util

import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

object DocUtils {
    fun uuid(length : Int = 32) = randomAlphanumeric(length).lowercase()
}