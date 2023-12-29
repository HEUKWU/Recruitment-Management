package heukwu.recruitmentmanagement.apply.repository;

import heukwu.recruitmentmanagement.user.repository.User;
import heukwu.recruitmentmanagement.post.repository.Post;
import jakarta.persistence.*;

@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
