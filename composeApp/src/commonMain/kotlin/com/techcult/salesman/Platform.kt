package com.techcult.salesman

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform