package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.Post;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;

public interface PostRepo extends JpaRepository<Post, Long> {
    void deletePostById(Long id);

    Optional<Post> findPostById(Long id);

    List<Post> findPostFromUserById(Long id);

    List<Post> findPostFromForumById(Long id);
}
