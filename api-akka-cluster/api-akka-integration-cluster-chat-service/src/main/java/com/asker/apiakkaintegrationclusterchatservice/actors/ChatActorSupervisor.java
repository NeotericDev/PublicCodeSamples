package com.asker.apiakkaintegrationclusterchatservice.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.asker.messages.*;

public class ChatActorSupervisor extends AbstractActor {

    private ActorRef chatActor = context().system().actorOf(ChatActor.propsDefault());
    // Declare and instantiate other actors if available

    public static Props propsDefault(){
        return Props.create(ChatActorSupervisor.class);
    }

    //Define your own Supervising strategy if needed. Am leaving it to default.

    @Override
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TextMsg.class, msg -> {
                    chatActor.tell(msg, sender());
                })
                .build();
    }
}
