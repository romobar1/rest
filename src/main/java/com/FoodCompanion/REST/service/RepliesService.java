package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.ReplyNotFoundException;
import com.FoodCompanion.REST.model.Replies;
import com.FoodCompanion.REST.repo.RepliesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepliesService {
    public final RepliesRepo repliesRepo;
    @Autowired
    public RepliesService(RepliesRepo repliesRepo){
        this.repliesRepo = repliesRepo;
    }

    public Replies addReplies(Replies replies){
        return repliesRepo.save(replies);
    }

    public List<Replies> getAllReplies(){
        return repliesRepo.findAll();
    }

    public Replies updateResply(Replies reply){
        return repliesRepo.save(reply);
    }
    public void deleteReply(Long id){
        repliesRepo.deleteRepliesById(id);
    }

    public Replies findReplyById(Long id){
        return repliesRepo.findRepliesById(id).orElseThrow(()->
                new ReplyNotFoundException("La respuesta con el id " + id + " no existe."));
    }

    public List<Replies> getRepliesFromPost(Long id) {
        return repliesRepo.findRepliesFromPostById(id);
    }
}
