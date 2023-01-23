fun main(args: Array<String>) {
    val controller = PostController()
    val view = PostView()

    val posts = controller.posts
    view.displayPosts(posts)
}