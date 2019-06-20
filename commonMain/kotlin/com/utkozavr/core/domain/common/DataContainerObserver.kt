package com.utkozavr.core.domain.common

interface DataContainerObserver {

    fun onDataChange(data: Any?)

    fun onProcessStateChange(processState: ProcessState)

    fun onError(error: Error?)

}