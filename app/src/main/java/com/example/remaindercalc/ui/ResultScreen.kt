package com.example.remaindercalc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
    divisor: Int?,   // 割った数（14か21）。nullの場合は掛け算の答えだけを表示します
    quotient: Int?,  // 商（答え）
    remainder: Int?, // 余り
    onClear: () -> Unit // Cボタンを押したときのお願い
) {
    // 画面全体を縦に並べる設定です
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (divisor == null) {
            // 割り算をしない場合（×ボタン2回目のとき）は、掛け算の答えだけを表示します
            Text(
                text = "掛け算の答え",
                fontSize = 14.sp,
                color = Color.LightGray
            )
            Text(
                text = "$input",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            // 合計を表示します
            Text(
                text = "合計：$input",
                fontSize = 14.sp,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(4.dp)) // 少し隙間を開けます

            // 14や21の数字と、商（答え）を表示します
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$divisor",
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .background(Color.DarkGray, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                )
                Text(
                    text = "シート：$quotient",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            // 14や21の数字と、余りを表示します
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$divisor",
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .background(Color.DarkGray, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                )
                Text(
                    text = "バラ：$remainder",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

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
