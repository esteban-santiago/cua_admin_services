package com.cua.admin.controllers.rest.it;

import com.cua.admin.model.it.Notification;
import java.time.Duration;
import java.util.stream.Stream;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 *
 * @author esantiago
 */
@RestController
public class NotificationRestController {
    @GetMapping("/sapi/it/notification/{id}")
    Mono<Notification> notificationById(@PathVariable Long id) {
        return Mono.just(new Notification(id, 1, "Contenido"));
    }
    
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value = "/sap/it/notification")
    Flux<Notification> notifications() {
        Flux<Notification> notificationFlux = 
                Flux.fromStream(Stream.generate(() -> new Notification(System.currentTimeMillis(), 1, "Evento generado")));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(notificationFlux, durationFlux)
                .map(Tuple2::getT1);
    }
}
