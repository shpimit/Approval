package vmsa.kproducer.web;

import vmsa.kproducer.service.KafkaProducer;
import vmsa.kproducer.vo.ApprovalVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Api(tags = {"1. Kafka Producer"})
@RestController
@RequestMapping("/approval/")
public class KafkaProducerController {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // KafkaConfiguration에서 작성한 Bean 주입.
//    @Autowired
//    KafkaTemplate kafkaTemplate;
//
//    /**
//     * /get?message=value 형태로 접근할 수 있도록 api 작성
//     *
//     * @param message
//     * @return
//     */
//    @GetMapping(value = "/get")
//    public String getData(@RequestParam(value = "message", required = true, defaultValue = "") String message) {
//        // 현재 시간
//        LocalDateTime date = LocalDateTime.now();
//        String dateStr = date.format(fmt);
//
//        // mytopic에 현재 시간 + message를 produce 한다.
//        kafkaTemplate.send("ApprovalTopic", dateStr + "   " + message);
//        return "kafkaTemplate.send >>  " + message;
//    }

    @Autowired
    private KafkaProducer kafkaProducer;

    @ApiOperation(value = "결재", notes = "결재정보 상신한다.")
    @PostMapping("/push")
    public String push(@RequestBody ApprovalVO approvalVO) {
        kafkaProducer.send(approvalVO);
        return "success";
    }
}

