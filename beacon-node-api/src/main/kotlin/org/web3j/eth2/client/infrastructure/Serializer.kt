package org.web3j.eth2.client.infrastructure

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.reflect.KotlinJsonAdapterFactory
import java.util.Date

object Serializer {
    @JvmStatic
    val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(LocalDateTimeAdapter())
            .add(LocalDateAdapter())
            .build()
}