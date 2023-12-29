package heukwu.recruitmentmanagement.repository;

import heukwu.recruitmentmanagement.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
