package heukwu.recruitmentmanagement.service;

import heukwu.recruitmentmanagement.dto.PostDto;
import heukwu.recruitmentmanagement.entity.Company;
import heukwu.recruitmentmanagement.entity.Post;
import heukwu.recruitmentmanagement.repository.CompanyRepository;
import heukwu.recruitmentmanagement.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = Replace.ANY)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CompanyRepository companyRepository;

    Company company;

    @BeforeEach
    void before() {
        Company companyOf = Company.builder()
                .id(1L)
                .country("한국")
                .companyName("네이버")
                .location("판교")
                .build();

        company = companyRepository.save(companyOf);
    }

    @Test
    void test() {
        PostDto.Req requestDto = PostDto.Req.builder()
                .skill("java")
                .position("backend")
                .description("hi")
                .build();

        Post post = Post.of(new Company(), requestDto);

        Post savePost = postRepository.save(post);

        assertThat(post.getId()).isEqualTo(savePost.getId());
    }
}