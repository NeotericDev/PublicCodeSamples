package com.asker.apiakkaintegrationcluster.controllers;

import com.asker.ClusterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {


    @Autowired()
    protected ClusterClient clusterClient;
}
