package com.denis.mlkitfacedetector.feature_main.ui_components

import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.denis.mlkitfacedetector.feature_main.camera.configureCamera
import com.denis.mlkitfacedetector.feature_main.data_class.FaceDecoration
import com.denis.mlkitfacedetector.feature_main.data_class.PreviewScaleType
import com.denis.mlkitfacedetector.feature_main.data_class.SourceInfo
import com.denis.mlkitfacedetector.feature_main.utils.calculateScale
import com.google.mlkit.vision.face.Face

@Composable
fun CameraPreview(
    cameraLens: Int,
) {
    var selectedDecoration by remember { mutableStateOf(FaceDecoration.CONTOUR) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    var sourceInfo by remember { mutableStateOf(SourceInfo(10, 10, false)) }
    var detectedFaces by remember { mutableStateOf<List<Face>>(emptyList()) }
    val previewView = remember { PreviewView(context) }
    val cameraProvider = remember(sourceInfo, cameraLens) {
        ProcessCameraProvider.getInstance(context)
            .configureCamera(
                previewView, lifecycleOwner, cameraLens, context,
                setSourceInfo = { sourceInfo = it },
                onFacesDetected = { detectedFaces = it },
            )
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        with(LocalDensity.current) {
            Box(
                modifier = Modifier
                    .size(
                        height = sourceInfo.height.toDp(),
                        width = sourceInfo.width.toDp()
                    )
                    .scale(
                        calculateScale(
                            constraints,
                            sourceInfo,
                            PreviewScaleType.CENTER_CROP
                        )
                    )
            )
            {
                CameraPreviewView(previewView)
                DecorationSelector(
                    selectedDecoration = selectedDecoration,
                    onDecorationSelected = { selectedDecoration = it }
                )
                DetectedFaces(
                    faces = detectedFaces,
                    sourceInfo = sourceInfo,
                    selectedDecoration = selectedDecoration
                )
            }
        }
    }
}