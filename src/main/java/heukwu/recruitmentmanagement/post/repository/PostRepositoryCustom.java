package heukwu.recruitmentmanagement.post.repository;

import heukwu.recruitmentmanagement.post.controller.PostSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<PostEntity> findBySearchOption(Pageable pageable, PostSearch search);
}
