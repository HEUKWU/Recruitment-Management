package heukwu.recruitmentmanagement.service;

import heukwu.recruitmentmanagement.dto.PostDto;
import heukwu.recruitmentmanagement.entity.Company;
import heukwu.recruitmentmanagement.entity.Post;
import heukwu.recruitmentmanagement.repository.CompanyRepository;
import heukwu.recruitmentmanagement.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    private Company naver;
    private Company kakao;
    private Company line;

    @BeforeEach
    void setUp() {
        naver = Company.builder()
                .id(1L)
                .companyName("NAVER")
                .build();

        kakao = Company.builder()
                .id(1L)
                .companyName("KAKAO")
                .build();

        line = Company.builder()
                .id(1L)
                .companyName("LINE")
                .build();
    }

    @Test
    void createPostTest() {

        //given
        when(companyRepository.findById(line.getId())).thenReturn(Optional.of(naver));
        when(postRepository.save(any(Post.class))).thenAnswer(i -> i.getArguments()[0]);

        //when
        PostDto.Req dto = PostDto.Req.builder()
                .position("Back End")
                .skill("Spring")
                .description("Java Spring Back End")
                .build();

        PostDto.Res post = postService.createPost(1L, dto);

        //then
        assertThat(post.getSkill()).isEqualTo(dto.getSkill());
    }
}
