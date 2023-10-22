package zppdenga.ruonlinersx.ui

import zppdenga.ruonlinersx.domain.model.Loan


data class MainState(
    val moneyList: List<Loan> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = true,
    val neededSum: Int = 0,
    val maxSum:Int = 0
)
