package heukwu.recruitmentmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
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
}
