package zppdenga.creditpluss.data.remote

import retrofit2.http.GET

interface ApiTa {
    @GET("alexofyou/navashygrnn.nakarty/main/dbvb.json")
    suspend fun getData() : List<LoanDto>
}