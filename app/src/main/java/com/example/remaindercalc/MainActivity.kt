package com.example.remaindercalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.wear.compose.material.MaterialTheme
import com.example.remaindercalc.ui.InputScreen
import com.example.remaindercalc.ui.ResultScreen

// このクラスは、アプリが開かれたときに1番最初に動く「案内係」です。
class MainActivity : ComponentActivity() {
    // アプリの頭脳（MainViewModel）を準備します
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // スマホや時計の画面に「ここに絵を描いてね！」とセットします
        setContent {
            // Wear OS用の基本デザインを使います
            MaterialTheme {
                // 頭脳（ViewModel）から、今の画面の状態と数字を受け取ります
                val screenState by viewModel.screenState.collectAsState()
                val inputText by viewModel.inputText.collectAsState()

                // State（状態）に合わせて、InputScreenとResultScreenを切り替えます
                when (val state = screenState) {
                    // もし「入力」の状態だったら…
                    is ScreenState.Input -> {
                        // 入力画面を表示します！
                        InputScreen(
                            onNumberClick = { number -> viewModel.onNumberClick(number) },
                            onMultiplyClick = { viewModel.onMultiplyClick() },
                            onCalculate = { divisor -> viewModel.calculate(divisor) }
                        )
                    }
                    // もし「結果」の状態だったら…
                    is ScreenState.Result -> {
                        // 結果画面を表示します！
                        ResultScreen(
                            input = state.input,
                            divisor = state.divisor,
                            quotient = state.quotient,
                            remainder = state.remainder,
                            onClear = { viewModel.clear() }
                        )
                    }
                }
            }
        }
    }
}
