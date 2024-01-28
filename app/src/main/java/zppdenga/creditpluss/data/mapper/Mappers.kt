package zppdenga.creditpluss.data.mapper

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import zppdenga.creditpluss.data.remote.LoanDto
import zppdenga.creditpluss.domain.model.Loan

fun List<LoanDto>.mapToLoan(): List<Loan> {
    return this.map {
        Loan(
            imageUrl = it.imageUrl,
            percent = it.percent,
            sumOne = it.sumOne,
            url = it.url,
            age = it.aage,
        )
    }
}

fun String.convertToInt(): Int {
    return try {
        (this.substring(3, 5) + this.substring(6)).toInt()
    } catch (e: Exception) {
        1000000
    }
}

fun maxAmount(data: List<Loan>): Int {
    return data.maxOf { it.sumOneInt }
}

@RequiresApi(VERSION_CODES.O)
fun setTime(): String {
    val localTime = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return localTime
        .plusMinutes(30)
        .format(formatter)
}