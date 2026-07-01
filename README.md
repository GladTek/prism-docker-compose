# Jahia Prism - Docker Compose

![Jahia](https://img.shields.io/badge/Jahia-8.2+-00A0E3?style=for-the-badge&logo=jahia&logoColor=white)
![Docker Pulls](https://img.shields.io/docker/pulls/achaabni/jahia-prism?style=for-the-badge&logo=docker&logoColor=white&label=Docker%20Pulls)
![React](https://img.shields.io/badge/React-19.0-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![TypeScript](https://img.shields.io/badge/TypeScript-5.0+-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Docker](https://img.shields.io/badge/Docker_Compose-v2+-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Traefik](https://img.shields.io/badge/Traefik-v3.7-24A1C1?style=for-the-badge&logo=traefik&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)

Local development stack for Jahia (Prism), fronted by Traefik with TLS, backed by
Postgres, with Mailpit for catching outbound email.

## 1. Project Overview

- **Template Set**: **GladTek Prism** (`gladtek-prism`) - Modern Jahia 8+ JavaScript Template Set & Component Library built with React 19 (Server-Side Rendering + Client-Side Islands), TypeScript, and Vite.
- **Framework**: Jahia JavaScript Modules Engine (`@jahia/javascript-modules-library`)
- **Rendering Model**: Server-side React components (`.server.tsx`) with client-side interactive islands (`.client.tsx`)
- **Styling & Themes**: Dynamic site-level themes (`corporate`, `editorial`, `ecommerce`, `portfolio`, `gov`) driven by `global.css` design tokens and mixins
- **Form Builder**: Integration with multi-step wizard forms (`fmdb:form`, `fmdb:step`, `fmdb:fieldList`) and custom components (`gtf:*`)

---

## 2. Component Library & Architecture

### Page Templates & Base Styling
- **Base Layout**: `Layout.tsx` handling dynamic `<head>`, SEO meta tags, Google Fonts, theme CSS variables, and Jahia edit-mode scripts.
- **Page Layouts**: `PageLayout.tsx` with configurable JCR areas (`header`, `main`, `footer`, `sidebar`).
- **Global Design System**: `global.css` defining HSL color tokens, typography rules, dark mode, and responsive layout primitives.

### Pre-built UI Components (50+ Components)
- **Navigation & Layout**: MegaMenu (Panels, Link Groups, Cards, NavLinks), Navbar, Footer, Breadcrumbs, Sticky Anchor Nav, Section, Row, ContentBlock, Tabs.
- **Hero & CTAs**: HeroBanner, CTABanner, CallToAction, BannerStrip, BeforeAfter comparison sliders.
- **Grids, Cards & Data**: BentoGrid, FeatureGrid, ServiceGrid, IconList, ComparisonTable, CsvTable.
- **Media & Visuals**: Video, AudioPlayer, ImageGallery, LightboxGallery, LogoShowcase (animated infinite marquee loop).
- **Social Proof & Metrics**: Stats counters, Testimonials grid, Team grid, Timeline, ProcessSteps, Pricing tables.
- **Dynamic JCR Query Views**: Blog (BlogPost, BlogQuery, RelatedBlogPosts), Case Studies (CaseStudy, CaseStudyQuery), Events (Event, EventQuery), Resources (Resource, ResourceQuery).
- **Interactive Widgets**: Accordion, HorizontalAccordion, Modal, CountdownTimer, MapEmbed, ShareBar, ReadingProgress, JsonLd, EditButton.

---

## 3. Formidable Form Component Catalog & Specification

### Custom Elements (`gtf:`)

| Component | Node Type | Submitted Value | Notes |
|-----------|-----------|-----------------|-------|
| Rating (stars) | `gtf:inputRating` | `"1"` - `"N"` | `maxStars` property controls range |
| Phone | `gtf:inputPhone` | `+33612345678` | Dial-code selector + local number |
| Consent checkbox | `gtf:inputConsent` | Boolean | Rich-text label via `consentText` |
| Budget slider | `gtf:inputBudgetSlider` | Number string | `minValue`, `maxValue`, `stepValue` |
| Image choice | `gtf:inputImageChoice` | String | Child nodes of type `gtf:imageChoiceOption` |
| Matrix | `gtf:inputMatrix` | One value per row | Grid of radio buttons; `rows` × `columns` properties |
| Ranking | `gtf:inputRanking` | Ordered list | Drag-to-rank; child nodes of type `gtf:rankingOption` |

### Custom Non-Input / Presentational Components

| Component | Node Type | Purpose |
|-----------|-----------|---------|
| Form section | `gtf:formSection` | Visual grouping block - no submission |
| Form recap | `gtf:formRecap` | Summary step showing all entered values |
| CSS injector | `gtf:formStyle` | Injects a JCR CSS file globally via `<AddResources>` |

---

## 4. Pre-configured Forms (`/sites/prism`)

The Prism template site includes 13 pre-configured Formidable forms embedded across its pages for landing flows, user interaction, and component demonstrations:

### 1. Event Sponsorship Registration
- **Page Path:** `/sites/prism/home/events/vaadin-developer-day`
- **Fields:** Full Name (`fmdb:inputText`), Registration Type (`fmdb:select`), Sponsorship Budget (`gtf:inputBudgetSlider`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Collects corporate sponsorship inquiries and estimated budget tiers for Vaadin Developer Day.

### 2. Event RSVP
- **Page Path:** `/sites/prism/home/events/astro-cms-workshop`
- **Fields:** Full Name (`fmdb:inputText`), Attendance Type (`fmdb:select`), Guest Count (`fmdb:select`), Notes (`fmdb:textarea`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Manages workshop RSVPs, attendance preferences, and guest count tracking for technical workshops.

### 3. Document Download
- **Page Path:** `/sites/prism/home/resources/ai-cms-whitepaper`
- **Fields:** Gated download step (`step-1`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`) & triggers Automated Email (`gtf:emailDownloadNotificationAction`)
- **Purpose:** Captures lead information and sends an automated whitepaper access link via email.

### 4. Contact Request
- **Page Path:** `/sites/prism/home/contact`
- **Fields:** Multi-step branching setup (`details`, `support`, `project-request`, `general-inquiry`, `billing`, `recap`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Comprehensive contact center routing visitor requests dynamically by department with a dynamic recap step.

### 5. Newsletter Subscription (Catalog)
- **Page Path:** `/sites/prism/home/forms-catalog/newsletter-subscription`
- **Fields:** Email (`fmdb:inputEmail`), Topics (`fmdb:checkbox`), Consent (`gtf:inputConsent`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Single-step catalog demonstration for collecting email subscriptions and GDPR marketing consents.

### 6. Contact Form (Catalog)
- **Page Path:** `/sites/prism/home/forms-catalog/contact-form`
- **Fields:** Full Name (`fmdb:inputText`), Email (`fmdb:inputEmail`), Subject (`fmdb:select`), Message (`fmdb:textarea`), File Attachment (`fmdb:inputFile`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Demonstrates conditional field visibility (file upload input appears only when Support subject is selected).

### 7. Meeting Booking (Catalog)
- **Page Path:** `/sites/prism/home/forms-catalog/meeting-booking`
- **Fields:** Full Name (`fmdb:inputText`), Email (`fmdb:inputEmail`), Phone (`gtf:inputPhone`), Preferred Date & Time (`fmdb:inputDatetimeLocal`), Backup Date (`fmdb:inputDate`), Meeting Format (`fmdb:select`), Campaign Source (`fmdb:inputHidden`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Demonstrates date/time pickers, international phone inputs, and hidden tracking fields.

### 8. Event Registration (Catalog)
- **Page Path:** `/sites/prism/home/forms-catalog/event-registration`
- **Fields:** 2 Steps (`personal` → `preferences`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Multi-step registration flow showing guest options and conditional dietary/accessibility questions.

### 9. Product Survey (Catalog)
- **Page Path:** `/sites/prism/home/forms-catalog/product-survey`
- **Fields:** Product (`fmdb:select`), Satisfaction (`gtf:inputRating`), Liked Features (`fmdb:checkbox`), Aspect Rating Matrix (`gtf:inputMatrix`), Main Complaint (`fmdb:select`), Improvements (`fmdb:textarea`), Recommendation (`fmdb:radio`), Follow-up (`fmdb:textarea`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Feedback & NPS evaluation showcasing custom star ratings and rating matrices.

### 10. UX Research Survey (Catalog)
- **Page Path:** `/sites/prism/home/forms-catalog/ux-research-survey`
- **Fields:** 2 Steps (`discovery` → `preferences`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Multi-step survey featuring interactive drag-to-rank feature prioritization (`gtf:inputRanking`) and color selection.

### 11. Job Application (Catalog)
- **Page Path:** `/sites/prism/home/forms-catalog/job-application`
- **Fields:** 3 Steps (`profile` → `details` → `confirmation`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`) & triggers dual emails (`gtf:emailTemplateAction` to candidate and HR)
- **Purpose:** Multi-step job application funnel with role-based branching and automated notification dispatch.

### 12. Insurance Quote (Catalog)
- **Page Path:** `/sites/prism/home/forms-catalog/insurance-quote`
- **Fields:** 4 Steps (`your-situation` → `coverage` → `budget-priorities` → `confirmation`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Advanced insurance risk wizard utilizing image options (`gtf:inputImageChoice`), budget sliders, summary recap, and sub-form embedding.

### 13. Quote Request
- **Page Path:** `/sites/prism/home/pricing/quote`
- **Fields:** Service (`fmdb:select`), Pages (`fmdb:inputText`), CMS (`fmdb:select`), Site URL (`fmdb:inputText`), Project Goals (`fmdb:textarea`), Form Style (`gtf:formStyle`)
- **Actions:** Saves to JCR (`fmdb:save2jcrAction`)
- **Purpose:** Commercial pricing estimation form for prospective web projects.

---

## 5. Local Docker Stack Services

| Service              | URL                                | Purpose                                   |
|-----------------------|-------------------------------------|--------------------------------------------|
| `jahia`               | https://prism.localhost             | Jahia application                          |
| `mailpit`              | https://mailpit.localhost           | Mail catcher (web UI) — SMTP on `:1025`    |
| `reverse-proxy`        | https://traefik.localhost/dashboard/ | Traefik dashboard (note the trailing slash) |
| `postgres`             | internal only                       | Jahia database                             |
| `provisioning-render`  | one-shot, exits after rendering     | Builds the final provisioning script       |

All `*.localhost` hosts resolve locally without any `/etc/hosts` changes on most systems (Chrome, Firefox, and modern OSes resolve `.localhost` to `127.0.0.1` automatically).

---

## 6. Prerequisites

- Docker and Docker Compose v2
- Ports `80`, `443`, `8080`, `8025`, `1025`, `9229` free on the host

---

## 7. Getting Started

```bash
cp env.example .env
# edit .env: set MCP_API_TOKEN, adjust MCP_ENABLED/passwords as needed
docker compose up
```

Then visit https://prism.localhost. The self-signed dev certificate will trigger a browser warning the first time — that's expected for local TLS.

---

## 8. Configuration (`.env`)

Every value below has a sane default baked into `docker-compose.yml` — `.env` only needs to override what you actually care about. `env.example` lists all of them explicitly for discoverability.

**Jahia**

| Variable             | Default        | Description                                                            |
|-----------------------|----------------|---------------------------------------------------------------------------|
| `SUPER_USER_PASSWORD` | `root1234`     | Jahia root/admin password                                              |
| `TIMEZONE`            | `Europe/Paris` | Container timezone                                                     |
| `MCP_ENABLED`         | `false`        | When `true`, installs the MCP module and creates an API token on startup |
| `MCP_API_TOKEN`       | —              | Token value used when creating the MCP API token (only used if MCP enabled) |
| `JPDA`                | `true`         | Enables the Java remote debugger (separate from `JAHIA_DEBUG_PORT`, which is the Node/Chrome inspector) |

**Database**

| Variable  | Default      | Description             |
|------------|--------------|--------------------------|
| `DB_NAME`  | `jahia`      | Postgres database name  |
| `DB_USER`  | `jahia`      | Postgres user            |
| `DB_PASS`  | `dbpassword` | Postgres password        |

**Mail** (internal Docker network hostnames — different from `MAILPIT_HOST`, which is the *external* Traefik-routed host)

| Variable          | Default   | Description                                   |
|--------------------|-----------|-------------------------------------------------|
| `MAIL_SMTP_HOST`   | `mailpit` | SMTP host Jahia connects to (Compose service name) |
| `MAIL_SMTP_PORT`   | `1025`    | SMTP port Jahia connects to (container-internal port) |

**Hostnames** (all resolve locally without `/etc/hosts` changes on most systems)

| Variable       | Default            | Description                    |
|-----------------|--------------------|----------------------------------|
| `JAHIA_HOST`    | `prism.localhost`   | Host routed to the Jahia UI     |
| `MAILPIT_HOST`  | `mailpit.localhost` | Host routed to the Mailpit UI   |
| `TRAEFIK_HOST`  | `traefik.localhost` | Host routed to the Traefik dashboard |

**Ports**

| Variable            | Default | Description                     |
|----------------------|---------|-----------------------------------|
| `HTTP_PORT`           | `80`    | Traefik HTTP entrypoint          |
| `HTTPS_PORT`          | `443`   | Traefik HTTPS entrypoint         |
| `JAHIA_HTTP_PORT`     | `8080`  | Jahia HTTP port (also exposed directly) |
| `JAHIA_DEBUG_PORT`    | `9229`  | Node/Chrome DevTools inspector port (`chrome://inspect`) |
| `MAILPIT_UI_PORT`     | `8025`  | Mailpit web UI                   |
| `MAILPIT_SMTP_PORT`   | `1025`  | Mailpit SMTP listener            |

**Image versions**

| Variable          | Default                          |
|--------------------|-----------------------------------|
| `JAHIA_IMAGE`      | `achaabni/jahia-prism:0.1.1`      |
| `POSTGRES_IMAGE`   | `postgres:16`                     |
| `MAILPIT_IMAGE`    | `axllent/mailpit:latest`          |
| `TRAEFIK_IMAGE`    | `traefik:v3.7.6`                  |
| `ALPINE_IMAGE`     | `alpine:3.20`                     |

`.env` is gitignored — never commit real credentials. Use `env.example` as the template for what needs to be set.

---

## 9. How Provisioning Works

Jahia's Provisioning API has no built-in conditionals, so this project renders the final `provisioning.yaml` at container startup instead of using a static file:

1. `provisioning-render` (a one-shot Alpine container) reads [`jahia/provisioning/base.yaml`](jahia/provisioning/base.yaml) and, if `MCP_ENABLED=true`, appends [`jahia/provisioning/mcp.yaml`](jahia/provisioning/mcp.yaml).
2. It writes the result — plus the Groovy scripts from `jahia/provisioning/scripts/` — into a shared `provisioning` named volume.
3. The `jahia` service waits for `provisioning-render` to complete, then mounts that volume and runs `EXECUTE_PROVISIONING_SCRIPT` against the rendered `provisioning.yaml`.

To change what gets provisioned, edit `base.yaml` / `mcp.yaml` (always-run vs. MCP-gated steps) rather than a single flat file.

---

## 10. Project Structure

```
.
├── docker-compose.yml
├── .env                          # local secrets (gitignored)
├── env.example                   # template for .env
├── jahia/
│   ├── render-provisioning.sh    # builds provisioning.yaml from fragments
│   └── provisioning/
│       ├── base.yaml             # steps that always run
│       ├── mcp.yaml              # steps run only when MCP_ENABLED=true
│       └── scripts/
│           ├── activate-mail-server.groovy
│           └── create-token.groovy   # reads MCP_API_TOKEN from env
└── traefik/
    ├── tls.yaml                  # TLS store config for traefik
    └── certs/
        ├── generate.sh           # regenerates the self-signed localhost cert
        ├── localhost.crt
        └── localhost.key         # gitignored
```

---

## 11. Common Tasks

```bash
docker compose up -d              # start the stack in the background
docker compose logs -f jahia      # tail Jahia logs
docker compose down               # stop the stack
docker compose down -v            # stop and wipe volumes (Postgres data, rendered provisioning)
```

To toggle MCP after the stack has already been provisioned once, update `MCP_ENABLED` in `.env` and recreate the `jahia` container:

```bash
docker compose up -d --force-recreate jahia provisioning-render
```
