package com.denis.mlkitfacedetector.feature_main.ml_kit

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.denis.mlkitfacedetector.feature_main.FaceDetectorProcessor
import com.denis.mlkitfacedetector.feature_main.data_class.SourceInfo
import com.google.android.gms.tasks.TaskExecutors
import com.google.mlkit.common.MlKitException
import com.google.mlkit.vision.face.Face

fun bindAnalysisUseCase(
    lens: Int,
    setSourceInfo: (SourceInfo) -> Unit,
    onFacesDetected: (List<Face>) -> Unit
): ImageAnalysis? {

    val imageProcessor = try {
        FaceDetectorProcessor()
    } catch (e: Exception) {
        Log.e("CAMERA", "Can not create image processor", e)
        return null
    }
    val builder = ImageAnalysis.Builder()
    val analysisUseCase = builder.build()

    var sourceInfoUpdated = false

    analysisUseCase.setAnalyzer(
        TaskExecutors.MAIN_THREAD,
        { imageProxy: ImageProxy ->
            if (!sourceInfoUpdated) {
                setSourceInfo(obtainSourceInfo(lens, imageProxy))
                sourceInfoUpdated = true
            }
            try {
                imageProcessor.processImageProxy(imageProxy, onFacesDetected)
            } catch (e: MlKitException) {
                Log.e(
                    "CAMERA", "Failed to process image. Error: " + e.localizedMessage
                )
            }
        }
    )
    return analysisUseCase
}