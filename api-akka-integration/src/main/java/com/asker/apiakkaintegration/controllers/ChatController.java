package com.asker.apiakkaintegration.controllers;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import com.asker.apiakkaintegration.messages.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired()
    @Qualifier("ChatActor")
    ActorRef chatActor;

    @GetMapping("/{msg}")
    public Object chat(@PathVariable("msg") String msg){
        return ask(chatActor, new ChatMessage(msg), String.class);
    }

    private <T> T ask(ActorRef actor, Object msg, Class<T> returnTypeClass){
        return  (T) Patterns.ask(actor, msg, Duration.ofMillis(2000)).toCompletableFuture().join();
    }
}
