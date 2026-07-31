package org.jaime.bamoe.events;

import org.jboss.logging.Logger;
import org.kie.kogito.process.Processes;
import org.kie.kogito.process.SignalFactory;
import org.kie.kogito.usertask.UserTaskEventListener;
import org.kie.kogito.usertask.events.UserTaskStateEvent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NoCodeUserTaskEventListener implements UserTaskEventListener {

    private static final Logger logger = Logger.getLogger(NoCodeUserTaskEventListener.class);

    @Inject
    Processes processes;

    @Override
    public void onUserTaskState(UserTaskStateEvent event) {

        logger.infof("");
        logger.infof("");
        logger.infof("onUserTaskState: TRIGGERED USER TASK CHANGE STATE. OldStatus: %s, NewStatus: %s",
            event.getOldStatus().getName(),
            event.getNewStatus().getName());
        
        if(!event.getOldStatus().equals(event.getNewStatus())
            && event.getNewStatus().getName().equals("Suspended")) {

            logger.infof("onUserTaskState: Suspending user task and process SLAs...");
            
            org.kie.kogito.process.impl.AbstractProcessInstance processInstance = 
                (org.kie.kogito.process.impl.AbstractProcessInstance)processes.processById(event.getUserTaskInstance().getProcessInfo().getProcessId())
                    .instances()
                    .findById(event.getUserTaskInstance().getProcessInfo().getProcessInstanceId()).get();

            processInstance.send(SignalFactory.of("cancelSlas"));

            logger.infof("onUserTaskState: User Task and process SLAs suspended!!");
        }
        else if(!event.getOldStatus().equals(event.getNewStatus())
            && event.getOldStatus().getName().equals("Suspended")
            && event.getNewStatus().getName().equals("InProgress")) {

            logger.infof("onUserTaskState: Resuming user task and process SLAs...");

            org.kie.kogito.process.impl.AbstractProcessInstance processInstance = 
                (org.kie.kogito.process.impl.AbstractProcessInstance)processes.processById(event.getUserTaskInstance().getProcessInfo().getProcessId())
                    .instances()
                    .findById(event.getUserTaskInstance().getProcessInfo().getProcessInstanceId()).get();

            processInstance.send(SignalFactory.of("triggerSlas"));

            logger.infof("onUserTaskState: User Task and process SLAs resumed!!");

        }
        else {
            logger.infof("onUserTaskState: DISCARDING USERTASKEVENT");
        }

        logger.infof("");
        logger.infof("");

        UserTaskEventListener.super.onUserTaskState(event);
    }
}
