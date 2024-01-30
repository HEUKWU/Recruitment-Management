package heukwu.recruitmentmanagement.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import heukwu.recruitmentmanagement.post.controller.PostSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static heukwu.recruitmentmanagement.post.repository.QPostEntity.postEntity;

public class PostRepositoryCustomImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(PostEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<PostEntity> findBySearchOption(Pageable pageable, PostSearch search) {
        JPAQuery<PostEntity> query = jpaQueryFactory.selectFrom(postEntity).where(containPosition(search.position()), containSkill(search.skill()));
        List<PostEntity> postEntities = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(postEntities, pageable, query.stream().count());
    }

    private BooleanExpression containPosition(String position) {
        if (position == null || position.isEmpty()) {
            return null;
        }

        return postEntity.position.containsIgnoreCase(position);
    }

    private BooleanExpression containSkill(String skill) {
        if (skill == null || skill.isEmpty()) {
            return null;
        }

        return postEntity.skill.containsIgnoreCase(skill);
    }
}
