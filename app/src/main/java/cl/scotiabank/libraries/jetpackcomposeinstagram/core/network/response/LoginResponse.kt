package cl.scotiabank.libraries.jetpackcomposeinstagram.core.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("success") val success: Boolean)
