# Hello Work using Temporal SDK + Temporal.io

A real-world demonstration of the **Saga Pattern** using **Temporal.io** for distributed transaction orchestrator.

## 📋 Prerequisites

- **Java 17+**
- **Maven 3.6+**

## 🚀 Quick Start

This is references from temporal.io - https://docs.temporal.io/develop/java/set-up-your-local-java

### 1. Build the Application

```bash
mvn clean package
```

### 2. Run the Temporal Worker Application

```bash
mvn compile exec:java -Dexec.mainClass="io.temporal.examples.helloworld.SayHelloWorker"
```

### 3. Execute the Workflow

```bash
mvn compile exec:java -Dexec.mainClass="io.temporal.examples.helloworld.Starter"
```

