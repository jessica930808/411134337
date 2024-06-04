package tw.edu.pu.csim.yuzhou.a411134337

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.yuzhou.a411134337.ui.theme._411134337Theme

class SecondActivity : ComponentActivity() {

    private lateinit var correctSound: MediaPlayer
    private lateinit var incorrectSound: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        correctSound = MediaPlayer.create(this, R.raw.correct)
        incorrectSound = MediaPlayer.create(this, R.raw.incorrect)

        setContent {
            _411134337Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFADD8E6) // 淺藍色
                ) {
                    SecondScreen(
                        onCorrectClick ={ correctSound.start()
                        },
                        onIncorrectClick = { incorrectSound.start() }
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        correctSound.release()
        incorrectSound.release()
    }
}

@Composable
fun SecondScreen(
    onCorrectClick: () -> Unit,
    onIncorrectClick: () -> Unit
) {
    var displayText by remember { mutableStateOf("請問哪個是綠燈？") }
    var showCorrectDialog by remember { mutableStateOf(false) }
    var showIncorrectDialog by remember { mutableStateOf(false) }

    val fontFamily = FontFamily(
        Font(R.font.your_font_regular),
        Font(R.font.your_font_bold)
    )

    val context = LocalContext.current  //取得App的運行環境


    if (showCorrectDialog) {
        AlertDialog(
            onDismissRequest = { showCorrectDialog = false },
            title = {
                Text(
                    text = "回答正確",
                    fontFamily = fontFamily
                )
            },
            text = {
                Text(
                    text = "恭喜你，回答正確！！",
                    fontFamily = fontFamily
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showCorrectDialog = false
                        val intent = Intent(context, ThirdActivity::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Text(
                        text="✔",
                        fontSize = 20.sp,
                    )
                }
            }
        )
    }

    if (showIncorrectDialog) {
        AlertDialog(
            onDismissRequest = { showIncorrectDialog = false },
            title = {
                Text(
                    text = "回答錯誤",
                    fontFamily = fontFamily
                )
            },
            text = {
                Text(
                    text = "回答錯誤，請再試一次",
                    fontFamily = fontFamily
                )
            },
            confirmButton = {
                Button(onClick = { showIncorrectDialog = false }) {
                    Text(
                        text="✔",
                        fontSize = 20.sp,
                    )
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "第一關：紅綠燈辨識",
            fontSize = 22.sp,
            color = Color.Blue,
            fontFamily = fontFamily,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = displayText,
            fontSize = 20.sp,
            color = Color.Blue,
            fontFamily = fontFamily,
            modifier = Modifier.padding(bottom = 16.dp, top = 30.dp) // 將文字再往下移一點
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.red),
                    contentDescription = "紅燈",
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.White)
                        .clickable {
                            displayText = "紅燈"
                            onIncorrectClick()
                            showIncorrectDialog = true
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.green),
                    contentDescription = "綠燈",
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.White)
                        .clickable {
                            displayText = "綠燈"
                            onCorrectClick()
                            showCorrectDialog = true
                        }
                )
            }

            Spacer(modifier = Modifier.height(50.dp)) // 增加一個 Spacer 來調整按鈕的位置

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(bottom = 30.dp) // 給按鈕一些底部的填充
                ) {
                    Text(
                        text = "⇦",
                        fontSize = 20.sp,
                        fontFamily = fontFamily
                    )
                }
                Button(
                    onClick = {
                        val intent = Intent(context, ThirdActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(bottom = 30.dp) // 給按鈕一些底部的填充
                ) {
                    Text(
                        text = "⇨",
                        fontSize = 20.sp,
                        fontFamily = fontFamily
                    )
                }
            }
        }
    }
}
