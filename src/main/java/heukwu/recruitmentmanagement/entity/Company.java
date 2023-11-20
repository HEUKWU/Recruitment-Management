package heukwu.recruitmentmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
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
    private List<Post> postList;
}
