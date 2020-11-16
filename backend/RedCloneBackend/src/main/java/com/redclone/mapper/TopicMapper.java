package com.redclone.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.redclone.model.Post;
import com.redclone.model.Topic;
import com.redclone.objects.TopicDto;


@Mapper(componentModel = "spring")
public interface TopicMapper {

	@Mapping(target="numOfPosts",expression="java(mapPosts(topic.getPosts()))")
	TopicDto mapTopictoDTO(Topic topic);
	
	default Integer mapPosts(List<Post> posts)
	{
		return posts.size();
	}
	
	@InheritInverseConfiguration
	@Mapping(target="posts",ignore=true)
	Topic mapDtoToTopic(TopicDto topicdto);
}
