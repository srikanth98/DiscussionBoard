package com.redclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.redclone.model.Comment;
import com.redclone.model.Post;
import com.redclone.model.User;
import com.redclone.objects.CommentDto;


@Mapper(componentModel="spring")
public abstract class CommentMapper {
	@Mapping(target="id",ignore=true)
	@Mapping(target="user",source="user")
	@Mapping(target="post",source="post")
	@Mapping(target="text",source="commentDto.text")
	@Mapping(target="createdDate",expression="java(java.time.Instant.now())")
	public abstract Comment map(CommentDto commentDto,User user,Post post);
	
	@Mapping(target="postId",expression="java(comment.getPost().getPostId())")
	@Mapping(target="userName",expression="java(comment.getUser().getUsername())")
	@Mapping(target="createdDate",expression="java(comment.getCreatedDate().toString())")
	public abstract CommentDto maptoDto(Comment comment);
		
}
