package com.rodrigodominguez.motionlayouttalk.abcmotion

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.rodrigodominguez.motionlayouttalk.R

@ExperimentalMotionApi
@Composable
@Preview
fun CollapseScreenDemo() {
    var animateToEnd by remember {
        mutableStateOf(false)
    }
    val progress by animateFloatAsState(
        targetValue = if (animateToEnd) 1f else 0f,
        animationSpec = tween(3000)
    )
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene1_collapse)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        val contentAttr = motionProperties(id = "content")
        val titleAttr = motionProperties(id = "title")

        Box(
            modifier = Modifier
                .layoutId("content")
                .clip(
                    CutCornerShape(
                        topStart = contentAttr.value.int("corner").toFloat()
                    )
                )
                .background(Color.LightGray)
                .clickable { animateToEnd = !animateToEnd }
        )
        Box(
            modifier = Modifier
                .layoutId("film_image")
                .background(Color.Green)
        )

        Text(
            text = "MotionLayout example",
            fontSize = titleAttr.value.fontSize("fontSize"),
            modifier = Modifier.layoutId("title")
        )

        Box(
            modifier = Modifier
                .layoutId("circle")
                .clip(CircleShape)
                .background(Color.Red)
        )
    }
}