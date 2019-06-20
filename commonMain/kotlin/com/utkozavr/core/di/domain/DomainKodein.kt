package com.utkozavr.core.di.domain

import com.utkozavr.core.di.domain.module.domainCommonModule
import com.utkozavr.core.di.domain.module.entityModule
import com.utkozavr.core.di.domain.module.interactorModule
import com.utkozavr.core.di.domain.module.useCaseModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein

@UnstableDefault
@ExperimentalCoroutinesApi
val domainKodein = Kodein.lazy{
    importOnce(domainCommonModule)
    importOnce(entityModule)
    importOnce(useCaseModule)
    importOnce(interactorModule)

}