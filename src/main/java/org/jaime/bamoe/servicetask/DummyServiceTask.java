package org.jaime.bamoe.test.servicetask;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

@ApplicationScoped
public class DummyServiceTask {

    Logger logger = Logger.getLogger(DummyServiceTask.class);

    public void sayHello(String name) {
        logger.info("Hello, " + name);
    }    
}
