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