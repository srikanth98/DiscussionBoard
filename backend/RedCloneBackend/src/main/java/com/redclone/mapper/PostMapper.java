package com.redclone.mapper;

import java.util.List;
import java.util.Optional;



import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.redclone.model.Comment;
import com.redclone.model.Post;
import com.redclone.model.PostResponse;
import com.redclone.model.Topic;
import com.redclone.model.User;
import com.redclone.objects.PostRequest;
import com.redclone.repository.CommentRepo;
import com.redclone.repository.VoteRepo;
import com.redclone.service.AuthService;



@Mapper(componentModel="spring")
public abstract class PostMapper {
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private VoteRepo voteRepo;
	@Autowired
	private AuthService authService;
	
	@Mapping(target="createdDate", expression = "java(java.time.Instant.now())")
	@Mapping(target="description",source="postRequest.description")
	@Mapping(target="user",source="user")
	@Mapping(target="topic",source="topic")
	@Mapping(target="voteCount",constant="0")
	public abstract Post map(PostRequest postRequest,Topic topic,User user);
	
	@Mapping(target="id" ,source="postId")
	@Mapping(target="postName" ,source="postName")
	@Mapping(target="description", source="description")
	@Mapping(target="url" ,source="url")
	@Mapping(target="topicName" ,source="topic.name")
	@Mapping(target="userName" ,source="user.username")
	@Mapping(target="commentCount",expression="java(commentCount(post))")
	@Mapping(target="duration",expression="java(getDuration(post))")
	public abstract PostResponse mapToDto(Post post);
	
	Integer commentCount(Post post) 
	{
		try {
		Optional<List<Comment>> commentList = commentRepo.findByPost(post);
		if(commentList.isPresent())
		{
			return commentList.get().size();
		}
		else return 0;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	String getDuration(Post post)
	{
		//Todo
		return post.getCreatedDate().toString();
		
	}
	
}
