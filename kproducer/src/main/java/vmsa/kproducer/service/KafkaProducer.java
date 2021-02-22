package vmsa.kproducer.service;

import vmsa.kproducer.vo.ApprovalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, ApprovalVO> kafkaTemplate;

    @Value("${topic}")
    private String topicName;

    public void send(ApprovalVO approvalVO) {

        Message<ApprovalVO> message = MessageBuilder
                .withPayload(approvalVO)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        ListenableFuture<SendResult<String, ApprovalVO>> future =  kafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, ApprovalVO>>() {

            @Override
            public void onSuccess(SendResult<String, ApprovalVO> stringObjectSendResult) {
                System.out.println("Sent message=[" + stringObjectSendResult.getProducerRecord().value().toString() +
                        "] with offset=[" + stringObjectSendResult.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[] due to : " + ex.getMessage());
            }
        });
    }
}
