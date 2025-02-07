package com.denis.mlkitfacedetector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.denis.mlkitfacedetector.feature_main.screen.CameraScreen
import com.denis.mlkitfacedetector.feature_main.viewmodel.MainViewModel
import com.denis.mlkitfacedetector.ui.theme.MLKitFaceDetectorTheme
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
