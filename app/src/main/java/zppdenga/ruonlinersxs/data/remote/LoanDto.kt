package zppdenga.ruonlinersxs.data.remote


import com.google.gson.annotations.SerializedName

data class LoanDto(
    @SerializedName("aage")
    val aage: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("percent")
    val percent: String,
    @SerializedName("sum_one")
    val sumOne: String,
    @SerializedName("url")
    val url: String
)