# AI Modernization Platform

An Agentic AI platform for modernizing IBM Mainframe applications using Spring AI, OpenAI, and Java.

## Vision

Modernize legacy mainframe applications through a pipeline of specialized AI agents rather than a single prompt.

The platform is designed to understand existing systems first, then progressively generate modern cloud-native applications.

```
IBM GenApp
     │
     ▼
Discovery Agent
     │
     ▼
Discovery Document
     │
     ▼
API Generation Agent
     │
     ▼
Spring Boot Microservices
     │
     ▼
UI Generation Agent
     │
     ▼
Angular Application
```

---

## Current MVP

### ✅ Discovery Agent

The Discovery Agent analyzes legacy IBM mainframe assets such as:

* COBOL Programs
* JCL
* CICS Programs
* Copybooks (planned)

It produces a structured `DiscoveryDocument` containing:

* Program Summary
* Business Purpose
* Inputs
* Outputs
* Dependencies
* External Programs
* Files / DB2 / VSAM usage
* CICS usage
* Batch processing details
* Suggested Microservice
* Assumptions

The generated document is persisted as a JSON artifact that serves as the input for the next modernization stage.

---

## Planned Agents

### Discovery Agent

Produces a structured understanding of legacy applications.

### API Generation Agent

Consumes the Discovery Document and generates a Spring Boot microservice blueprint and implementation.

### UI Generation Agent

Consumes the generated API model and produces an Angular application.

---

## Technology Stack

* Java 21
* Spring Boot 3.5
* Spring AI
* OpenAI
* Maven

Future Target:

* AWS ECS
* Amazon Bedrock
* GitHub Actions
* Angular
* PostgreSQL

---

## Project Structure

```
backend
│
├── src
├── artifacts
│   ├── discovery
│   ├── api
│   └── ui
└── pom.xml
```

---

## Current Workflow

```
IBM GenApp COBOL

        │

        ▼

Discovery Agent

        │

        ▼

DiscoveryDocument.json
```

---

## Roadmap

* [x] Spring Boot project setup
* [x] Spring AI integration
* [x] OpenAI integration
* [x] Discovery Agent
* [x] Discovery artifact generation
* [ ] API Generation Agent
* [ ] Spring Boot code generation
* [ ] UI Generation Agent
* [ ] Angular generation
* [ ] AWS deployment

---

## Sample Repository

The MVP uses the IBM GenApp sample application as the modernization input to validate the agentic workflow.

---

## License

This project is intended for learning, experimentation, and demonstrating AI-assisted legacy modernization concepts.
