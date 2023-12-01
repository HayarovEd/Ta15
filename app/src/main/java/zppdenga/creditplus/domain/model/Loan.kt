package zppdenga.creditplus.domain.model

import zppdenga.creditplus.data.mapper.convertToInt

data class Loan(
    val age: String,
    val imageUrl: String,
    val percent: String,
    val sumOne: String,
    val url: String
) {
    val sumOneInt = sumOne.convertToInt()
}
