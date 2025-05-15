package org.abdozcan.renderer

inline fun print(block: () -> String) {
    println(block())
}