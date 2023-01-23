import kotlinx.coroutines.*

class PostController {
    private val jsonPlaceholderApi: JsonPlaceholderApi =
        RetrofitInstance.retrofit.create(JsonPlaceholderApi::class.java)

    private var _posts: MutableList<Post> = getAllPosts() as MutableList<Post>
    val posts: List<Post>
        get() = _posts

    private fun getAllPosts(): List<Post> {
        return runBlocking {
            async {
                jsonPlaceholderApi.getPosts()
            }.await()
        }
    }
}