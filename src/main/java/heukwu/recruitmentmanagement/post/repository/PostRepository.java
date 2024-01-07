package heukwu.recruitmentmanagement.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByCompanyId(long CompanyId);
}
