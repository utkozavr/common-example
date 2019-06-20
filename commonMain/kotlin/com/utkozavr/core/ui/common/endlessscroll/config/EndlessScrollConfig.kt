package com.utkozavr.core.ui.common.endlessscroll.config

class EndlessScrollConfig(
    val pageCurrentIndex: Int = 0,
    val pageLoadingIndex: Int = 0,
    val countItemsOnPage: Long = 180,
    val borderStart: Int = 30,
    val borderEnd: Int = 30,
    val previousDataCross: Int = 80,
    val scrollDistanceFilter: Int = 100
)