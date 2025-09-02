package fr.benchaabane.riyadhair.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.encodedPath
        val json = context.assets.open(
             when {
                uri.contains("flights") -> "mock/flights.json"
                uri.contains("reservations") -> "mock/reservations.json"
                uri.contains("account/profile") -> "mock/account.json"
                uri.contains("offers/best") -> "mock/best-offers.json"
                uri.contains("partners") -> "mock/partners.json"
                else -> "{}"
            }
        ).bufferedReader().use { it.readText() }
        return Response.Builder()
            .code(200)
            .message(json)
            .request(chain.request())
            .protocol(Protocol.HTTP_3)
            .body(json.toResponseBody("application/json".toMediaType()))
            .addHeader("content-type", "application/json")
            .build()
    }
}


