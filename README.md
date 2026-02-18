# 🛒 E-Commerce Application – Spring Boot & Vue 3

Moderne **E-Commerce Webanwendung** mit **Spring Boot (Java 21, Reactive)** im Backend und **Vue 3 + TypeScript** im Frontend.  
Der Fokus liegt auf **skalierbarer Architektur**, **Full-Text-Suche** und **klarer Trennung von Domänen**.

---

## Produkt List:
<img width="1466" height="831" alt="Bildschirmfoto 2026-02-09 um 13 18 03" src="https://github.com/user-attachments/assets/36346612-5ffe-461e-a7fb-c28559dbae8f" />


---

## Produkt Detail:
<img width="1383" height="828" alt="Bildschirmfoto 2026-02-05 um 12 05 05" src="https://github.com/user-attachments/assets/e3a6ee14-d7e2-47d7-81b2-fe4c7fbd1443" />


---

## S3-Objekt Store:
<img width="1435" height="788" alt="Bildschirmfoto 2026-02-05 um 12 05 37" src="https://github.com/user-attachments/assets/925d76ff-3382-4575-9337-0f545fa77502" />


---

## Backend-Produkt List:
<img width="1089" height="875" alt="Bildschirmfoto 2026-02-05 um 12 05 56" src="https://github.com/user-attachments/assets/ae272a48-b7a3-4c28-b5b3-b298b94612cf" />


---

## Elasticsearch:
<img width="996" height="777" alt="Bildschirmfoto 2026-02-05 um 12 08 13" src="https://github.com/user-attachments/assets/b65118ac-2cde-4b7e-866a-de66d9b62c03" />


---

## MongoDB:
<img width="1442" height="740" alt="Bildschirmfoto 2026-02-05 um 12 09 07" src="https://github.com/user-attachments/assets/882ee50e-d04f-4dd4-a8e5-68e6f1231787" />








## 🧩 Tech Stack

### Backend
- Java 21
- Spring Boot (WebFlux / Reactive)
- Spring Data MongoDB
- Spring Data Elasticsearch
- Spring Data Redis
- Docker & Docker Compose

### Frontend
- Vue 3
- TypeScript
- Vue Router
- Axios
- Bootstrap

---

## 🏗️ Architektur


Frontend (Vue 3)
↓
Spring Boot API (Reactive)
↓
| MongoDB | Elasticsearch | Redis |


### Datenbanken
- **MongoDB** → Hauptdatenbank (Produkte, Accounts, Bestellungen)
- **Elasticsearch** → Full-Text-Suche (ohne Login)
- **Redis** → Temporärer Warenkorb (TTL)

---

## 📦 Domänen

### Produkt
- Produktdetails
- Bilder
- Kategorien
- Preise
- Volltextsuche (Elasticsearch)

### Warenkorb
- Bestellungen
- Versand
- Zahlung
- Lager
- Gast- & User-Warenkorb (Redis)

### Account
- Profil
- Bestellverwaltung
- Favoriten

> Die Architektur ist **erweiterbar** – neue Domänen können einfach ergänzt werden.

---

## ⚙️ Backend – Setup

### Dependencies
- Spring Boot DevTools
- Lombok
- Spring WebFlux
- Spring Data MongoDB (Reactive)
- Spring Data Elasticsearch
- Spring Data Redis

### Projektstruktur (Beispiel: Produkt-Domäne)

/produkt
- ├─ controller/
- ├─ entity/
- ├─ model/
- ├─ repo/
- └─ service/


✔️ **Entities werden nicht direkt zurückgegeben**  
✔️ Kommunikation über Response-Modelle (`ProduktResponse`)

---

## 🐳 Docker Setup

MongoDB, Elasticsearch und Redis laufen über über Docker Compose.

bash command:
cd src/main/resources
docker compose up -d


---
## 🧪 Demo-Daten

Beim Start der Anwendung werden automatisch 20 Beispielprodukte erzeugt.

StartDemo/
 - └─ ProduktDemoData.java

---
## 🎨 Frontend – Setup
Installation:

npm install -g @vue/cli
vue create webui
cd webui
yarn serve


Frontend läuft unter:

http://localhost:8080

---
## 🔗 API-Kommunikation (Axios)

yarn add axios vue-axios


main.js

import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

axios.defaults.baseURL = 'http://localhost:8080'
createApp(App).use(VueAxios, axios).mount('#app')

---
## 🧭 Routing

Produktliste

Produktdetails
Command:

vue add router


---
## 🛠️ Vue Best Practices

| Zweck            | Vue Feature |
| ---------------- | ----------- |
| State            | `data()`    |
| Berechnete Werte | `computed`  |
| Logik            | `methods`   |
| Listen           | `v-for`     |

---
## 🧾 Produktmodell (Kurz)

id

name

description

image

price

moneySymbol (Enum)

category

features

seller

available

freeDelivery

deliveryIn

✔️ Preise in separater Collection
✔️ Lieferzeit & Versandkosten per Service berechnet
✔️ Suche über Elasticsearch

---
## 🗃️ File Storage (MinIO / S3)

Produktbilder werden über S3-kompatiblen Storage (MinIO) gespeichert.

application.properties

minio.s3.access-key=root
minio.s3.secret-key=secret
minio.s3.endpoint=http://localhost:9000


---
## 🚀 Ziel des Projekts

Parallele Entwicklung von Frontend & Backend

Saubere, skalierbare E-Commerce-Architektur

Moderne Technologien (Reactive, Docker, Vue 3)

Realistische Shop-Funktionalität inkl. Suche & Warenkorb

