package heukwu.recruitmentmanagement.apply.controller;

import heukwu.recruitmentmanagement.apply.service.Apply;
import heukwu.recruitmentmanagement.apply.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/apply")
    public ApplyResponse.Create apply(@RequestBody ApplyRequest.Create request) {
        Apply requestDto = request.toDomain();
        Apply apply = applyService.apply(requestDto);

        return ApplyResponse.Create.from(apply);
    }
}
