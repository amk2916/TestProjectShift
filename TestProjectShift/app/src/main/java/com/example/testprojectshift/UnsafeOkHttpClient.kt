package com.example.testprojectshift

import okhttp3.OkHttpClient
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.X509Certificate


object UnsafeOkHttpClient {
    fun getUnsafeOkHttpClient(): OkHttpClient? {
        return try {
            // Create a trust manager that does not validate certificate chains


            val trustAllCerts: Array<TrustManager> = arrayOf(
                object : X509TrustManager {

                    val acceptedIssuers: Array<X509Certificate?>
                        get() = arrayOf()

                    override fun checkClientTrusted(
                        p0: Array<out java.security.cert.X509Certificate>?,
                        p1: String?
                    ) {

                    }

                    override fun checkServerTrusted(
                        p0: Array<out java.security.cert.X509Certificate>?,
                        p1: String?
                    ) {

                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return emptyArray()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}