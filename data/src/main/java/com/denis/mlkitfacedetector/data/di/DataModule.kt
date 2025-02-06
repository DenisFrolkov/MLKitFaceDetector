package com.denis.mlkitfacedetector.data.di

import com.denis.mlkitfacedetector.data.repository.CameraRepositoryImpl
import com.denis.mlkitfacedetector.domain.repository.CameraRepository
import com.denis.mlkitfacedetector.domain.usecase.GetCameraLensUseCase
import com.denis.mlkitfacedetector.domain.usecase.SwitchCameraUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideCameraRepository(): CameraRepository {
        return CameraRepositoryImpl()
    }

    @Provides
    fun provideSwitchCameraUseCase(repository: CameraRepository): SwitchCameraUseCase {
        return SwitchCameraUseCase(repository)
    }

    @Provides
    fun provideGetCameraLensUseCase(repository: CameraRepository): GetCameraLensUseCase {
        return GetCameraLensUseCase(repository)
    }
}