package org.example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        PlaceholderApi api = retrofit.create(PlaceholderApi.class);

        Call<List<Post>> call = api.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(
                    Call<List<Post>> call,
                    Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    if (posts != null) {
                        posts.forEach(post -> {
                            StringBuilder content = new StringBuilder();
                            content
                                    .append("id: ")
                                    .append(post.getId())
                                    .append("\n")
                                    .append("UserId: ")
                                    .append(post.getUserId())
                                    .append("\n")
                                    .append("title: ")
                                    .append(post.getTitle())
                                    .append("\n")
                                    .append("body: ")
                                    .append(post.getContent())
                                    .append("\n\n");
                            System.out.println(content);
                        });
                    }
                } else {
                    System.out.println("fail");
                }
            }

            @Override
            public void onFailure(
                    Call<List<Post>> call,
                    Throwable t) {
                System.out.println("error");
            }
        });
    }
}