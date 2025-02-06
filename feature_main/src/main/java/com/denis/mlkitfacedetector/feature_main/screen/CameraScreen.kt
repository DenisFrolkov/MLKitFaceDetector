package com.denis.mlkitfacedetector.feature_main.screen

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.denis.mlkitfacedetector.feature_main.ui_components.CameraPreview
import com.denis.mlkitfacedetector.feature_main.ui_components.CameraSwitchButton
import com.denis.mlkitfacedetector.feature_main.utils.PermissionHandler
import com.denis.mlkitfacedetector.feature_main.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CameraScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val lens by mainViewModel.cameraLens.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
            } else {
                Toast.makeText(context, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    )

    // Если разрешение не дано, запрашиваем его
    LaunchedEffect(Unit) {
        if (!PermissionHandler.hasCameraPermission(context)) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    // Рендерим UI камеры
    Scaffold() {
        CameraPreview(cameraLens = lens.ordinal)
        CameraSwitchButton(onCameraSwitch = { mainViewModel.switchLens() })
    }
}