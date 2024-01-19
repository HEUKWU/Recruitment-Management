package heukwu.recruitmentmanagement.apply.service;

import heukwu.recruitmentmanagement.apply.repository.ApplyEntity;
import heukwu.recruitmentmanagement.apply.repository.ApplyRepository;
import heukwu.recruitmentmanagement.exception.BusinessException;
import heukwu.recruitmentmanagement.exception.ErrorMessage;
import heukwu.recruitmentmanagement.exception.NotFoundException;
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

    public Apply apply(Apply requestDto) {
        User user = userRepository.findById(requestDto.userId()).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_USER));
        PostEntity postEntity = postRepository.findById(requestDto.postId()).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_POST));

        Optional<ApplyEntity> existApply = applyRepository.findApplyByUserIdAndPostId(user.getId(), postEntity.getId());
        if (existApply.isPresent()) {
            throw new BusinessException(ErrorMessage.DUPLICATE_APPLY);
        }

        ApplyEntity applyEntity = applyRepository.save(requestDto.toEntity());

        return Apply.from(applyEntity);
    }
}
