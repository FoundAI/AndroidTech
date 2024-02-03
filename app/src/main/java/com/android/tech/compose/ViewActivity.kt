package com.android.tech.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.tech.ui.theme.AndroidTechTheme

const val TAG: String = "MainActivity"

class ViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContent类似setContentView一样为Activity设置布局
        setContent {
            //根据项目名称生层的主题名称
            AndroidTechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //ScrollView功能
                    Column(modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())) {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

//使用了@Composeable注解称之为组合函数
//@Composeable注解注释可告知 Compose 编译器，此函数需要转化为页面显示，并且和协程中suspend函数一样，
//只能在Composeable注解函数中调用另外一个Composeable注解函数
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //Column布局使得组件垂直排列
    Column {
        //Greeting函数中的Text组件，就是Compose提供的文本组件，类似XML方式中的TextView组件
        //Modifier修饰符
        //修饰符可以更改大小、布局、外观与添加点击事件等
        //modifier修饰符的顺序会影响最终结果
        //先添加背景色，再设置边距就成了内边距的效果，
        Text(
            text = "Hello $name!",
            modifier = Modifier
                .background(Color.Red)
                .padding(10.dp)
                .clickable {
                    Log.d(TAG, "Hello $name!")
                }
        )

        Text(text = "First Compose Demo 1",
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Red)
                .clickable {
                    Log.d(TAG, "First Compose Demo 1")
                }
        )

        //Row布局使得组件水平排列
        Row {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
            Text(
                text = "First Compose Demo 1",
                modifier = modifier
            )
        }
        //Box相当于XML中的FrameLayout，还有ConstraintLayout等布局
        Box {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
            Text(
                text = "First Compose Demo 1",
                modifier = modifier
            )
        }

        Column() {
            for (i in 0..10) {
                more("Compose${i + 1},快来学习吧～")
            }
        }
    }
}

@Composable
fun more(title: String) {

    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Blue)
            .padding(10.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = {
                Log.d(TAG, "Hello")
            }) {
            Text(
                text = "详情",
                color = Color.White,

                )
        }
    }
}

//@Preview注解是方便开发者在不运行的前提下可预览效果
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTechTheme {
        Greeting("Android")

    }
}