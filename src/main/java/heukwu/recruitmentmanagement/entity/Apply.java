package heukwu.recruitmentmanagement.entity;

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
