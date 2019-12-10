package com.asker.apiakkaintegrationclustercalcservice;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import com.asker.ClusterClient;
import com.asker.apiakkaintegrationclustercalcservice.actors.CalculatorActor;
import com.asker.apiakkaintegrationclustercalcservice.actors.CalculatorServiceSupervisor;
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
    public ActorRef calculatorSupervisor(){
        return actorSystem().actorOf(CalculatorServiceSupervisor.propsDefault(), "calculatorService"); // This name is used in CalculatorService Path in API Project
    }

    @Bean
    public ClusterClient getClusterClient(){
        ActorRef calActorSupervisor = calculatorSupervisor();
        ClusterClient clusterClient = new ClusterClient(actorSystem());
        clusterClient.mediator.tell(new DistributedPubSubMediator.Put(calActorSupervisor), calActorSupervisor);
        return clusterClient;
    }

}
