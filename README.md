# CatalogoProductos_Prueba

## 🏗️ Arquitectura del Proyecto

El proyecto está diseñado siguiendo una **arquitectura por capas** desacoplada tanto en el Backend como en el Frontend:

### **Backend (`BackendStoreApi`)**
* **`config`**: Configuración global de la aplicación (CORS, Beans, etc.).
* **`controller`**: Exposición de los endpoints REST (`/api/login`, `/api/productos`, `/api/catalogo`).
* **`dto`**: Objetos de transferencia de datos para solicitudes y respuestas.
* **`model`**: Entidades JPA (`Producto`, `Categoria`, `Usuario`) mapeadas hacia MySQL.
* **`repository`**: Capa de acceso a datos mediante Spring Data JPA.
* **`security`**: Filtros JWT, codificación de contraseñas con **BCrypt** y configuración de Spring Security.
* **`service`**: Lógica de negocio e integración con la API externa usando **`RestTemplate`**.

### **Frontend (`FrontedStoreApi`)**
* **`login`**: Componente modular para autenticación de usuarios.
* **`productos`**: Componente principal con grid dinámica, buscador por nombre/categoría y modal interactivo.
* **`Services`**: Servicios Angular para consumo de endpoints (`ProductoService`, `AuthService`, `authInterceptor`).

---

## 🛠️ Tecnologías Utilizadas

* **Backend:** Spring Boot (Java 17), Spring Security, Spring Data JPA, JWT (JSON Web Tokens).
* **Frontend:** Angular Standalone Components, TypeScript, HTML5 / CSS3.
* **Consumo de API Externa:** `RestTemplate` de Spring Framework.
* **Base de Datos & Herramientas:** MySQL Workbench, BCrypt Password Encoder.
* **Entorno de Ejecución:** Node.js v24, JDK 17+.

---

## ⚙️ Requisitos Previos y Entorno

* **Java Development Kit (JDK):** Versión 17 o superior.
* **Node.js:** Versión 24.x.
* **MySQL Server & Workbench:** Ejecutándose localmente en el puerto `3306`.

---

## 🚀 Pasos de Ejecución

### 1. Backend (Spring Boot)
1. Abre la carpeta `BackendStoreApi` en tu IDE de preferencia (STS, IntelliJ IDEA, VS Code).
2. Configura las credenciales de tu base de datos MySQL en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/db_store_api?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=tu_contrasena
   spring.jpa.hibernate.ddl-auto=update
   
   # Configuración JWT (15 minutos de expiración)
   security.config.expiration=900000
