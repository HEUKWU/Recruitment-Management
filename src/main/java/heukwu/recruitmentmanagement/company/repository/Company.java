package heukwu.recruitmentmanagement.company.repository;

import heukwu.recruitmentmanagement.post.repository.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "company")
    private List<PostEntity> postEntityList;
}
