package com.algaworks.algasensors.temperature.monitoring.infrasctructure.rabbitmq;

import com.algaworks.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.algaworks.algasensors.temperature.monitoring.infrasctructure.rabbitmq.RabbitMQConfig.PROCESS_TEMPERATURE_V_1_Q;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    @RabbitListener(queues = PROCESS_TEMPERATURE_V_1_Q)
    public void handle(@Payload TemperatureLogData temperatureLogData,
                       @Headers Map<String, Object> headers) {
        TSID sensorId = temperatureLogData.getSensorId();
        Double value = temperatureLogData.getValue();
        log.info("Temperature updated: {} - {}", sensorId, value);
        log.info("Headers: {}", headers);
    }
}
