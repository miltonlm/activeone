package com.activeone.posts.commentaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentariesRepository extends JpaRepository<CommentariesEntity, Integer> {

}
