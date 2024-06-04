package tw.edu.pu.csim.yuzhou.a411134337

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt



class ForthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFFADD8E6) // 淺藍色
            ) {

                Drag()
            }
        }
    }
}

@Composable
fun Drag() {
    val context = LocalContext.current  //取得App的運行環境

    var check1 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var check2 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var check3 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var check4 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var check5 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var check6 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var check7 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var check8 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞
    var check9 by remember { mutableStateOf(false) } // 添加一個狀態來跟踪碰撞

    var blackAppleImageResId by remember { mutableStateOf(R.drawable.apple_2) }
    var blackBananaImageResId by remember { mutableStateOf(R.drawable.banana_2) }
    var blackCherryImageResId by remember { mutableStateOf(R.drawable.cherry_2) }
    var blackDurianImageResId by remember { mutableStateOf(R.drawable.durian_2) }
    var blackGrapeImageResId by remember { mutableStateOf(R.drawable.grape_2) }
    var blackLemonImageResId by remember { mutableStateOf(R.drawable.lemon_2) }
    var blackOngLaiImageResId by remember { mutableStateOf(R.drawable.ong_lai_2) }
    var blackStrawberryImageResId by remember { mutableStateOf(R.drawable.strawberry_2) }
    var blackWatermelonImageResId by remember { mutableStateOf(R.drawable.watermelon_2) }

    var offset1 by remember { mutableStateOf(Offset(100f, 250f)) }
    var offset2 by remember { mutableStateOf(Offset(300f, 250f)) }
    var offset3 by remember { mutableStateOf(Offset(500f, 250f)) }
    var offset4 by remember { mutableStateOf(Offset(700f, 250f)) }
    var offset5 by remember { mutableStateOf(Offset(25f, 1500f)) }
    var offset6 by remember { mutableStateOf(Offset(225f, 1500f)) }
    var offset7 by remember { mutableStateOf(Offset(425f, 1500f)) }
    var offset8 by remember { mutableStateOf(Offset(625f, 1500f)) }
    var offset9 by remember { mutableStateOf(Offset(825f, 1500f)) }
    var offset10 by remember { mutableStateOf(Offset(0f, 0f)) }
    var offset11 by remember { mutableStateOf(Offset(3f, 0f)) }
    var offset12 by remember { mutableStateOf(Offset(6f, 0f)) }
    var offset13 by remember { mutableStateOf(Offset(0f, 2f)) }
    var offset14 by remember { mutableStateOf(Offset(3f, 2f)) }
    var offset15 by remember { mutableStateOf(Offset(6f, 2f)) }
    var offset16 by remember { mutableStateOf(Offset(0f, 4f)) }
    var offset17 by remember { mutableStateOf(Offset(3f, 4f)) }
    var offset18 by remember { mutableStateOf(Offset(6f, 4f)) }

    var title by remember { mutableStateOf("第三關：圖片辨識") }
    var name by remember { mutableStateOf("幫我把圖片放入正確格子內") }
    var height by remember { mutableStateOf(0) }
    var width by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(true) }

    val fontFamily = FontFamily(
        Font(R.font.your_font_regular),
        Font(R.font.your_font_bold)
    )
    Box(modifier = Modifier.fillMaxSize()) {
        // 標題
        Text(
            text = title,
            fontSize = 22.sp,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
        )

        Spacer(modifier = Modifier.height(400.dp))

        Text(
            text = name,
            fontSize = 14.sp,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(52.dp)
                .align(Alignment.TopCenter)
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // 九宮格內的圖片
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 檢查是否與黑蘋果發生碰撞
                if (check1 == true) {
                    // 更改黑蘋果的圖像資源
                    blackAppleImageResId = R.drawable.apple // 用新的蘋果圖像的資源 ID 替換這裡的資源 ID
                }

                if (check2 == true) {
                    // 更改黑香蕉的圖像資源
                    blackBananaImageResId = R.drawable.banana // 用新的香蕉圖像的資源 ID 替換這裡的資源 ID
                }

                if (check3 == true) {
                    // 更改黑櫻桃的圖像資源
                    blackCherryImageResId = R.drawable.cherry // 用新的櫻桃圖像的資源 ID 替換這裡的資源 ID
                }

                if (check4 == true) {
                    // 更改黑榴槤的圖像資源
                    blackDurianImageResId = R.drawable.durian // 用新的榴槤圖像的資源 ID 替換這裡的資源 ID
                }

                if (check5 == true) {
                    // 更改黑葡萄的圖像資源
                    blackGrapeImageResId = R.drawable.grape // 用新的葡萄圖像的資源 ID 替換這裡的資源 ID
                }

                if (check6 == true) {
                    // 更改黑檸檬的圖像資源
                    blackLemonImageResId = R.drawable.lemon // 用新的檸檬圖像的資源 ID 替換這裡的資源 ID
                }

                if (check7 == true) {
                    // 更改黑鳳梨的圖像資源
                    blackOngLaiImageResId = R.drawable.ong_lai // 用新的鳳梨圖像的資源 ID 替換這裡的資源 ID
                }

                if (check8 == true) {
                    // 更改黑草莓的圖像資源
                    blackStrawberryImageResId = R.drawable.strawberry // 用新的草莓圖像的資源 ID 替換這裡的資源 ID
                }

                if (check9 == true) {
                    // 更改黑西瓜的圖像資源
                    blackWatermelonImageResId = R.drawable.watermelon // 用新的西瓜圖像的資源 ID 替換這裡的資源 ID
                }


                // 第一行
                Row(Modifier.padding(4.dp)) {
                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset10.x.roundToInt(), offset10.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))

                    ) {
                        Image(
                            painter = painterResource(id = blackAppleImageResId),
                            contentDescription = "黑蘋果",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset11.x.roundToInt(), offset11.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))
                    ) {
                        Image(
                            painter = painterResource(id = blackBananaImageResId),
                            contentDescription = "黑香蕉",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset12.x.roundToInt(), offset12.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))
                    ) {
                        Image(
                            painter = painterResource(id = blackCherryImageResId),
                            contentDescription = "黑櫻桃",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                //第二行
                Row(Modifier.padding(4.dp)) {
                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset13.x.roundToInt(), offset13.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))
                    ) {
                        Image(
                            painter = painterResource(id = blackDurianImageResId),
                            contentDescription = "黑榴槤",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset14.x.roundToInt(), offset14.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))
                    ) {
                        Image(
                            painter = painterResource(id = blackGrapeImageResId),
                            contentDescription = "黑葡萄",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset15.x.roundToInt(), offset15.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))
                    ) {
                        Image(
                            painter = painterResource(id = blackLemonImageResId),
                            contentDescription = "黑檸檬",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                //第三行
                Row(Modifier.padding(4.dp)) {
                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset16.x.roundToInt(), offset17.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))
                    ) {
                        Image(
                            painter = painterResource(id = blackOngLaiImageResId),
                            contentDescription = "黑鳳梨",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset17.x.roundToInt(), offset17.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))
                    ) {
                        Image(
                            painter = painterResource(id = blackStrawberryImageResId),
                            contentDescription = "黑草莓",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                height = coordinates.size.height
                                width = coordinates.size.width
                            }
                            .offset { IntOffset(offset18.x.roundToInt(), offset18.y.roundToInt()) }
                            .size(100.dp)
                            .background(Color(0xFFADD8E6)) // 淺藍色
                            .padding(4.dp)
                            .background(Color(0xFFE9B97C))
                    ) {
                        Image(
                            painter = painterResource(id = blackWatermelonImageResId),
                            contentDescription = "黑西瓜",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            //4水果
            Box(modifier = Modifier
                .offset { IntOffset(offset1.x.roundToInt(), offset1.y.roundToInt()) }
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset1 += dragAmount

                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.apple),
                    contentDescription = "蘋果",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Box(modifier = Modifier
                .offset { IntOffset(offset2.x.roundToInt(), offset2.y.roundToInt()) }
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset2 += dragAmount
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.banana),
                    contentDescription = "香蕉",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Box(modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset3.x.roundToInt(), offset3.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset3 += dragAmount
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cherry),
                    contentDescription = "櫻桃",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Box(modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset4.x.roundToInt(), offset4.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset4 += dragAmount
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.durian),
                    contentDescription = "榴槤",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            //5水果
            Box(modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset5.x.roundToInt(), offset5.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset5 += dragAmount
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.grape),
                    contentDescription = "葡萄",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Box(modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset6.x.roundToInt(), offset6.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset6 += dragAmount
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lemon),
                    contentDescription = "檸檬",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Box(modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset7.x.roundToInt(), offset7.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset7 += dragAmount
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ong_lai),
                    contentDescription = "鳳梨",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Box(modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset8.x.roundToInt(), offset8.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset8 += dragAmount
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.strawberry),
                    contentDescription = "草莓",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Box(modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    height = coordinates.size.height
                    width = coordinates.size.width
                }
                .offset { IntOffset(offset9.x.roundToInt(), offset9.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset9 += dragAmount
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.watermelon),
                    contentDescription = "西瓜",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Box(
                modifier = Modifier
            ){
                var r1: Rect = Rect(offset1.x.toInt(), offset1.y.toInt(),
                    offset1.x.toInt()+width, offset1.y.toInt()+height)
                var r2: Rect = Rect(offset2.x.toInt(), offset2.y.toInt(),
                    offset2.x.toInt()+width, offset2.y.toInt()+height)
                var r3: Rect = Rect(offset3.x.toInt(), offset3.y.toInt(),
                    offset3.x.toInt()+width, offset3.y.toInt()+height)
                var r4: Rect = Rect(offset4.x.toInt(), offset4.y.toInt(),
                    offset4.x.toInt()+width, offset4.y.toInt()+height)
                var r5: Rect = Rect(offset5.x.toInt(), offset5.y.toInt(),
                    offset5.x.toInt()+width, offset5.y.toInt()+height)
                var r6: Rect = Rect(offset6.x.toInt(), offset6.y.toInt(),
                    offset6.x.toInt()+width, offset6.y.toInt()+height)
                var r7: Rect = Rect(offset7.x.toInt(), offset7.y.toInt(),
                    offset7.x.toInt()+width, offset7.y.toInt()+height)
                var r8: Rect = Rect(offset8.x.toInt(), offset8.y.toInt(),
                    offset8.x.toInt()+width, offset8.y.toInt()+height)
                var r9: Rect = Rect(offset9.x.toInt(), offset9.y.toInt(),
                    offset9.x.toInt()+width, offset9.y.toInt()+height)

                var r10: Rect = Rect(200, 490, 250, 650)
                var r11: Rect = Rect(475, 500, 575, 650)
                var r12: Rect = Rect(700, 600, 825, 650)

                var r13: Rect = Rect(200, 850, 250, 900)
                var r14: Rect = Rect(475, 850, 575, 900)
                var r15: Rect = Rect(700, 850, 825, 900)

                var r16: Rect = Rect(200, 1200, 250, 1225)
                var r17: Rect = Rect(475, 1200, 575, 1225)
                var r18: Rect = Rect(700, 1200, 825, 1225)

                if(r1.intersect(r10)) {
                    check1 = true
                } else{
                    check1 = false
                }

                if(r2.intersect(r11)) {
                    check2 = true
                } else{
                    check2 = false
                }

                if(r3.intersect(r12)) {
                    check3 = true
                } else{
                    check3 = false
                }

                if(r4.intersect(r13)) {
                    check4 = true
                } else{
                    check4 = false
                }

                if(r5.intersect(r14)) {
                    check5 = true
                } else{
                    check5 = false
                }

                if(r6.intersect(r15)) {
                    check6 = true
                } else{
                    check6 = false
                }

                if(r7.intersect(r16)) {
                    check7 = true
                } else{
                    check7 = false
                }

                if(r8.intersect(r17)) {
                    check8 = true
                } else{
                    check8 = false
                }

                if(r9.intersect(r18)) {
                    check9 = true
                } else{
                    check9 = false
                }
            }


            if(check1==check2 && check2==check3 && check3==check4 && check4==check5 && check5==check6 && check6==check7
                && check7==check8 && check8==check9 && check9 == true && showDialog){
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(
                            text = "第三關",
                            fontFamily = fontFamily
                        )
                    },
                    text = {
                        Text(
                            text = "恭喜全數過關!!!",
                            fontFamily = fontFamily
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            }
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
}






