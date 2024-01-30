package heukwu.recruitmentmanagement.apply.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApplyEntity is a Querydsl query type for ApplyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplyEntity extends EntityPathBase<ApplyEntity> {

    private static final long serialVersionUID = -1530809863L;

    public static final QApplyEntity applyEntity = new QApplyEntity("applyEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QApplyEntity(String variable) {
        super(ApplyEntity.class, forVariable(variable));
    }

    public QApplyEntity(Path<? extends ApplyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplyEntity(PathMetadata metadata) {
        super(ApplyEntity.class, metadata);
    }

}

