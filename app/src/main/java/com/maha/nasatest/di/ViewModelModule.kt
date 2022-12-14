package com.maha.nasatest.di

import com.maha.nasatest.data.repository.RoverRepository
import com.maha.nasatest.data.repository.RoverRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ViewModelComponent::class)
@Module
interface ViewModelModule {
    @Binds
     fun bindRepository(repository: RoverRepositoryImpl): RoverRepository

}