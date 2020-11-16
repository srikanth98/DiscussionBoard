package com.redclone.objects;

import com.redclone.model.VoteType;

public class VoteDto {
	
	private VoteType voteType;
    private Long postId;
    
    
    public VoteDto()
    {
    	super();
    }
    
	public VoteDto(VoteType voteType, Long postId) {
		super();
		this.voteType = voteType;
		this.postId = postId;
	}
	public VoteType getVoteType() {
		return voteType;
	}
	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
    
    
}
