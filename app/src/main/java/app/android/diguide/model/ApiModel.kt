package app.android.diguide.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ApiModel(
    @SerializedName("count") val count: Int?,
    @SerializedName("entries") val entries: List<Entry?>?
) {
    @Keep
    data class Entry(
        @SerializedName("API") val aPI: String?,
        @SerializedName("Auth") val auth: String?,
        @SerializedName("Category") val category: String?,
        @SerializedName("Cors") val cors: String?,
        @SerializedName("Description") val description: String?,
        @SerializedName("HTTPS") val hTTPS: Boolean?,
        @SerializedName("Link") val link: String?
    )
}