package vmsa.kconsumer.service;

import vmsa.kconsumer.vo.ApprovalVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

//    @KafkaListener(topics = "ApprovalTopic", groupId = "gvpro-consumer")
//    public void consume(String message) throws IOException {
//        System.out.println(String.format("Consumed message : %s", message));
//    }

//    @KafkaListener(topics = "ApprovalTopic", containerFactory = "gvproKafkaListenerContainerFactory", groupId = "gvpro-consumer")
//    public void listenMeJJTopic(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey) throws Exception {
//        log.info("Topic: [ApprovalTopic] messageKey Message: [" + messageKey + "]");
//        log.info("Topic: [ApprovalTopic] Received Message: [" + message + "] from partition: [" + partition + "]");
//    }

    @KafkaListener(topics = "${topic}", groupId = "${group-id}", containerFactory = "pushEntityKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload ApprovalVO approvalVO, @Headers MessageHeaders messageHeaders) {

        // GCM으로 해당 데이터를 전달하는 로직 ....

        System.out.println(
                "Received Message: " + approvalVO.toString() +
                        " headers: " + messageHeaders);
    }

}
