package heukwu.recruitmentmanagement.service;

import heukwu.recruitmentmanagement.dto.PostDto;
import heukwu.recruitmentmanagement.repository.CompanyRepository;
import heukwu.recruitmentmanagement.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private PostService postService;

    @DisplayName("존재하지 않는 회사 id 입력시 오류 발생")
    @Test
    void noExistCompany() {
        PostDto.Req requestDto = PostDto.Req
                .builder()
                .description("존재하지 않는 회사")
                .build();

        assertThatThrownBy(() -> postService.createPost(100L, requestDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

//    @DisplayName("올바른 정보로 채용공고 등록시 채용공고를 조회할 수 있다.")
//    @Test
//    void createPost() {
//        PostDto.Req requestDto = PostDto.Req
//                .builder()
//                .position("BackEnd")
//                .skill("Java-Spring")
//                .description("Java BackEnd Developer")
//                .build();
//
//        postService.createPost(1L, requestDto);
//        Post post = postRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
//
//        assertThat(post.getDescription()).isEqualTo("Java BackEnd Developer");
//    }
}