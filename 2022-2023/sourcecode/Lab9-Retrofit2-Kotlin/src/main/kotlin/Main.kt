import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun main(args: Array<String>) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(PlaceholderApi::class.java)

    val call: Call<List<Post>> = api.posts()

    call.enqueue(object : Callback<List<Post>> {
        override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
            if (response.isSuccessful) {
                val posts = response.body()
                posts?.forEach {
                    val content = StringBuilder()
                    content.append("id: ").append(it.id).append("\n")
                        .append("UserId: ").append(it.userId).append("\n")
                        .append("title: ").append(it.title).append("\n")
                        .append("body: ").append(it.title).append("\n\n")
                    println(content)
                }
            } else {
                println("fail")
            }
        }

        override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
            println("error")
        }
    })
}