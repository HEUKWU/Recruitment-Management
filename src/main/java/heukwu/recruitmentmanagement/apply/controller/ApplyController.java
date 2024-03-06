package heukwu.recruitmentmanagement.apply.controller;

import heukwu.recruitmentmanagement.apply.service.Apply;
import heukwu.recruitmentmanagement.apply.service.ApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "apply", description = "채용 지원")
@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @Operation(summary = "공고 지원")
    @PostMapping("/apply")
    public ApplyResponse.Create apply(@RequestBody ApplyRequest.Create request) {
        Apply requestDto = request.toDomain();
        Apply apply = applyService.apply(requestDto);

        return ApplyResponse.Create.from(apply);
    }
}
