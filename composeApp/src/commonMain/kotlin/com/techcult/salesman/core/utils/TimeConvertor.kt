package com.techcult.salesman.core.utils

fun timeFormatter(input: Int): String {
    return if (input == 60) {
        "1:00 m"
    } else if (input >= 10) {
        "00:${input} s"
    } else {
        "00:0${input} s"
    }

}