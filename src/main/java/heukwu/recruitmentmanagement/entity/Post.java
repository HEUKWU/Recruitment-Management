package heukwu.recruitmentmanagement.entity;

import heukwu.recruitmentmanagement.dto.PostDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    @ManyToOne
    private Company company;

    @OneToMany
    private List<Apply> applyList;

    @Builder
    public Post(String position, String skill, String description, Company company) {
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
}
