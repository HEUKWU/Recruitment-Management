package heukwu.recruitmentmanagement.entity;

import heukwu.recruitmentmanagement.dto.PostDto;
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
public class Post {

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
    public Post(Long id, String position, String skill, String description, Company company) {
        this.id = id;
        this.position = position;
        this.skill = skill;
        this.description = description;
        this.company = company;
    }

    public static Post of(Company company, PostDto.Req requestDto) {
        return Post.builder()
                .company(company)
                .position(requestDto.getPosition())
                .skill(requestDto.getSkill())
                .description(requestDto.getDescription())
                .build();
    }

    public Post edit(PostDto.Req editDto) {
        this.position = editDto.getPosition();
        this.skill = editDto.getSkill();
        this.description = editDto.getDescription();

        return this;
    }
}
