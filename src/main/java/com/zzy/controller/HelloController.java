package com.zzy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Thinkpad-W530 on 2021/10/22.
 */

@RestController
@RequestMapping("/test")
public class HelloController {


    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/mono")
    public Mono<Object> mono() {
        return Mono.create(monoSink -> {
            LOGGER.info("创建 Mono");
            monoSink.success("hello webflux");
        }).doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
            LOGGER.info("{}", subscription.hashCode());
        }).doOnNext(o -> { //当订阅者收到数据时，改方法会调用
            LOGGER.info("{}", o);
        });
    }


    @GetMapping(value = "/flux")
    public Flux<Object> flux() {
        return Flux.generate(
                () -> 1,
                (i, sink) -> {
                    LOGGER.warn("i:{}", i);
                    sink.next(i * i + "");
                    if (i == 5) sink.complete();
                    return ++i;
                },
                state -> LOGGER.warn("the final state is:{}", state)
        );
    }


    @GetMapping(value = "/flux2", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Object> flux2() {

            return Flux.create(sink -> {
                for (int i = 0; i < 1000000; i++) {

                    sink.next(i);
                }
                sink.complete();
            }).doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
                        LOGGER.info("{}", subscription);
                    }
            ).doOnNext(o -> { //当订阅者收到数据时，改方法会调用
                LOGGER.info("{}", o);
            });


    }

}
