# CarePilot AI — Smart Patient Care Coordination Assistant

A Java 21 + Spring Boot + Spring AI healthcare assistant with policy-grounded chat, conversation history, audit traceability, admin KB controls, multilingual UI and optional Azure Neural Text-to-Speech.

## Key improvements

- `com.infy.healthcare` renamed to `com.carepilot.healthcare`.
- Modern responsive healthcare UI with role switching, KB status, history, quick prompts, dark mode, voice panel and grounded citations.
- Multilingual chat selector: English, Hindi, Kannada, French, Chinese, Spanish, German, Japanese, Portuguese and Arabic.
- Optional Azure Speech neural TTS is called by Java at `/api/voice/synthesize`; no secret is exposed to the browser.
- Browser speech recognition can fill the composer for hands-free input.
- PII/PHI redaction before persistence/audit.
- Emergency/clinically sensitive queries receive a safety escalation notice.
- Audit trail and conversation history APIs retained.
- Admin login protects KB ingestion/upload endpoints.

## Run

1. Java 21.
2. MariaDB if you want the database/vector-store environment from the original project.
3. Set Azure OpenAI variables when using the real model:
   - `AZURE_OPENAI_ENDPOINT`
   - `AZURE_OPENAI_API_KEY`
   - `AZURE_OPENAI_DEPLOYMENT_NAME`
4. Optional neural voice:
   - `CAREPILOT_VOICE_KEY`
   - `CAREPILOT_VOICE_REGION`
5. Run `mvn spring-boot:run` or open in IntelliJ/VS Code.
6. Open `http://localhost:8080/`.

## Demo mode

If Azure OpenAI is not configured, the application can still start and use the built-in approved knowledge snippets for a deterministic grounded demonstration. Neural TTS remains disabled until Azure Speech credentials are supplied.

## APIs

- `POST /ai/healthcare/chat/sync`
- `POST /ai/healthcare/chat/async`
- `GET /audit/{workflowId}`
- `GET /kb/status`
- `POST /kb/ingest`
- `POST /kb/upload`
- `POST /admin/login`
- `GET /admin/session`
- `POST /admin/logout`
- `GET /chat/history/conversations`
- `GET /chat/history/{conversationId}`
- `POST /api/voice/synthesize`

No Python is part of this project.
