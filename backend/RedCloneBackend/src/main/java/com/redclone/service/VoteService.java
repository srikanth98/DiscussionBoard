package com.redclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redclone.mapper.VoteMapper;
import com.redclone.model.Post;
import com.redclone.model.Vote;
import com.redclone.model.VoteType;
import com.redclone.objects.VoteDto;
import com.redclone.repository.PostRepo;
import com.redclone.repository.VoteRepo;
import java.util.Optional;

@Service
public class VoteService {

	@Autowired
	private VoteRepo voteRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private AuthService authService;

	@Transactional
	public void vote(VoteDto voteDto) throws Exception
	{
		Post post = postRepo.findById(voteDto.getPostId()).orElseThrow(()->new Exception("Unable to find vote by id "+voteDto.getPostId()));
		Optional<Vote> voteByPostAndUser = voteRepo.findTopByPostAndUserOrderByVoteIdDesc(post,authService.getCurrentUser());
		if(voteByPostAndUser.isPresent()&& voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType()))
		{
			throw new Exception("Already voted!");
		}
	   if(VoteType.UPVOTE==voteDto.getVoteType())
	   {
		   post.setVoteCount(post.getVoteCount()+1);
	   }
	   else
	   {
		   post.setVoteCount(post.getVoteCount()-1);
	   }
		voteRepo.save(mapToVote(voteDto,post));
		postRepo.save(post);
		
	}
	private Vote mapToVote(VoteDto voteDto, Post post) throws Exception {
		// TODO Auto-generated method stub
		Vote vote = new Vote();
		vote.setPost(post);
		vote.setUser(authService.getCurrentUser());
		vote.setVoteType(voteDto.getVoteType());
		return vote;
	}
}
