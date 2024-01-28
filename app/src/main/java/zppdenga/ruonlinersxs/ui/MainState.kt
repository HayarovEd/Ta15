package zppdenga.ruonlinersxs.ui

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import zppdenga.ruonlinersxs.data.mapper.setTime
import zppdenga.ruonlinersxs.domain.model.Loan

@RequiresApi(VERSION_CODES.O)
data class MainState  constructor(
    val moneyList: List<Loan> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = true,
    val neededSum: Int = 0,
    val maxSum:Int = 0,
    val time: String = setTime()
)
