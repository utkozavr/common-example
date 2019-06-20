package com.utkozavr.core.ui.common.endlessscroll

import com.utkozavr.core.di.appKodein
import com.utkozavr.core.ui.common.EndlessScrollObserverInterface
import com.utkozavr.core.ui.common.endlessscroll.config.EndlessScrollConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.factory2

/**
 *
 * EndlessScrollLoader start loading next dataset by scroll position
 *
 * @property observer EndlessScrollObserverInterface
 * @property pageCurrentIndex Int Current page
 * @property pageLoadingIndex Int Page which loading
 * @property countItemsOnPage Int Count items in single dataset
 * @property borderStart Int Border from start of dataset for trigger loading
 * @property borderEnd Int order from end of dataset for trigger loading
 * @property previousDataCross Int Count of items which will be same between current dataset and next dataset
 * @property scrollDistanceFilter Int Sum of scroll distance will be ignored in case less of scrollDistanceFilter
 * @property previousLoadFinished Boolean Is previous loading totally finished
 * @property sumDx Int Sum of scroll distance by X
 * @property sumDy Int Sum of scroll distance by Y
 * @property itemLoadedCount Int Items count loaded for requested page
 * @constructor
 */
class EndlessScrollLoader(
    protected var observer: EndlessScrollObserverInterface,
    protected var pageCurrentIndex: Int = 0,
    protected var pageLoadingIndex: Int = 0,
    protected var countItemsOnPage: Long = 180,
    protected val borderStart: Int = 30,
    protected val borderEnd: Int = 30,
    protected val previousDataCross: Int = 80,
    protected val scrollDistanceFilter: Int = 100
) {

    protected var previousLoadFinished: Boolean = true
    protected var sumDx: Int = 0
    protected var sumDy: Int = 0
    protected var itemLoadedCount: Long = 0



    companion object: KodeinAware {

        @UnstableDefault
        @ExperimentalCoroutinesApi
        override val kodein: Kodein
            get() = appKodein

        fun getInstanceByFactory(observer: EndlessScrollObserverInterface, config: EndlessScrollConfig): EndlessScrollLoader {
            val endlessScrollFactory by factory2<EndlessScrollObserverInterface, EndlessScrollConfig, EndlessScrollLoader>()

            return endlessScrollFactory(observer, config)
        }

    }

    protected fun mathAbs(i: Int):Int {
        return if(i < 0){
            i * -1
        } else {
            i
        }
    }


    fun setScrollData(
        dx: Int,
        dy: Int,
        firstVisibleItem: Int,
        lastVisibleItem: Int
    ) {

        sumDx += dx
        sumDy += dy



        if (mathAbs(sumDx) > scrollDistanceFilter || mathAbs(sumDy) > scrollDistanceFilter) {

            val isForwardDirection = sumDx > 0 || sumDy > 0

            val nextPage = if (isForwardDirection) {
                pageCurrentIndex + 1
            } else {
                pageCurrentIndex - 1
            }

            val position = getFromPosition(nextPage, countItemsOnPage, previousDataCross)

            if (previousLoadFinished
                && nextPage > 0
                && itemLoadedCount == countItemsOnPage
                && isInBorder(isForwardDirection, lastVisibleItem, firstVisibleItem, countItemsOnPage, borderStart, borderEnd)
            ) {

                previousLoadFinished = false
                pageLoadingIndex = nextPage
                itemLoadedCount = 0

                observer.load(position, countItemsOnPage)

            }


            sumDx = 0
            sumDy = 0
        }

    }



    protected fun getFromPosition(page: Int, count: Long, previousDataCross: Int): Long {

        var position = (page - 1) * (count - previousDataCross)

        if (position < 0) {
            position = 0
        }

        return position
    }

    protected fun isInBorder(
        isForward: Boolean,
        lastVisibleItem: Int,
        firstVisibleItem: Int,
        totalItems: Long,
        borderStart: Int,
        borderEnd: Int
    ): Boolean {

        if (isForward) {
            if (lastVisibleItem > totalItems - borderEnd) {
                return true
            }
        } else {
            if (firstVisibleItem - borderStart < 0) {
                return true
            }
        }


        return false
    }


    fun loadFirstPage() {
        observer.load(0, countItemsOnPage)
    }

    fun loadPage(page: Int) {

        val position = getFromPosition(page, countItemsOnPage, previousDataCross)

        observer.load(position, countItemsOnPage)

    }

    fun onLoadingFinish(isSuccess: Boolean) {

        if (isSuccess && itemLoadedCount > 0) {
            pageCurrentIndex = pageLoadingIndex
        }

        previousLoadFinished = true


    }

    fun setLoadedCount(count: Long) {
        itemLoadedCount = count
    }


    fun loadNext(){
        loadPage(pageCurrentIndex + 1)
    }

    fun loadPrev(){

        val prevPage = if (pageCurrentIndex - 1 > 0) {
            pageCurrentIndex - 1
        } else {
            0
        }


        loadPage(prevPage)
    }

}