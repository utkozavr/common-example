package com.utkozavr.core

expect fun <T> runTest(block: suspend () -> T)