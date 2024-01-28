package zppdenga.creditpluss.domain.model

import zppdenga.creditpluss.data.mapper.convertToInt

data class Loan(
    val age: String,
    val imageUrl: String,
    val percent: String,
    val sumOne: String,
    val url: String
) {
    val sumOneInt = sumOne.convertToInt()
}
