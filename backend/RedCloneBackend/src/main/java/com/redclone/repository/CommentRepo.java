package com.redclone.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redclone.model.Comment;
import com.redclone.model.Post;
import com.redclone.model.User;
@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {

	Optional<List<Comment>> findByPost(Post post);

	Optional<List<Comment>> findAllByUser(User user);

}
