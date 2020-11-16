package com.redclone.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redclone.mapper.PostMapper;
import com.redclone.model.Post;
import com.redclone.model.PostResponse;
import com.redclone.model.Topic;
import com.redclone.model.User;
import com.redclone.objects.CommentDto;
import com.redclone.objects.PostRequest;
import com.redclone.repository.PostRepo;
import com.redclone.repository.TopicRepo;
import com.redclone.repository.UserRepo;

@Transactional
@Service
public class PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthService authService;
	@Autowired
	private TopicRepo subRedRepo;
	@Autowired
	private PostMapper postMap;
	public void createPost(PostRequest postRequest) throws Exception {
		// TODO Auto-generated method stub
		Topic topic = subRedRepo.findByName(postRequest.getTopicName()).orElseThrow(()->new Exception("Unable to find topic "));
		User currUser = authService.getCurrentUser();
		Post post = postMap.map(postRequest, topic, currUser);
		postRepo.save(post);
	}

	@Transactional(readOnly=true)
	public PostResponse getPost(Long id) throws Exception {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(id).orElseThrow(()->new Exception("Unable to find post by id "+id));
		return postMap.mapToDto(post);
	}
	@Transactional(readOnly=true)
	public List<PostResponse> getAllPosts() throws Exception{
		// TODO Auto-generated method stub
		return postRepo.findAll().stream().map(postMap::mapToDto).collect(Collectors.toList());
	}

	@Transactional(readOnly=true)
	public List<PostResponse> getPostsByUser(String name) {
		// TODO Auto-generated method stub
		List<PostResponse> posts = null;
		User user=null;
		try {
			user = userRepo.findByUsername(name).orElseThrow(()->new Exception("Unable to find user "+name));
		
				posts = postRepo.findByUser(user).stream().map(postMap::mapToDto).collect(Collectors.toList());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return posts;
		
	
		//return null;
	}
	
	@Transactional(readOnly=true)
	public List<PostResponse> getPostsByTopic(Long id) throws Exception
	{
		try
		{
		Topic topic = subRedRepo.findById(id).orElseThrow(()->new Exception("Unable to find the topic "+id));
		return postRepo.findByTopic(topic).stream().map(postMap::mapToDto).collect(Collectors.toList());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
	}

	public void deletePost(Long id) {
		// TODO Auto-generated method stub
	   
		postRepo.deleteById(id);
		
	}

	
	
	
	

	
}
