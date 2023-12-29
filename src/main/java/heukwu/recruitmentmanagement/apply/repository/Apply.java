package heukwu.recruitmentmanagement.apply.repository;

import heukwu.recruitmentmanagement.user.repository.User;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import jakarta.persistence.*;

@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PostEntity postEntity;

    @ManyToOne
    private User user;
}
