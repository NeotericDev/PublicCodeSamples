package com.asker.apiakkaintegration.controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import com.asker.apiakkaintegration.actors.CalculatorActor;
import com.asker.apiakkaintegration.messages.AddMessage;
import com.asker.apiakkaintegration.messages.DivMessage;
import com.asker.apiakkaintegration.messages.MulMessage;
import com.asker.apiakkaintegration.messages.SubMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/calc")
public class CalculatorController {

    @Autowired()
    @Qualifier("CalculatorActor")
    ActorRef calculatorActor;


    @GetMapping("/add/{p1}/{p2}")
    public Object add(@PathVariable("p1") int p1, @PathVariable("p2") int p2){
        int result = (int)Patterns.ask(calculatorActor, new AddMessage(p1, p2), Duration.ofMillis(2000)).toCompletableFuture().join();
        return p1 + " + " + p2 + " = " + result;
    }

    @GetMapping("/sub/{p1}/{p2}")
    public Object sub(@PathVariable("p1") int p1, @PathVariable("p2") int p2){
        int result = (int)Patterns.ask(calculatorActor, new SubMessage(p1, p2), Duration.ofMillis(2000)).toCompletableFuture().join();
        return p1 + " + " + p2 + " = " + result;
    }

    @GetMapping("/mul/{p1}/{p2}")
    public Object mul(@PathVariable("p1") int p1, @PathVariable("p2") int p2){
        int result = (int)Patterns.ask(calculatorActor, new MulMessage(p1, p2), Duration.ofMillis(2000)).toCompletableFuture().join();
        return p1 + " + " + p2 + " = " + result;
    }

    @GetMapping("/div/{p1}/{p2}")
    public Object div(@PathVariable("p1") int p1, @PathVariable("p2") int p2){
        Integer result = ask(calculatorActor, new DivMessage(p1,p2), Integer.class);
        return p1 + " + " + p2 + " = " + result;
    }

    private <T> T ask(ActorRef actor, Object msg, Class<T> returnTypeClass){
        return  (T)Patterns.ask(actor, msg, Duration.ofMillis(2000)).toCompletableFuture().join();
    }
}
