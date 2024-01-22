package heukwu.recruitmentmanagement.apply.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ApplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private Long userId;

    @Builder
    public ApplyEntity(Long id, Long postId, Long userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }
}
