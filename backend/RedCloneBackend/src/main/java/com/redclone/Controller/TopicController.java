package com.redclone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redclone.model.Topic;
import com.redclone.objects.TopicDto;
import com.redclone.service.TopicService;
@RestController
@RequestMapping("/api/topic")
public class TopicController {
	@Autowired
	private TopicService topicService;
	@PostMapping
	public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto request)
	{
		return new ResponseEntity<TopicDto>(topicService.save(request),HttpStatus.CREATED);
		
	}
	@GetMapping
	public ResponseEntity<List<TopicDto>>  getAllTopics()
	{
		return new ResponseEntity<List<TopicDto>>(topicService.getAllTopics(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicDto> getTopicById(@PathVariable Long id) throws Exception
	{
		return new ResponseEntity<TopicDto>(topicService.getTopic(id),HttpStatus.OK);
	}
}
