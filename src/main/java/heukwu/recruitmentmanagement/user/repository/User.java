package heukwu.recruitmentmanagement.user.repository;

import heukwu.recruitmentmanagement.apply.repository.Apply;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Apply> applyList;
}
