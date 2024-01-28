package zppdenga.creditpluss.data.repository

import zppdenga.creditpluss.data.mapper.mapToLoan
import zppdenga.creditpluss.data.remote.ApiTa
import zppdenga.creditpluss.domain.model.Loan
import zppdenga.creditpluss.domain.repository.TaRepository
import zppdenga.creditpluss.domain.utils.Resource
import javax.inject.Inject

class TaRepositoryImpl @Inject constructor(private val apiTa: ApiTa) : TaRepository {
    override suspend fun getRemoteData(): Resource<List<Loan>> {
        return try {
            val result = apiTa.getData()
            Resource.Success(
                result.mapToLoan()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}