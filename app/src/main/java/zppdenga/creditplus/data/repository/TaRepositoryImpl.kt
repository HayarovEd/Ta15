package zppdenga.creditplus.data.repository

import zppdenga.creditplus.data.mapper.mapToLoan
import zppdenga.creditplus.data.remote.ApiTa
import zppdenga.creditplus.domain.model.Loan
import zppdenga.creditplus.domain.repository.TaRepository
import zppdenga.creditplus.domain.utils.Resource
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