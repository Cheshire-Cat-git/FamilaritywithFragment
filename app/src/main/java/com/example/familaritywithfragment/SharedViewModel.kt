package com.example.familaritywithfragment

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SharedViewModel : ViewModel() {

    private val _color2 = MutableLiveData<Int>(Color.WHITE)
    val color2: LiveData<Int> = _color2

    private val _color3 = MutableLiveData<Int>(Color.WHITE)
    val color3: LiveData<Int> = _color3

    private val _isSwapped = MutableLiveData<Boolean>(false)
    val isSwapped: LiveData<Boolean> = _isSwapped

    fun changeColors() {
        _color2.value = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        _color3.value = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }

    fun swapFragments() {
        _isSwapped.value = !(_isSwapped.value ?: false)
    }
}