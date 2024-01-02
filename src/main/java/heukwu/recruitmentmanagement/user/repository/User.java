package heukwu.recruitmentmanagement.user.repository;

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

    @ElementCollection
    @CollectionTable(name = "applyIds", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
    private List<Long> applyIds;
}
