package com.denis.mlkitfacedetector.feature_main.ui_components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.denis.mlkitfacedetector.feature_main.data_class.FaceDecoration
import com.denis.mlkitfacedetector.feature_main.data_class.SourceInfo
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceLandmark

@Composable
fun DetectedFaces(
    faces: List<Face>,
    sourceInfo: SourceInfo,
    selectedDecoration: FaceDecoration
) {
    val density = LocalDensity.current
    val needToMirror = sourceInfo.isImageFlipped

    if (selectedDecoration.mood != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            for (face in faces) {
                val leftEye = face.getLandmark(FaceLandmark.LEFT_EYE)?.position
                val rightEye = face.getLandmark(FaceLandmark.RIGHT_EYE)?.position

                if (leftEye != null && rightEye != null) {
                    val eyeCenterX = (leftEye.x + rightEye.x) / 2
                    val eyeCenterY = (leftEye.y + rightEye.y) / 2

                    val eyeDistance = rightEye.x - leftEye.x

                    val glassesWidth =
                        eyeDistance * 5f
                    val glassesHeight = glassesWidth * 0.5f

                    val glassesLeft = eyeCenterX - (glassesWidth / 2)
                    val glassesTop =
                        eyeCenterY / selectedDecoration.mood

                    val adjustedGlassesLeft = if (needToMirror) {
                        sourceInfo.width - glassesLeft - glassesWidth
                    } else {
                        glassesLeft
                    }

                    Image(
                        painter = painterResource(id = selectedDecoration.drawableRes),
                        contentDescription = "Face Decoration",
                        modifier = Modifier
                            .graphicsLayer(
                                translationX = adjustedGlassesLeft,
                                translationY = glassesTop
                            )
                            .size(
                                with(density) { glassesWidth.toDp() },
                                with(density) { glassesHeight.toDp() })
                    )
                }
            }
        }
    } else {
        Canvas(modifier = Modifier.fillMaxSize()) {
            for (face in faces) {
                val left =
                    if (needToMirror) size.width - face.boundingBox.right.toFloat() else face.boundingBox.left.toFloat()
                drawRect(
                    Color.Gray, style = Stroke(2.dp.toPx()),
                    topLeft = Offset(left, face.boundingBox.top.toFloat()),
                    size = Size(face.boundingBox.width().toFloat(), face.boundingBox.height().toFloat())
                )
            }
        }

    }
}