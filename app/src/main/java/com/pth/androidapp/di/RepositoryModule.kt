package com.pth.androidapp.di

import com.pth.androidapp.data.repositories.AuthRepository
import com.pth.androidapp.data.repositories.CategoryRepository
import com.pth.androidapp.data.repositories.EmployerRepository
import com.pth.androidapp.data.repositories.JobRepository
import com.pth.androidapp.data.repositories.impl.AuthRepositoryImpl
import com.pth.androidapp.data.repositories.impl.CategoryRepositoryImpl
import com.pth.androidapp.data.repositories.impl.EmployerRepositoryImpl
import com.pth.androidapp.data.repositories.impl.JobRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindJobRepository(
        jobRepositoryImpl: JobRepositoryImpl
    ): JobRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindEmployerRepository(
        employerRepositoryImpl: EmployerRepositoryImpl
    ): EmployerRepository

}