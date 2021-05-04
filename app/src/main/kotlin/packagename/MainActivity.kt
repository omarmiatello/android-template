package com.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import packagename.theme.ModuleNameTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModuleNameTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ModuleNameScreen()
                }
            }
        }
    }
}

@Composable
fun ModuleNameScreen() {
    val vm = viewModel<MainViewModel>()
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        val counter by vm.counter.collectAsState()
        Text(
            text = "This is an example, counter: $counter",
            style = MaterialTheme.typography.h3,
        )
    }
}
