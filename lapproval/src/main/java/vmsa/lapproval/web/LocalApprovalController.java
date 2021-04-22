package vmsa.lapproval.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vmsa.lapproval.service.RemoteApprovalService;
import vmsa.lapproval.vo.ApprovalVO;

@Api(tags = {"1. Local Approval"})
@Slf4j
@RestController
@RequestMapping("/lapproval")
public class LocalApprovalController {

    @Autowired
    private final RemoteApprovalService remoteApprovalService;

    public LocalApprovalController(RemoteApprovalService remoteApprovalService) {
        this.remoteApprovalService = remoteApprovalService;
    }

    @ApiOperation(value = "결재", notes = "결재 정보 조회한다.")
    @GetMapping("/{doc_no}")
    public String getApproval(@PathVariable("doc_no") String doc_no) {
        String docInfo = remoteApprovalService.getApproval(doc_no);

        log.debug("[Approval Document id = %s at %s %s ]", doc_no, System.currentTimeMillis(), docInfo);

        return String.format("[Approval Document id = %s at %s %s ]", doc_no, System.currentTimeMillis(), docInfo);
    }

    @ApiOperation(value = "결재", notes = "결재 상신한다.")
    @PostMapping
    public ResponseEntity save(@RequestBody ApprovalVO approvalVO) {

        log.debug("Post=============================");

        return ResponseEntity.ok(remoteApprovalService.sendApproval(approvalVO));
    }
}
