package heukwu.recruitmentmanagement.apply.controller;

import heukwu.recruitmentmanagement.apply.service.Apply;
import heukwu.recruitmentmanagement.apply.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("apply/{userId}/{postId}")
    public ApplyResponse.Create apply(@PathVariable Long userId, @PathVariable Long postId) {
        Apply apply = applyService.apply(userId, postId);

        return ApplyResponse.Create.from(apply);
    }
}
