package zppdenga.ruonlinersx.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import zppdenga.ruonlinersx.R
import zppdenga.ruonlinersx.domain.model.Loan
import zppdenga.ruonlinersx.ui.theme.baseText
import zppdenga.ruonlinersx.ui.theme.green
import zppdenga.ruonlinersx.ui.theme.greyText
import zppdenga.ruonlinersx.ui.theme.line
import zppdenga.ruonlinersx.ui.theme.white

@Composable
fun ItemLoan(
    modifier: Modifier = Modifier,
    loan: Loan
) {
    val openLink = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(loan.url))
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = modifier
                .width(224.dp),
            model = loan.imageUrl,
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        Spacer(modifier = modifier.height(30.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(75.dp),
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ItemData(
                modifier = modifier.weight(1f),
                name = loan.sumOne,
                content = stringResource(id = R.string.amount)
            )
            Divider(
                color = line,
                modifier = modifier
                    .fillMaxHeight()
                    .width(2.dp)
            )
            ItemData(
                modifier = modifier.weight(1f),
                name = loan.percent,
                content = stringResource(id = R.string.bet)
            )
            Divider(
                color = line,
                modifier = modifier
                    .fillMaxHeight()
                    .width(2.dp)
            )
            ItemData(
                modifier = modifier.weight(1f),
                name = loan.age,
                content = stringResource(id = R.string.age)
            )
        }
        Spacer(modifier = modifier.height(36.dp))
        Button(
            modifier = modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = green
            ),
            shape = RoundedCornerShape(6.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            onClick = { openLink.launch(intent) }) {
            Text(
                text = stringResource(id = R.string.take_money),
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.exo)),
                    fontWeight = FontWeight(400),
                    color = white
                )
            )
        }
    }
}


@Composable
fun ItemData(
    modifier: Modifier = Modifier,
    name: String,
    content: String
) {
    Column(
        modifier = modifier.height(75.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 21.sp,
                fontFamily = FontFamily(Font(R.font.exo)),
                fontWeight = FontWeight(400),
                color = baseText
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(30.dp))
        Text(
            text = content,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.exo)),
                fontWeight = FontWeight(400),
                color = greyText
            ),
            textAlign = TextAlign.Center
        )
    }
}
@Preview
@Composable
fun Sample() {
    ItemLoan(
        loan = Loan(
            age = "18 лет",
            imageUrl = "https://otkrit-ka.ru/uploads/posts/2021-11/krasivye-foto-kartinki-ljagushki-1.jpg",
            percent = "1 %",
            sumOne = "30 000 ",
            url = ""
        )
    )
}

@Preview
@Composable
fun Sample2() {
    ItemData(
        name = "30 000 ",
        content = stringResource(id = R.string.amount)
    )
}