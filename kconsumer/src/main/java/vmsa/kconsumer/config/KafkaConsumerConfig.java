package vmsa.kconsumer.config;

import vmsa.kconsumer.vo.ApprovalVO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@PropertySource("classpath:kafka.properties")
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${bootstrap.servers}")
    private String bootstrapServer;

    @Value("${group-id}")
    private String groupId;

    @Value("${auto-offset-reset}")
    private String offsetReset;

    @Value("${max-poll-records}")
    private String maxPollRecords;

    @Value("${enable-auto-commit}")
    private String enableAutoCommit;

    @Bean
    public ConsumerFactory<String, ApprovalVO> pushEntityConsumerFactory() {
        JsonDeserializer<ApprovalVO> deserializer = gcmPushEntityJsonDeserializer();
        return new DefaultKafkaConsumerFactory<>(
                consumerFactoryConfig(deserializer),
                new StringDeserializer(),
                deserializer);
    }

    private Map<String, Object> consumerFactoryConfig(JsonDeserializer<ApprovalVO> deserializer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        return props;
    }

    private JsonDeserializer<ApprovalVO> gcmPushEntityJsonDeserializer() {
        JsonDeserializer<ApprovalVO> deserializer = new JsonDeserializer<>(ApprovalVO.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return deserializer;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ApprovalVO> pushEntityKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ApprovalVO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pushEntityConsumerFactory());
        return factory;
    }
}