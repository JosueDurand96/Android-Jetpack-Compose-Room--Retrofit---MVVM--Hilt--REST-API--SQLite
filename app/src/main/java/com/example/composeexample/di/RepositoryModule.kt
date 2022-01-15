package com.example.composeexample.di

import com.example.composeexample.datasource.RestDataSource
import com.example.composeexample.model.User
import com.example.composeexample.repository.UserRepository
import com.example.composeexample.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun userRepository(repo: UserRepositoryImpl): UserRepository

}

