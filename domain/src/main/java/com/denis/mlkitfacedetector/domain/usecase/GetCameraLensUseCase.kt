package com.denis.mlkitfacedetector.domain.usecase

import com.denis.mlkitfacedetector.domain.model.CameraLens
import com.denis.mlkitfacedetector.domain.repository.CameraRepository

class GetCameraLensUseCase(private val repository: CameraRepository) {
    operator fun invoke(): CameraLens {
        return repository.getCameraLens()
    }
}