package com.yasintanriverdi.disneycharacters.di

import com.yasintanriverdi.disneycharacters.data.remote.CharactersApi
import com.yasintanriverdi.disneycharacters.data.repository.CharacterRepositoryImpl
import com.yasintanriverdi.disneycharacters.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(
        api: CharactersApi,
        @DispatcherModule.IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CharacterRepository = CharacterRepositoryImpl(api, ioDispatcher)

}