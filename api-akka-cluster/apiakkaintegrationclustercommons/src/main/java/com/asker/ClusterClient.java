package com.asker;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import akka.pattern.Patterns;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

public class ClusterClient {

    ActorSystem actorSystem;
    public ActorRef mediator;
    DistributedPubSubMediator.Send pubSubSend = new DistributedPubSubMediator.Send(null, null);

    public ClusterClient(ActorSystem system){
        this.actorSystem = system;
        mediator = DistributedPubSub.get(actorSystem).mediator();
    }

    public CompletionStage<Object> ask(Object msg, String actorPath){
        return Patterns.ask(mediator, getPubSubSend(msg, actorPath), Duration.ofMillis(10000));
    }

    public void tell(Object msg, String actorPath){
        mediator.tell(getPubSubSend(msg, actorPath), ActorRef.noSender());
    }

    private DistributedPubSubMediator.Send getPubSubSend(Object msg, String actorPath){
        return pubSubSend.copy(actorPath, msg, true);
    }
}
