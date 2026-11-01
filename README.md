# ifs18005-springboot-todolist

Aplikasi web Todo List berbasis **Spring Boot**, **Maven**, dan **Vaadin**, dengan arsitektur Clean Architecture dari proyek referensi konsol (`ifs18005-java-exception-generic/todo-list`).

Data disimpan **in-memory** (belum memakai database). Konfigurasi memakai `application.yaml`.

## Prasyarat

- JDK 25
- Maven Wrapper (`mvnw` / `mvnw.cmd`) sudah tersedia di proyek

## Struktur Layer

```text
src/main/java/org/delcom/ifs18005springboottodolist/
├── domain/          # Entity, exception, repository port
├── usecase/         # TodoUseCase
├── adapter/         # InMemoryRepository, TodoRepository, TodoPresenter
├── framework/view/  # TodoView (Vaadin UI)
├── config/          # AppConfig (composition root Spring)
└── App.java
```

## Perintah Build & Menjalankan

### 1. Build proyek

```powershell
.\mvnw.cmd clean package
```

### 2. Build + uji + cek coverage Jacoco 100%

```powershell
.\mvnw.cmd clean verify
```

Laporan coverage: `target/site/jacoco/index.html`

### 3. Menjalankan aplikasi (Spring Boot DevTools)

**Spring Boot DevTools** sudah aktif: aplikasi auto-restart + browser live-reload setelah class ter-compile ulang.

**Opsi A — dari Cursor (disarankan)**  
1. Pasang **Extension Pack for Java**  
2. Pastikan `java.autobuild.enabled` aktif (sudah di `.vscode/settings.json`)  
3. Jalankan konfigurasi **TodoList (DevTools)** dari Run and Debug  
4. Simpan file `.java` / resource → autobuild menulis ke `target/classes` → DevTools restart

**Opsi B — Maven**

```powershell
.\mvnw.cmd spring-boot:run
```

Kalau autobuild IDE tidak jalan, pakai watcher compile:

```powershell
.\dev.ps1
```

Buka browser: [http://localhost:8080](http://localhost:8080)

Ringkas alurnya: **edit → compile → DevTools restart → LiveReload browser**.

### 4. Build & jalankan JAR (wajib `-Pproduction`)

Vaadin di JAR butuh frontend production. Tanpa `-Pproduction`, `java -jar` gagal dengan error `vaadin-dev-server not found`.

```powershell
.\mvnw.cmd clean package -Pproduction
java -jar target\ifs18005-springboot-todolist-0.0.1-SNAPSHOT.jar
```

> Catatan: `vaadin-dev` dan DevTools **tidak** ikut ke JAR (`optional` / `excludeDevtools=true`). Untuk development sehari-hari pakai `spring-boot:run`, bukan `java -jar`.

## Konfigurasi port (`.env`)

Salin `.env.example` menjadi `.env` lalu atur port:

```env
APP_PORT=8080
```

`application.yaml` memakai `server.port: ${APP_PORT:8080}` (default 8080 jika `.env` tidak ada).

## Fitur UI

- Tambah, ubah, hapus todo
- Cari berdasarkan judul
- Urutkan lewat klik header kolom
- Ringkasan jumlah todo / selesai / belum selesai
- Tanggal dibuat, tanggal selesai, dan durasi
- Penyimpanan sementara di memori (reset saat aplikasi di-restart)
