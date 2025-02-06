package com.denis.mlkitfacedetector.feature_main.viewmodel

import androidx.lifecycle.ViewModel
import com.denis.mlkitfacedetector.domain.model.CameraLens
import com.denis.mlkitfacedetector.domain.usecase.SwitchCameraUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val switchCameraUseCase: SwitchCameraUseCase
) : ViewModel() {

    private val _cameraLens = MutableStateFlow(CameraLens.FRONT)
    val cameraLens: StateFlow<CameraLens> = _cameraLens.asStateFlow()

    fun switchLens() {
        _cameraLens.value = switchCameraUseCase.invoke()
    }
}