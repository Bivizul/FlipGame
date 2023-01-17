package aaa.admin.flipgame

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Fligamnet {

    fun fligamnet(): FligamApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://65.109.10.118/")
            .build()
        return retrofit.create(FligamApi::class.java)
    }
}