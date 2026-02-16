package io.temporal.examples.helloworld;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

public class Starter {

    public static final Logger logger = Workflow.getLogger(Starter.class);

    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);

        SayHelloWorkflow workflow = client.newWorkflowStub(
                SayHelloWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue("hello-world-task-queue")
                        .setWorkflowId("say-hello-workflow-id")
                        .build()
        );

        String result = workflow.sayHello("Temporal");

        logger.info("Workflow result: " + result);
    }
}
