@file:Suppress("unused")

package com.exercise.matipv2.di

import com.exercise.matipv2.data.repository.MatipRepository
import com.exercise.matipv2.data.repository.OfflineMatipRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindOfflineMatipRepository(
        offlineMatipRepository: OfflineMatipRepository
    ): MatipRepository
}