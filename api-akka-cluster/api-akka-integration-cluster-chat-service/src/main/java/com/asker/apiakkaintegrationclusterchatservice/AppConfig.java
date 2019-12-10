package com.asker.apiakkaintegrationclusterchatservice;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.pubsub.DistributedPubSubMediator;
import com.asker.ClusterClient;
import com.asker.apiakkaintegrationclusterchatservice.actors.ChatActorSupervisor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ActorSystem actorSystem(){
        ActorSystem actorSystem = ActorSystem.create("MyCluster", akkaConfig());
        return actorSystem;
    }

    @Bean
    public Config akkaConfig(){
        return ConfigFactory.load();
    }

    @Bean
    public ActorRef chatSupervisor(){
        return actorSystem().actorOf(ChatActorSupervisor.propsDefault(), "chatService"); // This name is used in ChatService Path in API Project
    }

    @Bean
    public ClusterClient getClusterClient(){
        ActorRef chatActorSupervisor = chatSupervisor();
        ClusterClient clusterClient = new ClusterClient(actorSystem());
        clusterClient.mediator.tell(new DistributedPubSubMediator.Put(chatActorSupervisor), chatActorSupervisor);
        return clusterClient;
    }
}
