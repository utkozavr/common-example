package com.utkozavr.core.ui.common

/**
 * Interface of endless scroll observer which trigger data loading by scroll position
 */
interface EndlessScrollObserverInterface {

    /**
     *
     * @param dx Int Scroll distance by x
     * @param dy Int Scroll distance by y
     * @param firstVisibleItem Int First visible item in viewport
     * @param lastVisibleItem Int Last visible item in viewport
     */
    fun setScrollData(dx: Int, dy: Int, firstVisibleItem: Int, lastVisibleItem: Int)

    /**
     * Start attempt to load first page for empty data set
     */
    fun loadFirstPage()

    /**
     * Start attempt to load a page
     */
    fun loadPage(page: Int)

    /**
     * Load data
     *
     * @param fromPosition Long
     * @param count Long
     */
    fun load(fromPosition: Long, count: Long)


    /**
     *
     * Should be called after loading finished
     *
     * @param isSuccess Boolean If TRUE loadingPageIndex will set in currentPageIndex, if FALSE currentPageIndex will not change
     */
    fun onLoadingFinish(isSuccess: Boolean)


    /**
     *
     * Set how many items was loaded from last load.
     * If loaded less when page size when stop loading next page
     *
     * @param count Long
     */
    fun setLoadedCount(count: Long)

    /**
     * Start attempt to load next data set
     */
    fun loadNext()

    /**
     * Start attempt to load prev data set
     */
    fun loadPrev()

}