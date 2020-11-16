package com.redclone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redclone.objects.VoteDto;
import com.redclone.service.VoteService;

import io.swagger.annotations.Api;
@RestController
@RequestMapping("/api/votes")
@Api
public class VotesController {
	@Autowired
	private VoteService voteService;
	
	@PostMapping("")
	public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) throws Exception
	{
		voteService.vote(voteDto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
