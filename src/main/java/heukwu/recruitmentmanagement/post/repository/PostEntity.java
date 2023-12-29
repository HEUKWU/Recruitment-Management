package heukwu.recruitmentmanagement.post.repository;

import heukwu.recruitmentmanagement.apply.repository.Apply;
import heukwu.recruitmentmanagement.company.repository.Company;
import heukwu.recruitmentmanagement.post.service.PostDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

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

    @ManyToOne
    private Company company;

    @OneToMany
    private List<Apply> applyList;

    @Builder
    public PostEntity(Long id, String position, String skill, String description, Company company) {
        this.id = id;
        this.position = position;
        this.skill = skill;
        this.description = description;
        this.company = company;
    }

    public PostEntity edit(PostDto.Req editDto) {
        this.position = editDto.getPosition();
        this.skill = editDto.getSkill();
        this.description = editDto.getDescription();

        return this;
    }
}
