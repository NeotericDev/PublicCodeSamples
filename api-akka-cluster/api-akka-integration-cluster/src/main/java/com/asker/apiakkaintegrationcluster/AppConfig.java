package com.asker.apiakkaintegrationcluster;

import akka.actor.ActorSystem;
import com.asker.ClusterClient;
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
    public ClusterClient getClusterClient(){
        return new ClusterClient(actorSystem());
    }
}
