package com.utkozavr.core.di.data

import com.utkozavr.core.di.data.module.remoteDataSourceModule
import com.utkozavr.core.di.data.module.remoteProviderModule


import org.kodein.di.Kodein

val dataKodein = Kodein.lazy{
    importOnce(remoteProviderModule)
    importOnce(remoteDataSourceModule)
}