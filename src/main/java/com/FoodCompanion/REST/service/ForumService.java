package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.ForumNotFoundException;
import com.FoodCompanion.REST.model.Forum;
import com.FoodCompanion.REST.repo.ForumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {
    private final ForumRepo forumRepo;
    @Autowired
    public ForumService (ForumRepo forumRepo){
        this.forumRepo = forumRepo;
    }

    public Forum addForum(Forum forum){
        return forumRepo.save(forum);
    }

    public List<Forum> getAllForums(){
        return forumRepo.findAll();
    }

    public void deleteForum(Long id){
        forumRepo.deleteForumById(id);
    }

    public Forum updateForum(Forum forum){
        return forumRepo.save(forum);
    }

    public Forum findForumById(Long id){
        return forumRepo.findForumById(id).orElseThrow(()->
                new ForumNotFoundException("El foro con el id " + id + " no existe."));
    }


}
