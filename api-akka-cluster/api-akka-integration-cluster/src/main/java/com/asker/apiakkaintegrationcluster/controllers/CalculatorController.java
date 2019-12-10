package com.asker.apiakkaintegrationcluster.controllers;

import com.asker.apiakkaintegrationcluster.Constants.StringConstants;
import com.asker.messages.AddMessage;
import com.asker.messages.DivMessage;
import com.asker.messages.MulMessage;
import com.asker.messages.SubMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/calc")
public class CalculatorController extends BaseController{

    @GetMapping("/add/{p1}/{p2}")
    public Object add(@PathVariable("p1") int p1, @PathVariable("p2") int p2){
        CompletionStage<Object> pendingResult = clusterClient.ask(new AddMessage(p1, p2), StringConstants.CALCULATOR_SERVICE_PATH);
        Integer result = (Integer)pendingResult.toCompletableFuture().join();
        return p1 + " + " + p2 + " = " + result;
    }

    @GetMapping("/sub/{p1}/{p2}")
    public Object sub(@PathVariable("p1") int p1, @PathVariable("p2") int p2){
        CompletionStage<Object> pendingResult = clusterClient.ask(new SubMessage(p1, p2), StringConstants.CALCULATOR_SERVICE_PATH);
        Integer result = (Integer)pendingResult.toCompletableFuture().join();
        return p1 + " + " + p2 + " = " + result;
    }

    @GetMapping("/mul/{p1}/{p2}")
    public Object mul(@PathVariable("p1") int p1, @PathVariable("p2") int p2){
        CompletionStage<Object> pendingResult = clusterClient.ask(new MulMessage(p1, p2), StringConstants.CALCULATOR_SERVICE_PATH);
        Integer result = (Integer)pendingResult.toCompletableFuture().join();
        return p1 + " + " + p2 + " = " + result;
    }

    @GetMapping("/div/{p1}/{p2}")
    public Object div(@PathVariable("p1") int p1, @PathVariable("p2") int p2){
        CompletionStage<Object> pendingResult = clusterClient.ask(new DivMessage(p1, p2), StringConstants.CALCULATOR_SERVICE_PATH);
        Integer result = (Integer)pendingResult.toCompletableFuture().join();
        return p1 + " + " + p2 + " = " + result;
    }
}
