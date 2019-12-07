package com.asker.apiakkaintegration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.asker.apiakkaintegration.actors.CalculatorActor;
import com.asker.apiakkaintegration.actors.ChatActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ActorSystem actorSystem(){
        ActorSystem actorSystem = ActorSystem.create("MyActorSystem", akkaConfig());
        return actorSystem;
    }

    @Bean
    public Config akkaConfig(){
        return ConfigFactory.load();
    }

    @Bean(name = {"CalculatorActor"})
    public ActorRef calculatorActor(){
        return actorSystem().actorOf(CalculatorActor.propsDefault());
    }

    @Bean(name = {"ChatActor"})
    public ActorRef chatActor(){
        return actorSystem().actorOf(ChatActor.propsDefault());
    }
}
