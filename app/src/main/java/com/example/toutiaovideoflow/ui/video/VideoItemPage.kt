package com.example.toutiaovideoflow.ui.video
import com.example.toutiaovideoflow.R

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import com.example.toutiaovideoflow.ui.theme.*

@Composable
fun VideoItemPage (
    modifier: Modifier = Modifier,
){
    Box(modifier = modifier
        .fillMaxSize()
        .background(PitchBlack)
        .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .weight(0.85f)
                    .padding(8.dp),
            ) {
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = "@ ${stringResource(id = R.string.author)}",
                        color = White,
                        fontSize = 20.sp

                    )
                    Text(
                        text = "# ${stringResource(id = R.string.title)}",
                        color = White,
                        fontSize = 16.sp
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.15f)
                    .align(Alignment.CenterVertically)
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .padding(5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(White)
                                .size(45.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_default_avatar),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(42.dp)
                                    .align(Alignment.Center),
                                tint = Color.Unspecified
                            )
                        }
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .height(20.dp)
                                .width(40.dp)
                                .align(Alignment.BottomCenter)
                                .offset(y = 5.dp)
                                .zIndex(1f),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Red,
                                contentColor = White
                            )
                        ) {
                            Text(
                                text = "+关注",
                                fontSize = 11.sp
                            )
                        }
                    }
                    IconTextItem(
                        iconResId = R.drawable.ic_like,
                        text = stringResource(id = R.string.likeCount),
                    )
                    IconTextItem(
                        iconResId = R.drawable.ic_comment,
                        text = stringResource(id = R.string.commentCount),
                    )
                    IconTextItem(
                        iconResId = R.drawable.ic_collect,
                        text = stringResource(id = R.string.collectCount),
                    )
                    IconTextItem(
                        iconResId = R.drawable.ic_share,
                        text = "分享",
                    )
                }

            }
        }
    }
}
@Composable
fun IconTextItem(
    modifier: Modifier = Modifier,
    iconResId: Int,  // 使用drawable资源ID
    text: String,
    iconColor: Color = White,
    textColor: Color = White,
    iconSize: Dp = 24.dp,
    spacing: Dp = 4.dp,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(iconSize),
            tint = iconColor
        )
        Spacer(modifier = Modifier.height(spacing))
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp
        )
    }
}


@Preview
@Composable
fun VideoItemPagePreview() {
    VideoItemPage()
}