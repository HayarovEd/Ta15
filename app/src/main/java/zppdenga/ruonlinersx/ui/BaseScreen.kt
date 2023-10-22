package zppdenga.ruonlinersx.ui

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import zppdenga.ruonlinersx.R
import zppdenga.ruonlinersx.R.font
import zppdenga.ruonlinersx.ui.theme.baseText
import zppdenga.ruonlinersx.ui.theme.green
import zppdenga.ruonlinersx.ui.theme.white


@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

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
                .padding(10.dp)
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
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.curr),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(font.exo)),
                        fontWeight = FontWeight(400),
                        color = baseText
                    ),
                    textAlign = TextAlign.Center
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