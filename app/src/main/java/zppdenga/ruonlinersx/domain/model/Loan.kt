package zppdenga.ruonlinersx.domain.model

import zppdenga.ruonlinersx.data.mapper.convertToInt

data class Loan(
    val age: String,
    val imageUrl: String,
    val percent: String,
    val sumOne: String,
    val url: String
) {
    val sumOneInt = sumOne.convertToInt()
}
