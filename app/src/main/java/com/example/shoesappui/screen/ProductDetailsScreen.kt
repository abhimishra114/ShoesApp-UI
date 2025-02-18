package com.example.shoesappui.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoesappui.models.Product
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun ProductDetailsScreen(
    productId: String = "6"
) {
    val product = getProductList().find {
        it.id == productId
    }!!
    var xOffset by remember {
        mutableStateOf(800.dp)
    }
    var yOffset by remember {
        mutableStateOf(800.dp)
    }
    val animationXOffset = animateDpAsState(
        targetValue = xOffset,
        label = "",
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
    )
    val animationYOffset = animateDpAsState(
        targetValue = yOffset,
        label = "",
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
    )

    var productScale by remember {
        mutableFloatStateOf(.06f)
    }

    val animationProductScale = animateFloatAsState(
        targetValue = productScale,
        label = ""
    )

    val selectedColor by remember {
        mutableStateOf(product.color)
    }
    var productRotate by remember {
        mutableFloatStateOf(-60f)
    }

    val animationProductRotate = animateFloatAsState(
        targetValue = productRotate,
        label = ""
    )

    LaunchedEffect(true) {
        delay(150)
        xOffset = 140.dp
        yOffset = (-130).dp
        productScale = 1f
        productRotate = -30f
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .offset(x = animationXOffset.value, y = animationYOffset.value)
                .alpha(.3f)
                .size(400.dp)
                .background(
                    color = selectedColor,
                    shape = CircleShape
                )
        ) {

        }

        IconButton(
            onClick = {},
            Modifier
                .padding(start = 16.dp, top = 16.dp)
                .shadow(
                    elevation = 24.dp,
                    spotColor = DefaultShadowColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = Color.White, shape = RoundedCornerShape(12.dp)
                )
                .size(36.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null
            )
        }

        Column(

        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .scale(animationProductScale.value)
                    .rotate(animationProductRotate.value)
                    .padding(end = 48.dp, top = 30.dp)
                    .size(330.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Sneaker",
                        color = Color.Black,
                        fontSize = 10.sp,
                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                    )
                    Text(
                        text = product.name,
                        color = Color.Black,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(top = 2.dp),
                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                    )
                    Row(
                        modifier = Modifier.padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFDA45),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}