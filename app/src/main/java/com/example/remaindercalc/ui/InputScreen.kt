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
    onNumberClick: (Int) -> Unit,// 数字ボタンを押したときのお願い
    onMultiplyClick: () -> Unit, // かけるボタンを押したときのお願い
    onCalculate: (Int) -> Unit   // 14や21ボタンを押したときのお願い
) {
    // 画面全体を縦に並べる設定です
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1行目: ×ボタン（一番上）
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Button(
                onClick = onMultiplyClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF8C00)), // オレンジ色
                modifier = Modifier.size(36.dp) // 丸いボタンに変更
            ) {
                Text("×", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(4.dp)) // 少し隙間を開けます

        // 2行目: 1, 2, 3
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            NumberButton(1, onNumberClick)
            NumberButton(2, onNumberClick)
            NumberButton(3, onNumberClick)
        }
        Spacer(modifier = Modifier.height(4.dp)) // 少し隙間を開けます
        
        // 3行目: 14, 4, 5, 6, 21 （ここが一番横幅が広くなります）
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            // 左端に14
            Button(
                onClick = { onCalculate(14) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0055FF)), // 青色
                modifier = Modifier.size(36.dp)
            ) {
                Text("14")
            }
            
            // 中央に4, 5, 6
            NumberButton(4, onNumberClick)
            NumberButton(5, onNumberClick)
            NumberButton(6, onNumberClick)
            
            // 右端に21
            Button(
                onClick = { onCalculate(21) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00AA00)), // 緑色
                modifier = Modifier.size(36.dp)
            ) {
                Text("21")
            }
        }
        Spacer(modifier = Modifier.height(4.dp)) // 少し隙間を開けます
        
        // 4行目: 7, 8, 9
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            NumberButton(7, onNumberClick)
            NumberButton(8, onNumberClick)
            NumberButton(9, onNumberClick)
        }
        Spacer(modifier = Modifier.height(4.dp)) // 少し隙間を開けます
        
        // 5行目: 0 （一番下）
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            NumberButton(0, onNumberClick)
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
