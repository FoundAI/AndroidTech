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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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


class ListActivity : ComponentActivity() {
    val TAG: String = "ListActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContent类似setContentView一样为Activity设置布局
        setContent {
            //根据项目名称生层的主题名称
            AndroidTechTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    initView(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }

            }
        }
    }


    @Composable
    fun initView(name: String, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(items = getData()) {
                setItem(it)
            }
        }
    }

    @Composable
    fun setItem(title: String) {

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

    fun getData(): List<String> {
        return List(20) { "hello${it + 1}" }
    }

    @Preview(showBackground = true)
    @Composable
    fun ListPreview() {
        AndroidTechTheme {
            initView("Android")
        }
    }
}


