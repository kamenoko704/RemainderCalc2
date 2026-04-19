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

// このブロックは「数字を入力する画面」のデザインを作るところです
@Composable
fun InputScreen(
    inputText: String,           // 今入力されている数字
    onNumberClick: (Int) -> Unit,// 数字ボタンを押したときのお願い
    onCalculate: (Int) -> Unit   // 14や21ボタンを押したときのお願い
) {
    // 画面全体を縦に並べる設定です
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 一番上に、今入力されている数字を表示します
        Text(
            text = if (inputText.isEmpty()) "0" else inputText, // 空なら0を表示
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // ここから下はテンキー（数字ボタン）を並べます
        
        // 1 2 3 の行
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            NumberButton(1, onNumberClick)
            NumberButton(2, onNumberClick)
            NumberButton(3, onNumberClick)
        }
        Spacer(modifier = Modifier.height(4.dp)) // 少し隙間を開けます
        
        // 4 5 6 の行
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            NumberButton(4, onNumberClick)
            NumberButton(5, onNumberClick)
            NumberButton(6, onNumberClick)
        }
        Spacer(modifier = Modifier.height(4.dp)) // 少し隙間を開けます
        
        // 7 8 9 の行
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            NumberButton(7, onNumberClick)
            NumberButton(8, onNumberClick)
            NumberButton(9, onNumberClick)
        }
        Spacer(modifier = Modifier.height(4.dp)) // 少し隙間を開けます
        
        // 14 0 21 の行
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            // 14の計算ボタン（青色）
            Button(
                onClick = { onCalculate(14) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0055FF)), // 少し明るい青
                modifier = Modifier.size(36.dp)
            ) {
                Text("14")
            }
            
            // 0ボタン
            NumberButton(0, onNumberClick)
            
            // 21の計算ボタン（緑色）
            Button(
                onClick = { onCalculate(21) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00AA00)), // 少し明るい緑
                modifier = Modifier.size(36.dp)
            ) {
                Text("21")
            }
        }
    }
}

// --------------------------------------------------------
// このブロックは「1つの数字ボタン」を作るための部品（お料理のレシピ）です
@Composable
fun NumberButton(number: Int, onClick: (Int) -> Unit) {
    Button(
        onClick = { onClick(number) },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray), // ボタンの色を暗い灰色にする
        modifier = Modifier.size(36.dp) // ボタンの大きさを決める
    ) {
        Text(number.toString()) // ボタンの中に数字を書く
    }
}
