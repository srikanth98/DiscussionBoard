package com.redclone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redclone.model.Post;
import com.redclone.model.Topic;
import com.redclone.model.User;
@Repository
public interface PostRepo extends JpaRepository<Post,Long> {

	List<Post> findByUser(User user);
	List<Post> findByTopic(Topic topic);
}
