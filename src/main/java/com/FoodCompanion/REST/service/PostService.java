package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.PostNotFoundException;
import com.FoodCompanion.REST.model.Post;
import com.FoodCompanion.REST.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService {

    private final PostRepo postRepo;
    @Autowired
    public PostService(PostRepo postRepo){
        this.postRepo = postRepo;
    }

    public Post addPost(Post post){
        return postRepo.save(post);
    }
    public List<Post> getAllPosts(){
        return postRepo.findAll();
    }
    public Post updatePost(Post post){
        return postRepo.save(post);
    }
    public void deletePost(Long id){
        postRepo.deletePostById(id);
    }
    public Post findPostById(Long id){
        return postRepo.findPostById(id).orElseThrow(()->
                new PostNotFoundException("El post con el id " + id + " no existe."));
    }

    public List<Post> getPostsFromUser(Long id) {
        return postRepo.findPostFromUserById(id);
    }

    public List<Post> getPostsFromForum(Long id) {
        return postRepo.findPostFromForumById(id);
    }
}
