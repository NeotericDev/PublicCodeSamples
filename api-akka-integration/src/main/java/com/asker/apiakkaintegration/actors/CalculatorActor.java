package com.asker.apiakkaintegration.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.asker.apiakkaintegration.messages.AddMessage;
import com.asker.apiakkaintegration.messages.DivMessage;
import com.asker.apiakkaintegration.messages.MulMessage;
import com.asker.apiakkaintegration.messages.SubMessage;

public class CalculatorActor extends AbstractActor {
    private ActorRef sender;

    public static Props propsDefault(){
        return Props.create(CalculatorActor.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(AddMessage.class, msg -> {
                    sender = sender();
                    int result = add(msg);
                    sender.tell(result, self());
                })
                .match(SubMessage.class, msg -> {
                    sender = sender();
                    int result = sub(msg);
                    sender.tell(result, self());
                })
                .match(MulMessage.class, msg -> {
                    sender = sender();
                    int result = mul(msg);
                    sender.tell(result, self());
                })
                .match(DivMessage.class, msg -> {
                    sender = sender();
                    int result = div(msg);
                    sender.tell(result, self());
                })
                .build();
    }

    private int add(AddMessage msg){
        return msg.getOp1() + msg.getOp2();
    }

    private int sub(SubMessage msg){
        return msg.getOp1() - msg.getOp2();
    }

    private int mul(MulMessage msg){
        return msg.getOp1() * msg.getOp2();
    }

    private int div(DivMessage msg){
        return msg.getOp1() / msg.getOp2();
    }
}
