package com.redclone.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redclone.objects.TopicDto;
import com.redclone.repository.TopicRepo;
import com.redclone.mapper.TopicMapper;
import com.redclone.model.Topic;
@Service
public class TopicService {

	@Autowired
	private TopicRepo subRepo;
	@Autowired
	private TopicMapper topicmapper;
	@Transactional
	public TopicDto save(TopicDto subRedReq)
	{
		Topic savedTopic=subRepo.save(topicmapper.mapDtoToTopic(subRedReq));
		subRedReq.setId(savedTopic.getId());
		return subRedReq;
		
	}
	
	
	@Transactional
	public List<TopicDto> getAllTopics() {
		// TODO Auto-generated method stub
		return subRepo.findAll().stream().map(topicmapper::mapTopictoDTO).collect(Collectors.toList());
		
	}


	public TopicDto getTopic(Long id) throws Exception {
		// TODO Auto-generated method stub
		Topic topic = subRepo.findById(id).orElseThrow(() ->
		 new Exception("Error getting the topic"));
		return topicmapper.mapTopictoDTO(topic);
	}
	
	
	
}
