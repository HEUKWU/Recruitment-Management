package heukwu.recruitmentmanagement.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<PostEntity> findBySearchOption(Pageable pageable, String position, String skill);
}
