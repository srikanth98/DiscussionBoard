package com.redclone.Controller;

import java.util.List;




import javax.mail.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redclone.model.PostResponse;
import com.redclone.objects.PostRequest;
import com.redclone.service.PostService;
@RestController
@RequestMapping("/api/posts")
public class PostController 
{  
	
	@Autowired
	private PostService postService;
	@PostMapping
	public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) throws Exception
	{
		 postService.createPost(postRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@DeleteMapping("/byPost/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) throws Exception
	{
		postService.deletePost(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/byPost/{id}")
	public PostResponse getPost(@PathVariable Long id) throws Exception
	{
		return postService.getPost(id);	
	}
	
	@GetMapping("/")
	public List<PostResponse> getAllPosts() throws Exception
	{
		return postService.getAllPosts();
	}
	
	@GetMapping("/byUser/{name}")
	public List<PostResponse> getPostByUser(@PathVariable String name) throws Exception
	{
		return postService.getPostsByUser(name);
	}
	
	@GetMapping("/byTopic/{id}")
	public List<PostResponse> getPostsByTopic(@PathVariable Long id) throws Exception
	{
		return postService.getPostsByTopic(id);
	}
}
