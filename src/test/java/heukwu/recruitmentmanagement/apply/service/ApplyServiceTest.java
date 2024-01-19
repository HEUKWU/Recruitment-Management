package heukwu.recruitmentmanagement.apply.service;

import heukwu.recruitmentmanagement.apply.repository.ApplyEntity;
import heukwu.recruitmentmanagement.apply.repository.ApplyRepository;
import heukwu.recruitmentmanagement.exception.BusinessException;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import heukwu.recruitmentmanagement.post.repository.PostRepository;
import heukwu.recruitmentmanagement.user.repository.User;
import heukwu.recruitmentmanagement.user.repository.UserRepository;
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
class ApplyServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplyRepository applyRepository;

    @InjectMocks
    private ApplyService applyService;

    @Test
    @DisplayName("사용자가 지원을 성공했을때 반환값의 사용자 id와 지원 공고 id는 요청 매개변수의 사용자 id와 지원 공고 id와 같다.")
    void applyTest() {
        //given
        User user = User.builder()
                .id(1L)
                .username("userA")
                .build();

        PostEntity post = PostEntity.builder()
                .id(1L)
                .description("BackEnd")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(applyRepository.findApplyByUserIdAndPostId(1L, 1L)).thenReturn(Optional.empty());
        when(applyRepository.save(any(ApplyEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        //when
        Apply apply = applyService.apply(Apply.builder().userId(1L).postId(1L).build());

        //then
        assertThat(apply.userId()).isEqualTo(user.getId());
        assertThat(apply.postId()).isEqualTo(post.getId());
    }

    @Test
    @DisplayName("사용자가 이미 지원한 공고에 중복 지원했을때 예외가 발생한다.")
    void duplicateApply() {
        //given
        User user = User.builder()
                .id(1L)
                .username("userA")
                .build();

        PostEntity post = PostEntity.builder()
                .id(1L)
                .description("BackEnd")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(applyRepository.findApplyByUserIdAndPostId(1L, 1L)).thenReturn(Optional.of(ApplyEntity.builder().userId(1L).postId(1L).build()));

        //when, then
        assertThatThrownBy(() -> applyService.apply(Apply.builder().userId(1L).postId(1L).build())).isInstanceOf(BusinessException.class).hasMessageContaining("이미 지원한 공고입니다.");
    }
}
