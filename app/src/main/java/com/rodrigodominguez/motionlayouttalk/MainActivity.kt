package com.rodrigodominguez.motionlayouttalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionLayoutDebugFlags
import androidx.constraintlayout.compose.MotionScene
import com.rodrigodominguez.motionlayouttalk.abcmotion.YoutubeDemoComposable
import com.rodrigodominguez.motionlayouttalk.ui.theme.MotionLayoutTalkTheme
import java.util.*
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {


    @ExperimentalMaterialApi
    @ExperimentalMotionApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutTalkTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    YoutubeDemoComposable()
                }
            }
        }
    }
}


@ExperimentalMotionApi
@ExperimentalMaterialApi
@Composable
fun MotionSampleSwipe() {
    var componentHeight by remember { mutableStateOf(100f) }
    val swipeableState = rememberSwipeableState("Bottom")
    val anchors = mapOf(0f to "Bottom", componentHeight to "Top")

    val mprogress = (swipeableState.offset.value / componentHeight)

    MotionLayout(
        motionScene = MotionScene(
            """{
                Header: { exportAs: 'motion6'},
                ConstraintSets: {
                  start: {
                    box1: {
                      width: '90%',
                      height: 90,
                      start: ['parent', 'start'],
                      end: ['parent', 'end'],
                      bottom: ['parent','bottom', 16],
                      translationZ: 1
                    }
                  },
                  end: {
                    box1: {
                      width: '100%',
                      height: 220,
                      start: ['parent', 'start'],
                      end: ['parent', 'end'],
                      top: ['parent','top'],
                      translationZ: 0
                    }
                }
            }
}"""
        ),
        progress = mprogress,
        debug = EnumSet.of(MotionLayoutDebugFlags.SHOW_ALL),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Box(
            modifier = Modifier
                .layoutId("box1")
                .background(Color.Green)
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    reverseDirection = true,
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    orientation = Orientation.Vertical
                ).onSizeChanged { size ->
                    componentHeight = size.height.toFloat()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MotionLayoutTalkTheme {
    }
}