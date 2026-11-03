# abdullah_ubaid-pbo2026-springboot-todolist

Aplikasi web Todo List berbasis **Spring Boot**, **Maven**, dan **Vaadin**, dengan arsitektur **Clean Architecture**.

Data disimpan **in-memory** (belum memakai database). Port dikonfigurasi lewat `.env` (`APP_PORT`).

> **Penamaan proyek mahasiswa:** `{username}-pbo2026-springboot-todolist`  
> **Package:** `org.delcom.{username}pbo2026springboottodolist`  
> Contoh di repo ini memakai username `abdullah_ubaid`. Ganti sesuai username Anda.

## Prasyarat

- JDK 25
- Maven Wrapper (`mvnw` / `mvnw.cmd`)

## Struktur Layer

```text
abdullah_ubaid-pbo2026-springboot-todolist/
├── .env / .env.example
├── pom.xml
├── src/main/java/org/delcom/abdullah_ubaidpbo2026springboottodolist/
│   ├── App.java              # entry point (@SpringBootApplication)
│   ├── DotEnv.java           # muat .env ke system properties
│   ├── config/AppConfig.java # composition root Spring (@Bean)
│   ├── domain/               # entity, exception, repository port
│   ├── usecase/              # TodoUseCase
│   ├── adapter/              # InMemoryRepository, TodoRepository, TodoPresenter
│   └── framework/view/       # TodoView (Vaadin)
├── src/main/resources/
│   └── application.yaml
└── src/test/java/.../
    └── AppTest.java
```

## Konfigurasi port (`.env`)

```env
APP_PORT=8080
```

`application.yaml` memakai:

```yaml
server:
  port: ${APP_PORT:8080}
```

Salin dari `.env.example` jika `.env` belum ada. File `.env` diabaikan git.

## Perintah Build & Menjalankan

### 1. Development (disarankan)

```powershell
.\mvnw.cmd spring-boot:run
```

Atau dari Cursor/VS Code: Run and Debug → **TodoList (DevTools)**.

Buka [http://localhost:8080](http://localhost:8080) (atau port di `APP_PORT`).

Spring Boot DevTools aktif: simpan file → compile → auto-restart + live-reload.

Kalau autobuild IDE tidak jalan:

```powershell
.\dev.ps1
```

### 2. Uji + coverage Jacoco 100%

```powershell
.\mvnw.cmd clean verify
```

Laporan: `target/site/jacoco/index.html`

### 3. Build & jalankan JAR (wajib `-Pproduction`)

Tanpa `-Pproduction`, `java -jar` gagal (`vaadin-dev-server not found`) karena `vaadin-dev` tidak ikut ke JAR.

```powershell
.\mvnw.cmd clean package -Pproduction
java -jar target\abdullah_ubaid-pbo2026-springboot-todolist-0.0.1-SNAPSHOT.jar
```

Jalankan dari root proyek agar file `.env` terbaca.

## Fitur UI

- Tambah, ubah, hapus todo
- Cari berdasarkan judul
- Urutkan lewat klik header kolom
- Ringkasan jumlah todo / selesai / belum selesai
- Tanggal dibuat, tanggal selesai, dan durasi
- Penyimpanan sementara di memori (reset saat aplikasi di-restart)
