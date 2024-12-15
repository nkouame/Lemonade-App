package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    LemonadeApp()
                }
            }
        }
    }

    @Composable
    fun LemonadeApp() {
        var currentStep by remember { mutableIntStateOf(1) }
        var squeezeCount by remember { mutableIntStateOf(0) }
        var squeezesNeeded by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colorResource(R.color.yellow)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            when (currentStep) {
                                1 -> {
                                    currentStep = 2
                                    squeezesNeeded = (2..4).random()
                                    squeezeCount = 0
                                }

                                2 -> {
                                    squeezeCount++
                                    if (squeezeCount >= squeezesNeeded) {
                                        currentStep = 3
                                        squeezeCount = 0
                                    }
                                }

                                3 -> currentStep = 4
                                4 -> currentStep = 1
                            }
                        },
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFE0F4E0)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(
                                when (currentStep) {
                                    1 -> R.drawable.lemon_tree
                                    2 -> R.drawable.lemon_squeeze
                                    3 -> R.drawable.lemon_drink
                                    else -> R.drawable.lemon_restart
                                }
                            ),
                            contentDescription = when (currentStep) {
                                1 -> stringResource(R.string.lemon_tree_content_desc)
                                2 -> stringResource(R.string.lemon_content_desc)
                                3 -> stringResource(R.string.glass_of_lemonade_content_desc)
                                else -> stringResource(R.string.empty_glass_content_desc)
                            },
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = when (currentStep) {
                        1 -> stringResource(R.string.lemon_tree_select)
                        2 -> stringResource(R.string.lemon_squeeze)
                        3 -> stringResource(R.string.lemon_drink)
                        else -> stringResource(R.string.lemon_empty_glass)
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}