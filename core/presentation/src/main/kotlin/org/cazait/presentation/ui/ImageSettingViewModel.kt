package org.cazait.presentation.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.cazait.presentation.model.CafeImage
import javax.inject.Inject

@HiltViewModel
class ImageSettingViewModel @Inject constructor(

) : ViewModel() {
    private val _selectedUri = MutableStateFlow<Uri?>(null)
    val selectedUri = _selectedUri.asStateFlow()
    private val cafeImages = mutableListOf<CafeImage>()

    fun updateSelectedUri(uri: Uri?) {
        _selectedUri.value = uri
    }

    fun uploadSelectedImage() {
        /** TODO() 서버에게 이미지를 등록해달라는 요청을 하는 함수
         * 1. 카페 이미지를 url로 변환하는 요청
         * 2. 변환된 url을 카페 이미지 URL 저장 api 호출로 저장
         */
    }

    fun deleteSelectedImage() {
        // 1. 카페 이미지의 id를 알아야한다.
    }
}