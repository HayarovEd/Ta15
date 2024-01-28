package zppdenga.creditpluss.domain.repository

import zppdenga.creditpluss.domain.model.Loan
import zppdenga.creditpluss.domain.utils.Resource

interface TaRepository {
    suspend fun getRemoteData(): Resource<List<Loan>>
}