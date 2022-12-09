package com.example.animatedsplashscreen

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animatedsplashscreen.ui.theme.AnimatedSplashScreenTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedSplashScreenTheme {
                SplashScreenAnimate()
            }
        }
    }
}

@Composable
fun SplashScreenAnimate(){
    val scale = remember{androidx.compose.animation.core.Animatable(0f)}
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                delayMillis = 700,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            ) )
        delay(3000L)
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_700))
            ) {
        Image(
            painter = painterResource(id = R.drawable.jetpackcompose),
            contentDescription = "Splash screen image",
            modifier = Modifier
                .width(600.dp)
                .height(600.dp)
                .scale(scale.value)
            )
    }
}