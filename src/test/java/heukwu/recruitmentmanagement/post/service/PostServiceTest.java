package heukwu.recruitmentmanagement.post.service;

import heukwu.recruitmentmanagement.company.repository.Company;
import heukwu.recruitmentmanagement.company.repository.CompanyRepository;
import heukwu.recruitmentmanagement.exception.NotFoundException;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import heukwu.recruitmentmanagement.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
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
    @DisplayName("검색어를 입력하지 않고 게시글 전체조회시 응답된 리스트의 개수는 저장한 게시글의 개수와 같다.")
    void getAllPostTest() {

        //given
        Post post = Post.builder()
                .position("Back End")
                .skill("Spring")
                .description("Java Spring Back End")
                .build();

        PageRequest pageRequest = PageRequest.of(0, 10);

        List<PostEntity> postEntities = List.of(post.toEntity(naver.getId()), post.toEntity(kakao.getId()), post.toEntity(line.getId()));
        when(companyRepository.findById(1L)).thenReturn(Optional.ofNullable(naver));
        when(companyRepository.findById(2L)).thenReturn(Optional.ofNullable(kakao));
        when(companyRepository.findById(3L)).thenReturn(Optional.ofNullable(line));
        when(postRepository.findBySearchOption(pageRequest, null, null)).thenReturn(new PageImpl<>(postEntities));

        //when
        List<Post> allPost = postService.getAllPost(null, null, pageRequest.getPageNumber(), pageRequest.getPageSize());

        //then
        assertThat(allPost.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("특정 게시글 조회시 게시글을 올린 회사의 다른 게시글의 아이디를 조회할 수 있다.")
    void getPostTest() {

        //given
        Company company = Company.builder()
                .id(1L)
                .companyName("naver")
                .build();

        PostEntity postEntity1 = PostEntity.builder()
                .id(1L)
                .companyId(company.getId())
                .position("position")
                .skill("skill")
                .description("description")
                .build();

        PostEntity postEntity2 = PostEntity.builder()
                .id(2L)
                .companyId(company.getId())
                .position("position")
                .skill("skill")
                .description("description")
                .build();

        PostEntity postEntity3 = PostEntity.builder()
                .id(3L)
                .companyId(company.getId())
                .position("position")
                .skill("skill")
                .description("description")
                .build();

        when(postRepository.findById(1L)).thenReturn(Optional.of(postEntity1));
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(postRepository.findAllByCompanyId(company.getId())).thenReturn(List.of(postEntity1, postEntity2, postEntity3));

        //when
        PostWithOtherPosts post = postService.getPost(1L);

        //then
        assertThat(post.otherPostIds()).contains(2L, 3L);
    }

    @Test
    @DisplayName("존재하지 않는 게시글의 id로 조회시 예외가 발생한다.")
    void getPostNotExist() {

        //given
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        //when, then
        assertThatThrownBy(() -> postService.getPost(1L)).isInstanceOf(NotFoundException.class).hasMessageContaining("공고를 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("올바른 회사id와 요청 dto로 게시글을 생성했을 때 생성하고 응답된 게시글의 skill 필드는 요청 dto의 skill 필드와 같다.")
    void createPostTest() {

        //given
        when(companyRepository.findById(naver.getId())).thenReturn(Optional.of(naver));
        when(postRepository.save(any(PostEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        //when
        Post post = Post.builder()
                .position("Back End")
                .skill("Spring")
                .description("Java Spring Back End")
                .build();

        Post createdPost = postService.createPost(1L, post);

        //then
        assertThat(createdPost.skill()).isEqualTo(post.skill());
    }

    @Test
    @DisplayName("존재하지 않는 회사id로 게시글을 생성했을 때 예외가 발생한다.")
    void createPostNotExistCompany() {

        //given
        when(companyRepository.findById(4L)).thenReturn(Optional.empty());

        //when, then
        Post post = Post.builder()
                .position("Back End")
                .skill("Spring")
                .description("Java Spring Back End")
                .build();

        assertThatThrownBy(() -> postService.createPost(4L, post)).isInstanceOf(NotFoundException.class).hasMessageContaining("회사를 찾을 수 없습니다.");
    }
}
