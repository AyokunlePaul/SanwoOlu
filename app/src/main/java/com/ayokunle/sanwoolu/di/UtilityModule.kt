package com.ayokunle.sanwoolu.di

import com.ayokunle.sanwoolu.data.UserRepository
import com.ayokunle.sanwoolu.data.remote.UserRepositoryImpl
import com.ayokunle.sanwoolu.utils.dispatcher.SanwoOluDispatcher
import com.ayokunle.sanwoolu.utils.dispatcher.SanwoOluDispatcherImpl
import com.ayokunle.sanwoolu.utils.image.ImageLoader
import com.ayokunle.sanwoolu.utils.image.SanwoOluCoilImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilityModule {

    @Binds
    internal abstract fun provideDispatcher(impl: SanwoOluDispatcherImpl): SanwoOluDispatcher

    @Binds
    internal abstract fun provideImageLoader(impl: SanwoOluCoilImageLoaderImpl): ImageLoader

    @Binds
    internal abstract fun provideRepository(impl: UserRepositoryImpl): UserRepository
}