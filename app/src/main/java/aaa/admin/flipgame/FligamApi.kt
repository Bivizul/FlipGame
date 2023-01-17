package aaa.admin.flipgame

import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FligamApi {

    @POST("games/FlipGame/fligam.php")
    suspend fun getFligamg(
        @Body fligam: Fligam
    ): Response<Fligamg>

    @POST("games/FlipGame/fligamr.php")
    suspend fun setFligamr(@Body fligamr: JSONObject)

}