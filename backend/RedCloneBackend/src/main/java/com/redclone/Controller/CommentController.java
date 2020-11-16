package com.redclone.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redclone.objects.CommentDto;
import com.redclone.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@PostMapping
	public ResponseEntity<Void> createComment(@RequestBody CommentDto commentRequest) throws Exception
	{
		commentService.save(commentRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CommentDto>> getAllComments()
	{
		return new ResponseEntity<List<CommentDto>>(commentService.getAllComments(),HttpStatus.OK);
	}
	
	@GetMapping("/byUser/{username}")
	public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable String username) throws Exception
	{
		return new ResponseEntity<List<CommentDto>>(commentService.getAllCommentsForUser(username),HttpStatus.OK);
	}
	
	@GetMapping("/byPost/{postId}")
	public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Long postId) throws Exception
	{
		return new ResponseEntity<List<CommentDto>>(commentService.getAllCommentsForPost(postId),HttpStatus.OK);
	}
}
