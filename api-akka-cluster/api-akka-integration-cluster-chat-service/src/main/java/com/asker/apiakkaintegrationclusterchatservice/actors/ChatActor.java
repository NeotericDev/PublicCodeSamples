package com.asker.apiakkaintegrationclusterchatservice.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.asker.messages.TextMsg;

public class ChatActor extends AbstractActor {
    private ActorRef sender;

    public static Props propsDefault(){
        return Props.create(ChatActor.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TextMsg.class, msg -> {
                    sender = sender();
                    String response = chat(msg);
                    sender.tell(response, self());
                })
                .build();
    }

    private String chat(TextMsg msg) {
        return "Reply from ChatService [" + self().path() + "] :\n Sorry, I don't understand what do you mean by \"" + msg.getMsg() + "\".\nI am under development. So, please try some time later.";
    }
}
