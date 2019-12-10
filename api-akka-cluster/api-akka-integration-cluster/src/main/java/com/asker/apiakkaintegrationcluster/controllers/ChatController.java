package com.asker.apiakkaintegrationcluster.controllers;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import com.asker.apiakkaintegrationcluster.Constants.StringConstants;
import com.asker.messages.AddMessage;
import com.asker.messages.ChatMessage;
import com.asker.messages.TextMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController{
    Random rnd = new Random();
    @GetMapping("/{msg}")
    public Object chat(@PathVariable("msg") String msg){
        return clusterClient.ask(new TextMsg(rnd.nextInt(), msg), StringConstants.CHAT_SERVICE_PATH).toCompletableFuture().join();
    }

}
