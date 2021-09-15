package com.yasintanriverdi.disneycharacters.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Qualifier
    annotation class IoDispatcher

    @Qualifier
    annotation class MainDispatcher

    @Qualifier
    annotation class DefaultDispatcher

    @Provides
    @Singleton
    @IoDispatcher
    internal fun provideIoDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    @MainDispatcher
    internal fun provideMainDispatcher() = Dispatchers.Main

    @Provides
    @Singleton
    @DefaultDispatcher
    internal fun provideDefaultDispatcher() = Dispatchers.Default
}