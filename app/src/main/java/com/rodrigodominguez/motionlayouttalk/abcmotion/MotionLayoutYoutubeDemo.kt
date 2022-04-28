package com.rodrigodominguez.motionlayouttalk.abcmotion

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionLayoutDebugFlags
import androidx.constraintlayout.compose.MotionScene
import com.rodrigodominguez.motionlayouttalk.R
import java.util.*

@Preview
@ExperimentalMotionApi
@Composable
fun YoutubeDemoComposable() {
    var animatedToEnd by remember {
        mutableStateOf(false)
    }

    val progress by animateFloatAsState(
        targetValue = if (animatedToEnd) 1f else 0f,
        animationSpec = tween(1000)
    )

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene1)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val contentProperties = motionProperties(id = "content_video")

        Box(
            modifier = Modifier
                .layoutId("content_video")
                .background(contentProperties.value.color("background"))
                .clickable { animatedToEnd = !animatedToEnd }
        )

        Box(
            modifier = Modifier
                .layoutId("video_view")
                .background(Color.Black)
        )

        Text(
            text = "Title video", fontSize = 18.sp,
            modifier = Modifier.layoutId("title")
        )

        Text(
            text = "Description video",
            modifier = Modifier.layoutId("description")
        )
    }
}