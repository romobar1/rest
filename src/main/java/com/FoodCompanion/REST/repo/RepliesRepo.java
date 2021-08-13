package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.Post;
import com.FoodCompanion.REST.model.Replies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepliesRepo extends JpaRepository<Replies, Long> {

    void deleteRepliesById(Long id);

    Optional<Replies> findRepliesById(Long id);
}
