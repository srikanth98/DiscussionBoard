package com.redclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.redclone.model.Post;
import com.redclone.model.User;
import com.redclone.model.Vote;
import java.util.Optional;
@Repository
public interface VoteRepo extends JpaRepository<Vote,Long> {

	Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
