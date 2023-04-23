package cl.scotiabank.libraries.jetpackcomposeinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.scotiabank.libraries.jetpackcomposeinstagram.ui.theme.JetpackComposeInstagramTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeInstagramTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF161D26)),
                ) {
                    TwitterCard()
                    TuiDivider()
                    TwitterCard()

                }
            }
        }
    }
}

@Composable
fun TwitterCard() {
    var chat by remember { mutableStateOf(false) }
    var rt by remember { mutableStateOf(false) }
    var like by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF161D26))
            .padding(all = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "",
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(55.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextTile(title = "Edu", modifier = Modifier.padding(end = 8.dp))
                DefaultText(title = "@EjaramilloC", modifier = Modifier.padding(end = 8.dp))
                DefaultText(title = "4h", modifier = Modifier.padding(end = 8.dp))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_dots),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            TextBody(
                text = "Esto está muy weno :::::" +
                        "Esto está muy weno :::::" +
                        "Esto está muy weno :::::" +
                        "Esto está muy weno :::::" +
                        "Esto está muy weno :::::",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(percent = 10)),
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.padding(all = 16.dp)) {
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat_filled),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    isSelected = chat
                ) {
                    chat = !chat
                }
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rt),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rt),
                            contentDescription = "",
                            tint = Color(0xFF00FF27)
                        )
                    },
                    isSelected = rt
                ) {
                    rt = !rt
                }
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like_filled),
                            contentDescription = "",
                            tint = Color(0xFFFF0000)
                        )
                    },
                    isSelected = like
                ) {
                    like = !like
                }
            }
        }

    }
}

@Composable
fun SocialIcon(
    modifier: Modifier = Modifier,
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val defaultValue = 1
    Row(
        modifier = modifier.clickable {
            onItemSelected()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            selectedIcon()
        } else {
            unselectedIcon()
        }

        Text(
            text = if (isSelected) (defaultValue + 1).toString() else defaultValue.toString(),
            color = Color(0xFF7E8B98),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun TextBody(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun TextTile(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        color = Color.White,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}

@Composable
fun DefaultText(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        color = Color.Gray,
        modifier = modifier
    )
}

@Composable
fun TuiDivider() {
    Divider(modifier = Modifier
        .padding(top = 4.dp)
        .height(0.5.dp)
        .fillMaxWidth())
}
