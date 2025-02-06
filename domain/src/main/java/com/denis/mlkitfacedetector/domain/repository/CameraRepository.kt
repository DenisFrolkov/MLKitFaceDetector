package com.denis.mlkitfacedetector.domain.repository

import com.denis.mlkitfacedetector.domain.model.CameraLens

interface CameraRepository {
    fun getCameraLens(): CameraLens
    fun switchCamera(lens: CameraLens): CameraLens
}