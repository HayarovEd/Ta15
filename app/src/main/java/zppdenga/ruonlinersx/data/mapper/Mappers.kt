package zppdenga.ruonlinersx.data.mapper

import zppdenga.ruonlinersx.data.remote.LoanDto
import zppdenga.ruonlinersx.domain.model.Loan

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
        (this.substring(3,5)+this.substring(6)).toInt()
    } catch (e:Exception) {
        1000000
    }
}

fun maxAmount(data:List<Loan>): Int {
    return data.maxOf { it.sumOneInt }
}