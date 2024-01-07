package heukwu.recruitmentmanagement.post.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE post SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String skill;

    @Column(nullable = false)
    private String description;

    private Boolean deleted = Boolean.FALSE;

    private Long companyId;

    @Builder
    public PostEntity(Long id, String position, String skill, String description, Long companyId) {
        this.id = id;
        this.position = position;
        this.skill = skill;
        this.description = description;
        this.companyId = companyId;
    }

    public PostEntity edit(PostEntityUpdatePolicy updatePolicy) {
        this.position = updatePolicy.position();
        this.skill = updatePolicy.skill();
        this.description = updatePolicy.description();

        return this;
    }
}
