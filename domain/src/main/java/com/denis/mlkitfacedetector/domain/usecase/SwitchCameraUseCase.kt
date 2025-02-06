package com.denis.mlkitfacedetector.domain.usecase

import com.denis.mlkitfacedetector.domain.model.CameraLens
import com.denis.mlkitfacedetector.domain.repository.CameraRepository

class SwitchCameraUseCase(private val repository: CameraRepository) {
    operator fun invoke(): CameraLens {
        val currentLens = repository.getCameraLens()
        return repository.switchCamera(currentLens)
    }
}