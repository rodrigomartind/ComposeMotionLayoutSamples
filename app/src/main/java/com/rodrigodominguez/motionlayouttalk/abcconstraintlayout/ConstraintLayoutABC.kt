package com.rodrigodominguez.motionlayouttalk.abcconstraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Preview
@Composable
fun ConstraintLayoutSample1() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (button, title) = createRefs()
        val guideline1 = createGuidelineFromStart(36.dp)

        Text(text = "Title text",
            fontSize = 34.sp,
            modifier = Modifier.constrainAs(title) {
                centerVerticallyTo(parent)
                start.linkTo(guideline1)
            })

        Button(
            modifier = Modifier.constrainAs(button) {
                top.linkTo(title.bottom, margin = 16.dp)
                start.linkTo(guideline1)
            },
            onClick = { }
        ) {
            Text(text = "Button")
        }

    }
}

@Composable
fun ConstraintLayoutSample2() {
    ConstraintLayout(
        ConstraintSet {
            val button = createRefFor("button")
            val title = createRefFor("title")
            val g1 = createGuidelineFromStart(80.dp)
            constrain(button) {
                top.linkTo(title.bottom, 32.dp)
                start.linkTo(g1)
            }
            constrain(title) {
                centerVerticallyTo(parent)
                start.linkTo(g1)
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier.layoutId("title"),
            text = "Title text", fontSize = 44.sp
        )
        Button(
            modifier = Modifier.layoutId("button"), onClick = { }
        ) {
            Text(text = "Button")
        }
    }
}

@Preview
@Composable
fun ConstraintLayoutSample3() {
    ConstraintLayout(
        ConstraintSet(
            """{
                Debug: { name: 'somename' },
                g1: { type: 'vGuideline', start: 56 }, 
                button: {                               
                  top: ['title', 'bottom', 16],         
                  start: ['g1', 'start']
                },
                title: {                               
                  centerVertically: 'parent',           
                  start: ['g1', 'start']
                }
            }"""
        ),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier.layoutId("title"),
            text = "Title text", fontSize = 44.sp
        )
        Button(
            modifier = Modifier.layoutId("button"), onClick = { }
        ) {
            Text(text = "Button")
        }
    }
}