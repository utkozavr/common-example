package com.utkozavr.core.domain.common

import kotlin.properties.Delegates

class DataContainer(observer: DataContainerObserver?) {

    var data: Any? by Delegates.observable<Any?>(
        null,
        {property, oldValue, newValue ->
            if (oldValue != newValue){
                observer?.onDataChange(newValue)
            }
        }
    )

    var processState: ProcessState by Delegates.observable<ProcessState>(
        ProcessState.IDLE,
        {property, oldValue, newValue ->
            if (oldValue != newValue){
                observer?.onProcessStateChange(newValue)
            }
        }
    )

    var error: Error? by Delegates.observable<Error?>(
        null,
        {property, oldValue, newValue ->
            if (oldValue != newValue){
                observer?.onError(newValue)
            }
        }
    )


}