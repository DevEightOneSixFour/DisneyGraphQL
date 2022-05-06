package com.superspecialapp.disneygraphql.di

import com.superspecialapp.disneygraphql.data.api.DisneyApi
import com.superspecialapp.disneygraphql.domain.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDisneyApi() = DisneyApi

    @Singleton
    @Provides
    fun provideRepository(api: DisneyApi) = CharacterRepositoryImpl(api)
}