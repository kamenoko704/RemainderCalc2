package com.example.remaindercalc.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text

// このブロックは「計算結果を表示する画面」のデザインを作るところです
@Composable
fun ResultScreen(
    input: Int,      // 元の数字
    divisor: Int,    // 割った数（14か21）
    quotient: Int,   // 商（答え）
    remainder: Int,  // 余り
    onClear: () -> Unit // Cボタンを押したときのお願い
) {
    // 画面全体を縦に並べる設定です
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 小さく「何を何で割ったか」を表示します
        Text(
            text = "$input ÷ $divisor",
            fontSize = 16.sp,
            color = Color.LightGray
        )
        
        Spacer(modifier = Modifier.height(8.dp)) // 少し隙間を開けます

        // 商（答え）と余りを大きく表示します
        Text(
            text = "$quotient あまり $remainder",
            fontSize = 20.sp, // はみ出さないように少しだけ調整しました
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp)) // 少し隙間を開けます

        // 赤色のクリアボタン（入力画面に戻るボタン）です
        Button(
            onClick = onClear,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCC0000)), // 赤色
            modifier = Modifier.size(48.dp) // 押しやすいように少し大きくします
        ) {
            Text("C", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
