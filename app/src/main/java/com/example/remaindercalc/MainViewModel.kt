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
        val divisor: Int?, 
        val quotient: Int?, 
        val remainder: Int?
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

    // 掛け算のための記憶用変数です
    private var storedNumber: Int? = null // 1つ目の数字を覚えておく場所
    private var isMultiplyMode: Boolean = false // 掛け算ボタンが押されたかどうかの目印

    // 数字ボタンが押されたときの処理です
    fun onNumberClick(number: Int) {
        // 長すぎる数字は計算できないので、9桁までに制限します
        if (_inputText.value.length < 9) {
            _inputText.value += number.toString()
        }
    }

    // 「かける」ボタンが押されたときの処理です
    fun onMultiplyClick() {
        // 現在の入力を文字として取得します
        val currentInputString = _inputText.value
        
        // 何も入力されていなくて、掛け算モードでもない場合は何もしません
        if (currentInputString.isEmpty() && !isMultiplyMode) return

        if (isMultiplyMode && storedNumber != null) {
            // 2回目の「×」が押されたとき：掛け算の答えを計算して、結果画面に出します（＝の役割）
            // もし空っぽのまま「×」を押した場合は「1」を掛けたことにします（例：5 × × ＝ 5）
            val currentInput = currentInputString.toIntOrNull() ?: 1
            val result = storedNumber!! * currentInput
            
            // 掛け算の答えだけを結果画面に渡します（割り算はしないのでnullにします）
            _screenState.value = ScreenState.Result(
                input = result,
                divisor = null,
                quotient = null,
                remainder = null
            )
            
            // 次の計算のために状態をリセットします
            isMultiplyMode = false
            storedNumber = null
            _inputText.value = ""
        } else {
            // 1回目の「×」が押されたとき：今の数字を覚えて掛け算モードにします
            val currentInput = currentInputString.toIntOrNull()
            if (currentInput != null) {
                storedNumber = currentInput
                isMultiplyMode = true
                _inputText.value = "" // 2つ目の数字を入れるために画面を空っぽにします
            }
        }
    }

    // 「14」または「21」のボタンが押されたときの計算処理です
    fun calculate(divisor: Int) {
        // 今の入力を数字に直します（もし空っぽなら何もしません）
        val currentInput = _inputText.value.toIntOrNull()
        if (currentInput != null) {
            // 掛け算モードなら（前の数字 × 今の数字）を計算、そうでなければ今の数字をそのまま使います
            val targetNumber = if (isMultiplyMode && storedNumber != null) {
                storedNumber!! * currentInput
            } else {
                currentInput
            }

            val quotient = targetNumber / divisor // 商（答え）を計算
            val remainder = targetNumber % divisor // 余りを計算
            
            // 計算が終わったので、画面を「結果画面」に切り替えます
            _screenState.value = ScreenState.Result(
                input = targetNumber,
                divisor = divisor,
                quotient = quotient,
                remainder = remainder
            )
        }
    }

    // 「C (クリア)」ボタンが押されたときの処理です
    fun clear() {
        _inputText.value = "" // 入力されている数字を空っぽに戻します
        storedNumber = null   // 覚えていた数字も忘れます
        isMultiplyMode = false // 掛け算モードもやめます
        _screenState.value = ScreenState.Input // 画面を「入力画面」に戻します
    }
}
