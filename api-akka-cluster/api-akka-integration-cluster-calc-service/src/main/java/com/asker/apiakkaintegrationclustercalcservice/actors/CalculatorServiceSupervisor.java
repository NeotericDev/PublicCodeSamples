package com.asker.apiakkaintegrationclustercalcservice.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.asker.messages.*;

public class CalculatorServiceSupervisor extends AbstractActor {

    private ActorRef calculatorActor = context().system().actorOf(CalculatorActor.propsDefault());
    // Declare and instantiate other actors if available

    public static Props propsDefault(){
        return Props.create(CalculatorServiceSupervisor.class);
    }

    //Define your own Supervising strategy if needed. Am leaving it to default.

    @Override
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(AddMessage.class, msg -> {
                    calculatorActor.tell(msg, sender());
                })
                .match(SubMessage.class, msg -> {
                    calculatorActor.tell(msg, sender());
                })
                .match(MulMessage.class, msg -> {
                    calculatorActor.tell(msg, sender());
                })
                .match(DivMessage.class, msg -> {
                    calculatorActor.tell(msg, sender());
                })
                .matchAny(any -> {
                    System.out.println("||||||||Unwknown Msg received|||||||| : " + any);
                })
                .build();
    }
}
