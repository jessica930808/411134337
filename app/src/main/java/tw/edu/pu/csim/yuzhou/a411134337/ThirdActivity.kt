package tw.edu.pu.csim.yuzhou.a411134337


import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import tw.edu.pu.csim.yuzhou.a411134337.ui.theme._411134337Theme
import kotlin.math.roundToInt



class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _411134337Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFADD8E6) // 淺藍色
                ) {
                    ThirdScreen("")

                }
            }
        }
    }
}



@Composable
fun ThirdScreen(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current  // 取得App的運行環境
    var showDialog by remember { mutableStateOf(true) }

    val fontFamily = FontFamily(
        Font(R.font.your_font_regular),
        Font(R.font.your_font_bold)
    )

    // 定義圖片資源列表
    val imageResources = listOf(R.drawable.road1, R.drawable.road2)
    var currentImageIndex by remember { mutableStateOf(0) }
    var imageChanged by remember { mutableStateOf(false) } // 新增變量來跟踪圖片是否已經變更過

    LaunchedEffect(imageChanged) {
        if (!imageChanged) {
            delay(3500) // 延遲2秒
            currentImageIndex = (currentImageIndex + 1) % imageResources.size // 變更圖片索引
            imageChanged = true // 設置為true以停止後續變更
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "過馬路",
                    fontFamily = fontFamily
                )
            },
            text = {
                Text(
                    text = "當紅燈亮起時，不能過馬路;當綠燈亮起時，可以過馬路。請幫小朋友過馬路去幫助奶奶撿起散落的水果，拖曳人物至斑馬線對面即可過關",
                    fontFamily = fontFamily, modifier = Modifier.padding(bottom = 8.dp)
                )
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("確定")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    showDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Blue
                ),
                modifier = Modifier
                    .padding(start = 0.dp, top = 0.dp) // 調整左側和頂部填充
            ) {
                Text(
                    text = "\uD83D\uDCA1",
                    fontSize = 30.sp,
                    fontFamily = fontFamily
                )
            }
            //標題
            Text(
                text = "第二關：過馬路",
                fontSize = 22.sp,
                color = Color.Blue,
                fontFamily = fontFamily,
                modifier = Modifier
                    .padding(bottom = 32.dp) // 向下偏移 24dp
                    .offset(y = (16).dp)
            )
        }

        Image(
            painter = painterResource(id = imageResources[currentImageIndex]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.2f) // 將圖片放大 1.2 倍
        )

        Drag(isDraggable = imageChanged)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, SecondActivity::class.java)
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
                        val intent = Intent(context, ForthActivity::class.java)
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

@Composable
fun Drag(isDraggable: Boolean) {
    val density = LocalDensity.current

    val initialOffsetX = with(density) { 275.dp.toPx() } // 調整 X 坐標
    val initialOffsetY = with(density) { 300.dp.toPx() } // 調整 Y 坐標

    var offset1 by remember { mutableStateOf(Offset(initialOffsetX, initialOffsetY)) }
    var offset2 by remember { mutableStateOf(Offset(initialOffsetX - 850, initialOffsetY)) }
    var offset3 by remember { mutableStateOf(Offset(initialOffsetX - 850, initialOffsetY+175)) }
    var height by remember { mutableStateOf(0) }
    var width by remember { mutableStateOf(0) }
    var isCollisionDetected by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var showDialog by remember { mutableStateOf(true) }
    val fontFamily = FontFamily(
        Font(R.font.your_font_regular),
        Font(R.font.your_font_bold)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset1.x.roundToInt(), offset1.y.roundToInt()) }
                .pointerInput(isDraggable) {
                    if (isDraggable) {
                        detectDragGestures { change, dragAmount ->
                            offset1 += dragAmount
                        }
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.kid),
                contentDescription = "小孩",
                modifier = Modifier
                    .size(100.dp)
            )
        }

        Box(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset2.x.roundToInt(), offset2.y.roundToInt()) }
                .pointerInput(isDraggable) {
                    if (isDraggable) {
                        detectDragGestures { change, dragAmount ->
                            offset2 += dragAmount
                        }
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.old_woman),
                contentDescription = "老奶奶",
                modifier = Modifier
                    .size(75.dp)
            )
        }

        Box(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset3.x.roundToInt(), offset3.y.roundToInt()) } // 水果的位置
                .pointerInput(isDraggable) {
                    if (isDraggable) {
                        detectDragGestures { change, dragAmount ->
                            offset3 += dragAmount
                        }
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.fruit), // 水果的圖片
                contentDescription = "水果",
                modifier = Modifier
                    .size(50.dp)
            )
        }

        // 检测碰撞并更新状态
        Box(modifier = Modifier
            .align(Alignment.TopEnd)
        ){
            var r1: Rect = Rect(offset1.x.toInt(), offset1.y.toInt(),
                offset1.x.toInt()+width, offset1.y.toInt()+height)
            var r2: Rect = Rect(offset2.x.toInt(), offset2.y.toInt(),
                offset2.x.toInt()+width, offset2.y.toInt()+height)

            if(r1.intersect(r2)) {
                isCollisionDetected = true // 当检测到碰撞时，设置状态为 true
            } else {
                isCollisionDetected = false // 当未检测到碰撞时，设置状态为 false
            }
        }

        // 检测到碰撞时显示弹窗
        if (isCollisionDetected && showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        text = "第二關",
                        fontFamily = fontFamily
                    )
                },
                text = {
                    Text(
                        text = "恭喜過關",
                        fontFamily = fontFamily
                    )
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(
                            text = "✔",
                            fontSize = 20.sp,
                        )
                    }
                }
            )
        }
    }
}
