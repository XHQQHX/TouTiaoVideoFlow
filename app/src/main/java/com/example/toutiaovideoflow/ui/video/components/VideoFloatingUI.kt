package com.example.toutiaovideoflow.ui.video.components
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.ui.theme.*

@Composable
fun VideoFloatingUI(
    modifier: Modifier = Modifier,
    item: VideoItem,
    isDragging: Boolean,
    isPlaying: Boolean
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeVideoFloatingUI(
                modifier = modifier,
                item = item,
                isPlaying = isPlaying
            )
        }
        else -> {
            PortraitVideoFloatingUI(
                modifier = modifier,
                item = item,
                isDragging = isDragging
            )
        }
    }
}

@Composable
private fun LandscapeVideoFloatingUI(
    modifier: Modifier = Modifier,
    item: VideoItem,
    isPlaying: Boolean
) {
    if(!isPlaying) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // 左侧信息区域
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // 头像
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(White)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(getAvatarResId(item.id)),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(38.dp)
                                .align(Alignment.Center),
                            contentScale = ContentScale.Crop,
                        )
                    }

                    // 作者信息和关注按钮
                    Column {
                        Text(
                            text = "@ ${item.author}",
                            color = White,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "# ${item.title}",
                            color = White,
                            fontSize = 16.sp
                        )
                    }

                    // 关注按钮
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .height(32.dp)
                            .width(80.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Red,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "+关注",
                            fontSize = 14.sp
                        )
                    }
                }

                // 右侧操作区域
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconTextItem(
                        iconResId = R.drawable.ic_like,
                        text = item.likeCount
                    )
                    IconTextItem(
                        iconResId = R.drawable.ic_comment,
                        text = item.commentCount
                    )
                    IconTextItem(
                        iconResId = R.drawable.ic_collect,
                        text = item.collectCount
                    )
                    IconTextItem(
                        iconResId = R.drawable.ic_share,
                        text = "分享"
                    )
                }
            }
        }
    }
}


@Composable
private fun PortraitVideoFloatingUI(
    modifier: Modifier = Modifier,
    item: VideoItem,
    isDragging: Boolean
) {
    if(!isDragging) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.85f)
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = "@ ${item.author}",
                            color = White,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "# ${item.title}",
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
                                Image(
                                    painter = rememberAsyncImagePainter(getAvatarResId(item.id)),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(42.dp)
                                        .align(Alignment.Center),
                                    contentScale = ContentScale.Crop,
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
                            text = item.likeCount
                        )
                        IconTextItem(
                            iconResId = R.drawable.ic_comment,
                            text = item.commentCount
                        )
                        IconTextItem(
                            iconResId = R.drawable.ic_collect,
                            text = item.collectCount
                        )
                        IconTextItem(
                            iconResId = R.drawable.ic_share,
                            text = "分享"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IconTextItem(
    modifier: Modifier = Modifier,
    iconResId: Int,
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

@SuppressLint("LocalContextResourcesRead", "DiscouragedApi")
@Composable
@DrawableRes
fun getAvatarResId(id: String): Int {
    val context = LocalContext.current
    val index = id.toBigInteger().mod(16.toBigInteger()).toInt()
    val resId = context.resources.getIdentifier(
        "av_$index",
        "mipmap",
        context.packageName
    )
    return if (resId != 0) resId else R.drawable.ic_default_avatar
}

