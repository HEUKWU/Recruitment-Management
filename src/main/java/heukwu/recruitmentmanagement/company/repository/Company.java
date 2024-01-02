package heukwu.recruitmentmanagement.company.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "postIds", joinColumns = @JoinColumn(name = "companyId", referencedColumnName = "id"))
    private List<Long> postIds = new ArrayList<>();

    public void add(Long postId) {
        postIds.add(postId);
    }

    public void removePost(Long postId) {
        postIds.remove(postId);
    }
}
