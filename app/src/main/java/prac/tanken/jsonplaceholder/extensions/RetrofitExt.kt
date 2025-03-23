package prac.tanken.jsonplaceholder.extensions

import retrofit2.Retrofit

inline fun <reified K> Retrofit.create(): K = create(K::class.java)