package heukwu.recruitmentmanagement.apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<ApplyEntity, Long> {

    Optional<ApplyEntity> findApplyByUserIdAndPostId(Long userId, Long postId);
}
