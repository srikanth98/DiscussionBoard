package com.redclone.service;

import java.util.List;


import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Import all 

import com.redclone.model.*;
import com.redclone.objects.*;
import com.redclone.repository.*;
import com.redclone.service.*;
import com.redclone.mapper.*;


@Service
public class CommentService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private CommentMapper commentMap;
	@Autowired
	private AuthService authService;
	
	
	public void save(CommentDto commentRequest) throws Exception {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(commentRequest.getPostId()).orElseThrow(()->new Exception("Unable to find the post"));
		Comment comment = commentMap.map(commentRequest,authService.getCurrentUser(),post);
		commentRepo.save(comment);
		//Todo : Send notification of comment.
	}


    public List<CommentDto> getAllCommentsForPost(Long postId) throws Exception {
        Post post = postRepo.findById(postId).orElseThrow(() -> new Exception("Unable to find post"+postId.toString()));
        return commentRepo.findByPost(post).orElseThrow(()->new Exception("Unable to find comments for post "+post.toString()))
                .stream()
                .map(commentMap::maptoDto).collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentsForUser(String userName) throws Exception {
        User user = userRepo.findByUsername(userName)
                .orElseThrow(() -> new Exception(userName));
        return commentRepo.findAllByUser(user).orElseThrow(()-> new Exception("Unable to find comment by user"))
                .stream()
                .map(commentMap::maptoDto)
                .collect(Collectors.toList());
    }


	public List<CommentDto> getAllComments() {
		// TODO Auto-generated method stub
		return commentRepo.findAll().stream().map(commentMap::maptoDto).collect(Collectors.toList());
	}

	
	

}
