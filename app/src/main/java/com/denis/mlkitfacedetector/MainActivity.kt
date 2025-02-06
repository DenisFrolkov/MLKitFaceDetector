package com.denis.mlkitfacedetector

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import com.denis.mlkitfacedetector.feature_main.camera.permissionGranted
import com.denis.mlkitfacedetector.feature_main.camera.requestPermission
import com.denis.mlkitfacedetector.feature_main.screen.CameraScreen
import com.denis.mlkitfacedetector.feature_main.utils.PermissionHandler
import com.denis.mlkitfacedetector.ui.theme.MLKitFaceDetectorTheme
import com.denis.mlkitfacedetector.feature_main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        setContent {
            MLKitFaceDetectorTheme {
                CameraScreen(mainViewModel = mainViewModel)
            }
        }
    }
}
