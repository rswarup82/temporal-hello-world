package io.temporal.examples.helloworld;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

public class SayHelloWorker {

    public static final Logger logger = Workflow.getLogger(SayHelloWorker.class);

    public static void main(String[] args) {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker("hello-world-task-queue");
        worker.registerWorkflowImplementationTypes(SayHelloWorkflowImpl.class);
        worker.registerActivitiesImplementations(new GreetActivitiesImpl());

        logger.info("Starting SayHelloWorker for task queue 'hello-world-task-queue'...");

        factory.start();

    }

}
