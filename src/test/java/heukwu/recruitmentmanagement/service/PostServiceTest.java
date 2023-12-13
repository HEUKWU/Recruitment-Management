package heukwu.recruitmentmanagement.service;

import heukwu.recruitmentmanagement.dto.PostDto;
import heukwu.recruitmentmanagement.entity.Company;
import heukwu.recruitmentmanagement.entity.Post;
import heukwu.recruitmentmanagement.repository.CompanyRepository;
import heukwu.recruitmentmanagement.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

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
                .id(2L)
                .companyName("KAKAO")
                .build();

        line = Company.builder()
                .id(3L)
                .companyName("LINE")
                .build();
    }

    @Test
    @DisplayName("올바른 회사id와 요청 dto로 게시글을 생성했을 때 생성하고 응답된 게시글의 skill 필드는 요청 dto의 skill 필드와 같다.")
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

    @Test
    @DisplayName("존재하지 않는 회사id로 게시글을 생성했을  예외가 발생한다.")
    void createPostNoCompany() {

        //given
        when(companyRepository.findById(4L)).thenReturn(Optional.empty());

        //when
        PostDto.Req dto = PostDto.Req.builder()
                .position("Back End")
                .skill("Spring")
                .description("Java Spring Back End")
                .build();

        //then
        assertThatThrownBy(() -> postService.createPost(4L, dto)).isInstanceOf(IllegalArgumentException.class);
    }
}
