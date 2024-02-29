package heukwu.recruitmentmanagement.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import heukwu.recruitmentmanagement.post.controller.PostSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static heukwu.recruitmentmanagement.post.repository.QPostEntity.postEntity;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<PostEntity> findBySearchOption(Long cursorId, PostSearch search, Pageable pageable) {
        List<Long> ids = jpaQueryFactory
                .select(postEntity.id)
                .from(postEntity)
                .where(containPosition(search), containSkill(search), eqCursorId(cursorId))
                .limit(pageable.getPageSize() + 1)
                .fetch();

        List<PostEntity> postEntities = jpaQueryFactory.selectFrom(postEntity)
                .where(postEntity.id.in(ids))
                .fetch();

        boolean hasNext = false;
        if (postEntities.size() > pageable.getPageSize()) {
            postEntities.remove(pageable.getPageSize());

            hasNext = true;
        }

        return new SliceImpl<>(postEntities, pageable, hasNext);
    }

    private BooleanExpression containPosition(PostSearch search) {
        if (search == null || search.position() == null || search.position().isEmpty()) {
            return null;
        }

        return postEntity.position.containsIgnoreCase(search.position());
    }

    private BooleanExpression containSkill(PostSearch search) {
        if (search == null || search.skill() == null || search.skill().isEmpty()) {
            return null;
        }

        return postEntity.skill.containsIgnoreCase(search.skill());
    }

    private BooleanExpression eqCursorId(Long cursorId) {
        if (cursorId == null) {
            return null;
        }

        return postEntity.id.gt(cursorId);
    }
}
