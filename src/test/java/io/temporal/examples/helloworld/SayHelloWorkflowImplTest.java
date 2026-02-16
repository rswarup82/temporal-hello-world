package io.temporal.examples.helloworld;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.worker.Worker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.temporal.internal.logging.LoggerTag.TASK_QUEUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SayHelloWorkflowImplTest {

    private TestWorkflowEnvironment testEnv;
    private Worker worker;
    private WorkflowClient client;

    @BeforeEach
    public void setUp() {
        testEnv = TestWorkflowEnvironment.newInstance();
        worker = testEnv.newWorker(TASK_QUEUE);
        // Register your workflow implementations
        worker.registerWorkflowImplementationTypes(SayHelloWorkflowImpl.class);

        client = testEnv.getWorkflowClient();
    }

    // Clean up test environment after tests are completed
    @AfterEach
    public void tearDown() {
        testEnv.close();
    }

    @Test
    public void testActivityImpl() {
        // This uses the actual activity impl
        worker.registerActivitiesImplementations(new GreetActivitiesImpl());

        // Start test environment
        testEnv.start();

        // Create the workflow stub
        SayHelloWorkflow workflow =
                client.newWorkflowStub(
                        SayHelloWorkflow.class, WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build());

        // Execute our workflow waiting for it to complete
        String greeting = workflow.sayHello("World!");
        assertEquals("Hello World!", greeting);
    }
}
