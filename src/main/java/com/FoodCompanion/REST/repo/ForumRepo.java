package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForumRepo extends JpaRepository<Forum, Long> {
    void deleteForumById(Long id);

    Optional<Forum> findForumById(Long id);
}
