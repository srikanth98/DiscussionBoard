package com.redclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redclone.model.Topic;
@Repository
public interface TopicRepo extends JpaRepository<Topic,Long> {

	Optional<Topic> findByName(String name);
}

