<div align="center">

# 🩺 **CarePilot AI**

### 🚀 Intelligent & Secure Healthcare Coordination Assistant

[![Typing SVG](https://readme-typing-svg.demolab.com?font=Fira+Code\&size=22\&duration=3000\&pause=800\&color=00C9FF\&center=true\&vCenter=true\&width=850\&lines=AI-Powered+Healthcare+Coordination;RAG-Grounded+%7C+Privacy-Aware+%7C+Safety-First;Patient+%7C+Coordinator+%7C+Admin+Workflows;Built+with+Java+21+%2B+Spring+Boot+%2B+Spring+AI)](https://git.io/typing-svg)

<p>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.5.15-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_AI-1.1.8-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/RAG-Powered-8A2BE2?style=for-the-badge&logo=openai&logoColor=white" />
</p>

<p>
  <img src="https://img.shields.io/badge/🔐_Privacy-PII%2FPHI_Redaction-ff4b6e?style=for-the-badge" />
  <img src="https://img.shields.io/badge/🛡️_Safety-Escalation-00b894?style=for-the-badge" />
  <img src="https://img.shields.io/badge/🔎_Traceability-Auditable-f39c12?style=for-the-badge" />
  <img src="https://img.shields.io/badge/🌐_UI-Multilingual-0984e3?style=for-the-badge" />
</p>

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:00C9FF,50:6C5CE7,100:FF4B6E&height=120&section=header&text=CarePilot%20AI&fontSize=42&fontColor=ffffff&animation=fadeIn&fontAlignY=55" width="100%" />

</div>

> 💡 **CarePilot AI** is a policy-grounded healthcare coordination platform combining **Generative AI, RAG, privacy protection, safety escalation, auditability, and role-based workflows** to support safer care navigation.

---

CarePilot AI is a **Java 21 + Spring Boot + Spring AI** healthcare
coordination assistant designed around policy-grounded responses,
retrieval-augmented generation (RAG), privacy-aware processing, safety
escalation, conversation history, audit traceability, and knowledge-base
management.

The application provides separate experiences for **Patient,
Coordinator, and Admin** roles through a modern responsive web
interface.

---

## ✨ Highlights

* **Policy-grounded healthcare chat** using an approved knowledge
  base.
* **RAG-based retrieval** with MariaDB Vector Store and Spring AI.
* **Cited / grounded responses** so retrieved knowledge can be traced
  back to the response.
* **Patient, Coordinator, and Admin role experiences** with
  role-specific workflows and UI.
* **PII/PHI redaction** before sensitive information is persisted or
  included in audit records.
* **Safety escalation** for emergency or clinically sensitive
  conversations.
* **Conversation history** with stored conversations and messages.
* **Audit trail** with workflow IDs, actor information, redacted
  queries, retrieved chunks, citations, escalation state, and
  timestamps.
* **Knowledge Base management** including ingestion, upload, status,
  and chunk information.
* **Multilingual interface** with language selection for the supported
  UI experiences.
* **Real-time response streaming** in the chat interface.
* **Dark/light UI support**, responsive layout, quick prompts, role
  switching, and privacy indicators.
* **OpenAPI / Swagger support** through Springdoc.

---

## 🖥️ Application Screens

### Role Selection

CarePilot starts with a role-selection experience allowing users to
enter as a **Patient**, **Coordinator**, or **Admin**.

> Add the screenshots from this project under `docs/screenshots/` and
> use the gallery below.

### Patient Experience

The Patient workspace focuses on healthcare navigation such as
appointments, medication information, health guidance, insurance, and
discharge-related workflows.

### Coordinator Experience

The Coordinator workspace provides policy-grounded support for
scheduling SOPs, referrals, prior-authorisation workflows, discharge
planning, and escalation protocols.

### Admin Experience

The Admin workspace provides access to knowledge-base controls, audit
information, conversation history, and platform-level analytics.

### Audit Trail

The audit view exposes workflow-level traceability including:

* Workflow ID
* Actor
* Redacted query
* Retrieved chunks
* Citations
* Escalation status
* Timestamp

Sensitive identifiers are redacted before being persisted in the audit
record.

### Knowledge Base

The Knowledge Base interface provides:

* Ingestion
* Document upload
* Knowledge-base status
* Vector chunk visibility
* Chunk/dimension information

### Multilingual UI

The interface supports language selection and localized role-selection
experiences. The screenshots demonstrate English, Hindi, and Chinese UI
states.

---

## 🏗️ Architecture

```text
                         ┌──────────────────────────┐
                         │       Web Browser        │
                         │ HTML / CSS / JavaScript  │
                         └────────────┬─────────────┘
                                      │
                                      ▼
                    ┌────────────────────────────────┐
                    │       Spring Boot Backend       │
                    │                                │
                    │  Controllers                   │
                    │  ├─ Healthcare Chat            │
                    │  ├─ Knowledge Base             │
                    │  ├─ Audit                      │
                    │  ├─ Chat History                │
                    │  └─ Admin                      │
                    │                                │
                    │  Services                      │
                    │  ├─ RAG / Chat                 │
                    │  ├─ Safety Advisor              │
                    │  ├─ PII/PHI Redaction           │
                    │  ├─ Audit                       │
                    │  ├─ Chat History                │
                    │  └─ Knowledge Base              │
                    └───────────────┬────────────────┘
                                    │
                 ┌──────────────────┼───────────────────┐
                 │                  │                   │
                 ▼                  ▼                   ▼
        ┌────────────────┐ ┌─────────────────┐ ┌──────────────────┐
        │  Spring AI     │ │ MariaDB Vector  │ │ Approved         │
        │  Azure OpenAI  │ │ Store           │ │ Knowledge Base   │
        └────────────────┘ └─────────────────┘ └──────────────────┘
```

---

## 🔄 RAG / Grounded Response Flow

```text
User Query
    │
    ▼
PII / PHI Redaction
    │
    ▼
Safety & Policy Checks
    │
    ▼
Knowledge Retrieval
    │
    ▼
MariaDB Vector Store
    │
    ▼
Relevant Knowledge Chunks
    │
    ▼
Spring AI + Azure OpenAI
    │
    ▼
Grounded Response + Citations
    │
    ├──────────────► Conversation History
    │
    └──────────────► Audit Trail
```

The application is designed so that healthcare responses are grounded in
the approved knowledge base rather than relying only on unrestricted
model generation.

---

## 🛡️ Privacy & Safety

Healthcare applications require additional handling of sensitive
information. CarePilot includes dedicated mechanisms for:

### PII/PHI Redaction

Sensitive identifiers are redacted before persistence and audit
processing.

Example:

```text
Original:
My name is Ansh and my SSN is 123456789.

Audit representation:
My name is Ansh and my SSN is [GOVT-ID-REDACTED].
```

### Safety Escalation

Emergency or clinically sensitive inputs can trigger an
escalation-oriented response instead of ordinary informational guidance.

The UI communicates that the assistant provides **policy and
care-navigation information** and is not a replacement for licensed
clinical decision-making or emergency services.

---

## 👥 User Roles

### 👤 Patient

Designed for patient-facing healthcare navigation.

Example areas:

* Appointments
* Medication information
* Health guidance
* Insurance
* Discharge guidance
* Accessibility support

### 🩺 Coordinator

Designed for healthcare coordination workflows.

Example areas:

* Scheduling SOPs
* Referrals
* Prior authorisation
* Discharge planning
* Escalation protocols

### 🛡️ Admin

Designed for platform administration.

Example areas:

* Knowledge-base ingestion
* Knowledge-base upload
* Audit trail
* Conversation history
* Platform analytics

---

## 📚 Knowledge Base

The application includes an approved knowledge-base resource under:

```text
src/main/resources/knowledge-base/
```

The project also contains:

```text
VectorStore.sql
```

for the vector-store/database environment.

The Knowledge Base UI exposes the current vector-store status and chunk
information returned by the backend.

---

## 🔌 REST APIs

### Healthcare Chat

```http
POST /ai/healthcare/chat/sync
POST /ai/healthcare/chat/async
```

### Knowledge Base

```http
POST /kb/ingest
POST /kb/upload
GET  /kb/status
```

### Audit

```http
GET /audit/{workflowId}
```

### Authentication / Administration

```http
POST /admin/login
GET  /admin/session
POST /admin/logout
```

### Conversation History

```http
GET /chat/history/conversations
GET /chat/history/{conversationId}
```

---

## 🧰 Tech Stack

Layer                 Technology

---

Language              Java 21
Backend               Spring Boot 3.5.15
AI Framework          Spring AI 1.1.8
LLM                   Azure OpenAI
Vector Store          MariaDB Vector Store
Database Access       Spring JDBC
Document Processing   Spring AI PDF / Tika readers
API Documentation     Springdoc OpenAPI
Frontend              HTML, CSS, JavaScript
Build Tool            Maven
Testing               Spring Boot Test / JUnit

---

## 📁 Project Structure

```text
CarePilot_Complete/
│
├── pom.xml
├── README.md
├── VectorStore.sql
├── .gitignore
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── carepilot/
    │   │           └── healthcare/
    │   │               ├── advisor/
    │   │               ├── config/
    │   │               ├── controller/
    │   │               ├── model/
    │   │               └── service/
    │   │
    │   └── resources/
    │       ├── application.properties
    │       ├── knowledge-base/
    │       └── static/
    │           ├── index.html
    │           ├── app.css
    │           └── app.js
    │
    └── test/
        └── java/
            └── com/
                └── carepilot/
                    └── healthcare/
                        └── service/
```

---

## 🚀 Getting Started

### Prerequisites

Install:

* **Java 21**
* **Maven**
* **MariaDB** for the database/vector-store environment
* Azure OpenAI credentials for real model-backed responses

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

### 1. Clone the repository

```bash
git clone https://github.com/anshmittal2004/CarePilot-AI---Smart-Patient-Care-Coordination-Assistant.git
cd CarePilot-AI---Smart-Patient-Care-Coordination-Assistant
```

### 2. Configure Azure OpenAI

Configure the required Azure OpenAI settings through the application's
configuration/environment setup.

Typical values include:

```text
AZURE_OPENAI_ENDPOINT
AZURE_OPENAI_API_KEY
AZURE_OPENAI_DEPLOYMENT_NAME
```

**Never commit real API keys, passwords, database credentials, or other
secrets to GitHub.**

### 3. Configure MariaDB

Set up the MariaDB environment required by the vector store and
application database configuration.

The repository includes:

```text
VectorStore.sql
```

for the vector-store/database environment.

### 4. Start the application

Using Maven:

```bash
mvn spring-boot:run
```

Or open the project in IntelliJ IDEA / VS Code and run the Spring Boot
application.

### 5. Open the application

```text
http://localhost:8080/
```

---

## 🧪 Testing

Run the test suite with:

```bash
mvn test
```

The project includes tests for the PII/PHI redaction service.

---

## 🔐 Security Notes

This project demonstrates privacy-aware healthcare coordination
patterns, but it should **not be treated as a production clinical system
without additional security, compliance, infrastructure, and clinical
validation**.

For GitHub/public repositories:

* Do not commit Azure API keys.
* Do not commit database passwords.
* Do not commit production credentials.
* Do not upload real patient information.
* Use environment variables or an appropriate secret-management
  system.
* Use synthetic/demo healthcare data for demonstrations.

---

## 📸 Screenshots

Recommended screenshot organization:

```text
docs/
└── screenshots/
    ├── 01-role-selection.png
    ├── 02-role-selection-hindi.png
    ├── 03-role-selection-chinese.png
    ├── 04-patient-chat.png
    ├── 05-coordinator-chat.png
    ├── 06-admin-chat.png
    ├── 07-dashboard.png
    ├── 08-audit-trail.png
    ├── 09-audit-record.png
    └── 10-knowledge-base.png
```

Then add them to this section using:

```markdown
![Role Selection](docs/screenshots/01-role-selection.png)

![Patient Chat](docs/screenshots/04-patient-chat.png)

![Coordinator Chat](docs/screenshots/05-coordinator-chat.png)

![Admin Dashboard](docs/screenshots/07-dashboard.png)

![Audit Trail](docs/screenshots/08-audit-trail.png)

![Knowledge Base](docs/screenshots/10-knowledge-base.png)
```

---

## 🎯 Project Objective

CarePilot AI explores how generative AI can be combined with
**retrieval, policy grounding, privacy protection, safety controls,
auditability, and role-based healthcare workflows** to build a more
controlled healthcare coordination assistant.

The project focuses on **care navigation and coordination**, rather than
autonomous diagnosis or treatment.

---

## 📌 Disclaimer

CarePilot AI is a software project for healthcare coordination and
information-navigation scenarios. It does not replace a licensed
healthcare professional, emergency services, or clinical decision-making
systems.

---

## 👨‍💻 Author

**Ansh Mittal**

B.Tech --- Computer Science & Engineering

GitHub: [@anshmittal2004](https://github.com/anshmittal2004)
