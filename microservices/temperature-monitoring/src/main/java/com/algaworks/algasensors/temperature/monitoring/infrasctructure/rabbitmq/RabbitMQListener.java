package com.algaworks.algasensors.temperature.monitoring.infrasctructure.rabbitmq;

import com.algaworks.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import com.algaworks.algasensors.temperature.monitoring.domain.service.SensorAlertService;
import com.algaworks.algasensors.temperature.monitoring.domain.service.TemperatureMonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.algaworks.algasensors.temperature.monitoring.infrasctructure.rabbitmq.RabbitMQConfig.PROCESS_TEMPERATURE_V1_Q;
import static com.algaworks.algasensors.temperature.monitoring.infrasctructure.rabbitmq.RabbitMQConfig.QUEUE_ALERTING_V1_Q;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TemperatureMonitoringService temperatureMonitoringService;
    private final SensorAlertService sensorAlertService;

    @RabbitListener(queues = PROCESS_TEMPERATURE_V1_Q)
    public void handleProcessingTemperature(@Payload TemperatureLogData temperatureLogData) {
        temperatureMonitoringService.processTemperatureReading(temperatureLogData);
    }

    @RabbitListener(queues = QUEUE_ALERTING_V1_Q)
    public void handleAlerting(@Payload TemperatureLogData temperatureLogData) {
        sensorAlertService.handleAlert(temperatureLogData);
    }
}
