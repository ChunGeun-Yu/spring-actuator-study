package me.developery.actuatorstudy.controller;

import io.micrometer.core.annotation.Counted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.developery.actuatorstudy.counter.MyHttpRequestManager;
import me.developery.actuatorstudy.counter.MyQueueManagerWithTags;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MetricsController {

    private final MyHttpRequestManager myHttpRequestManager;  // 생성자 주입

    private final MyQueueManagerWithTags myQueueManagerWithTags;

    @GetMapping("/req")
    public String request() {
        myHttpRequestManager.increase();  // counter 를 증가시킴
        return "ok";
    }

    @Counted(value="myCountedAnnotationCount", extraTags = {"type", "test1"})
    @GetMapping("/counted")
    public String counted() {
        return "ok";
    }

    @Counted(value="myCountedAnnotationCount", extraTags = {"type", "test2"})
    @GetMapping("/counted2")
    public String counted2() {
        return "ok";
    }


    @GetMapping("/push")
    public String push() {
        myQueueManagerWithTags.push();
        return "ok";
    }


    @GetMapping("/pop")
    public String pop() {
        myQueueManagerWithTags.pop();
        return "ok";
    }

}
