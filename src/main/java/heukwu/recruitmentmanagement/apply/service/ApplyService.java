package heukwu.recruitmentmanagement.apply.service;

import heukwu.recruitmentmanagement.apply.repository.ApplyEntity;
import heukwu.recruitmentmanagement.apply.repository.ApplyRepository;
import heukwu.recruitmentmanagement.post.repository.PostEntity;
import heukwu.recruitmentmanagement.post.repository.PostRepository;
import heukwu.recruitmentmanagement.user.repository.User;
import heukwu.recruitmentmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ApplyRepository applyRepository;

    public Apply apply(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);

        Optional<ApplyEntity> applyByUserIdAndPostId = applyRepository.findApplyByUserIdAndPostId(user.getId(), postEntity.getId());
        if (applyByUserIdAndPostId.isPresent()) {
            throw new IllegalArgumentException();
        }

        ApplyEntity applyEntity = Apply.toEntity(postId, userId);
        applyRepository.save(applyEntity);

        return Apply.from(applyEntity);
    }
}
