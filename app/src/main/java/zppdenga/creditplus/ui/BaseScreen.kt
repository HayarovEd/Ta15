package zppdenga.creditplus.ui

import android.os.Build.VERSION_CODES
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import zppdenga.creditplus.R
import zppdenga.creditplus.R.font
import zppdenga.creditplus.ui.theme.baseText
import zppdenga.creditplus.ui.theme.green
import zppdenga.creditplus.ui.theme.greyText
import zppdenga.creditplus.ui.theme.white

@RequiresApi(VERSION_CODES.O)
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
    val yourMoney = buildAnnotatedString {
        withStyle(style  = SpanStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(font.exo)),
            fontWeight = FontWeight(400),
            color = baseText
        )
        ) {
            append(stringResource(id = R.string.your_money))
        }
        withStyle(style  = SpanStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(font.exo)),
            fontWeight = FontWeight(400),
            color = baseText
        )
        ) {
            append(" ")
        }
        withStyle(style  = SpanStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(font.exo)),
            fontWeight = FontWeight(400),
            color = green
        )
        ) {
            append(state.value.time)
        }
    }
    if (state.value.error!=null) {
        Toast.makeText(context, state.value.error, Toast.LENGTH_LONG).show()
    }
    if (state.value.isLoading) {
        Box(modifier = modifier
            .fillMaxSize()
            .background(color = white)) {
            CircularProgressIndicator(
                modifier = modifier
                    .align(alignment = Alignment.Center)
                    .size(100.dp),
                color = green
            )
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = white)
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(id = R.string.amount_loan),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(font.exo)),
                        fontWeight = FontWeight(400),
                        color = baseText
                    )
                )
                Text(
                    text = "${state.value.neededSum} ${stringResource(id = R.string.curr)}",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(font.exo)),
                        fontWeight = FontWeight(400),
                        color = baseText
                    )
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Slider(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                value = state.value.neededSum.toFloat(),
                valueRange = 1000f..state.value.maxSum.toFloat(),
                steps = 1000,
                colors = SliderDefaults.colors(
                    thumbColor = green,
                    activeTrackColor = green,
                    inactiveTrackColor = white
                ),
                onValueChange = { viewModel.filterByMaxAmount(it.toInt()) }
            )
            Spacer(modifier = modifier.height(15.dp))
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.min_money),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(font.exo)),
                        fontWeight = FontWeight(400),
                        color = greyText
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${state.value.maxSum} ${stringResource(id = R.string.curr)}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(font.exo)),
                        fontWeight = FontWeight(400),
                        color = greyText
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = modifier.height(10.dp))
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItemSub(
                    name = stringResource(id = R.string.first_loan),
                    image = painterResource(id = R.drawable.percent)
                )
                ItemSub(
                    name = stringResource(id = R.string.checkout),
                    image = painterResource(id = R.drawable.checkout)
                )
                ItemSubAn(
                    name = yourMoney,
                    image = painterResource(id = R.drawable.time)
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(state.value.moneyList) { loan ->
                    ItemLoan(loan = loan)
                }
            }
        }
    }
}

@Composable
private fun ItemSub(
    modifier: Modifier = Modifier,
    name: String,
    image: Painter
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = modifier.size(72.dp),
            painter = image,
            contentDescription = ""
        )
        Spacer(modifier = modifier.height(15.dp))
        Text(
            text = name,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(font.exo)),
                fontWeight = FontWeight(400),
                color = baseText
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ItemSubAn(
    modifier: Modifier = Modifier,
    name: AnnotatedString,
    image: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier.size(72.dp),
            painter = image,
            contentDescription = ""
        )
        Spacer(modifier = modifier.height(15.dp))
        Text(
            text = name,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(font.exo)),
                fontWeight = FontWeight(400),
                color = baseText
            ),
            textAlign = TextAlign.Center
        )
    }
}