package vmsa.rapproval.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. Remote Approval"})
@Slf4j
@RestController
@RequestMapping("/rapproval")
public class RemoteApprovalController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "결재문서", notes = "결재 문서번호를 조회한다.")
    @GetMapping("/{doc_no}")
    public String getApproval(@PathVariable("doc_no") String doc_no) {

        logger.info("●●●●●●●●●●●●●● RemoteApprovalController started: http://localhost:8113{} to use Kafka Appender", doc_no);

        return "[doc_no  = " + doc_no + " at " + System.currentTimeMillis() + "]";
    }
}
