package heukwu.recruitmentmanagement.post.repository;

import heukwu.recruitmentmanagement.post.controller.PostSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {
    Slice<PostEntity> findBySearchOption(Long cursorId, PostSearch search, Pageable pageable);
}
