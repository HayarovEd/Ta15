package zppdenga.ruonlinersx.domain.repository

import zppdenga.ruonlinersx.domain.model.Loan
import zppdenga.ruonlinersx.domain.utils.Resource

interface TaRepository {
    suspend fun getRemoteData(): Resource<List<Loan>>
}