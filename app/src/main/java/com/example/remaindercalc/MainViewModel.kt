package com.example.remaindercalc

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// このクラスは「今の画面がどちらか」を表す目印です。
sealed class ScreenState {
    // 1. 数字を入れる画面
    object Input : ScreenState() 
    
    // 2. 結果を見る画面（計算した元の数字、割る数、商、余りを覚えておきます）
    data class Result(
        val input: Int, 
        val divisor: Int, 
        val quotient: Int, 
        val remainder: Int
    ) : ScreenState() 
}

// アプリの「頭脳」となるプログラムです
class MainViewModel : ViewModel() {
    // 現在の画面の状態（最初は「入力画面」にしておきます）
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Input)
    val screenState: StateFlow<ScreenState> = _screenState.asStateFlow()

    // 今入力されている数字（最初は空っぽの文字です）
    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    // 数字ボタンが押されたときの処理です
    fun onNumberClick(number: Int) {
        // 長すぎる数字は計算できないので、9桁までに制限します
        if (_inputText.value.length < 9) {
            _inputText.value += number.toString()
        }
    }

    // 「14」または「21」のボタンが押されたときの計算処理です
    fun calculate(divisor: Int) {
        // 今の入力を数字に直します（もし空っぽなら何もしません）
        val currentInput = _inputText.value.toIntOrNull()
        if (currentInput != null) {
            val quotient = currentInput / divisor // 商（答え）を計算
            val remainder = currentInput % divisor // 余りを計算
            
            // 計算が終わったので、画面を「結果画面」に切り替えます
            _screenState.value = ScreenState.Result(
                input = currentInput,
                divisor = divisor,
                quotient = quotient,
                remainder = remainder
            )
        }
    }

    // 「C (クリア)」ボタンが押されたときの処理です
    fun clear() {
        _inputText.value = "" // 入力されている数字を空っぽに戻します
        _screenState.value = ScreenState.Input // 画面を「入力画面」に戻します
    }
}
