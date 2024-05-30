package com.uniquindio.eureka_registry.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Component
public class MonitoringService {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    private static final String NOTIFICATION_SERVICE_URL = "http://notification-service/api/v1/alerts";

    @Scheduled(fixedRate = 60000) // Ejecutar cada 60 segundos
    public void monitorServices() {
        System.out.println("Iniciando monitoreo de servicios...");

        List<InstanceInfo> allInstances = eurekaClient.getApplications()
                .getRegisteredApplications()
                .stream()
                .flatMap(app -> app.getInstances().stream())
                .collect(Collectors.toList());

        if (allInstances.isEmpty()) {
            System.out.println("No hay servicios registrados.");
            return;
        }

        System.out.println("Número total de instancias registradas: " + allInstances.size());

        allInstances.forEach(instance -> {
            System.out.println("Servicio: " + instance.getAppName() + " - Estado: " + instance.getStatus());
        });

        List<InstanceInfo> downServices = allInstances.stream()
                .filter(instance -> instance.getStatus() != InstanceInfo.InstanceStatus.UP)
                .collect(Collectors.toList());

        if (downServices.isEmpty()) {
            System.out.println("Todos los servicios están operativos.");
        } else {
            System.out.println("Servicios caídos:");
            downServices.forEach(instance -> System.out.println(
                    "- " + instance.getAppName() + " (" + instance.getInstanceId() + ") - Estado: " + instance.getStatus()
            ));
            sendNotification(downServices);
        }
    }

    private void sendNotification(List<InstanceInfo> downServices) {
        try {
            restTemplate.postForEntity(NOTIFICATION_SERVICE_URL, downServices, Void.class);
            System.out.println("Notificación enviada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al enviar la notificación: " + e.getMessage());
            // Considera agregar más manejo de errores aquí (e.g., reintentos, registro en archivo, etc.)
        }
    }


}


