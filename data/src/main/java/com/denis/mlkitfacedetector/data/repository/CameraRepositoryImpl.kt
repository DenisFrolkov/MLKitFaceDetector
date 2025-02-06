package com.denis.mlkitfacedetector.data.repository

import com.denis.mlkitfacedetector.domain.model.CameraLens
import com.denis.mlkitfacedetector.domain.repository.CameraRepository
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor() : CameraRepository {

    private var currentLens = CameraLens.FRONT

    override fun getCameraLens(): CameraLens {
        return currentLens
    }

    override fun switchCamera(lens: CameraLens): CameraLens {
        currentLens = if (lens == CameraLens.FRONT) CameraLens.BACK else CameraLens.FRONT
        return currentLens
    }
}