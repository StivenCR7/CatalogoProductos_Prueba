# CatalogoProductos_Prueba

##  Arquitectura del Proyecto

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

##  Tecnologías Utilizadas

* **Backend:** Spring Boot (Java 17), Spring Security, Spring Data JPA, JWT (JSON Web Tokens).
* **Frontend:** Angular Standalone Components, TypeScript, HTML5 / CSS3.
* **Consumo de API Externa:** `RestTemplate` de Spring Framework.
* **Base de Datos & Herramientas:** MySQL Workbench, BCrypt Password Encoder.
* **Entorno de Ejecución:** Node.js v24, JDK 17+.

---

##  Requisitos Previos y Entorno

* **Java Development Kit (JDK):** Versión 17 o superior.
* **Node.js:** Versión 24.x.
* **MySQL Server & Workbench:** Ejecutándose localmente en el puerto `3306`.

---
##  Creación y Configuración Automática de Base de Datos

No es necesario crear manualmente la base de datos en MySQL Workbench antes de iniciar el proyecto.

Gracias al parámetro `createDatabaseIfNotExist=true` configurado en la cadena de conexión JDBC de `application.properties`, el driver de MySQL detecta al arrancar el backend si la base de datos `db_store_api` existe; de no ser así, la **crea automáticamente** en el servidor local.

Asimismo, mediante Hibernate JPA (`spring.jpa.hibernate.ddl-auto=update`), las tablas (`producto`, `categoria`, `usuario`) se generan automáticamente en la base de datos junto con el registro inicial del usuario administrador con su contraseña cifrada mediante **BCrypt**.

##  Pasos de Ejecución

### 1. Backend (Spring Boot)
1. Abre la carpeta `BackendStoreApi` en tu IDE de preferencia (STS, IntelliJ IDEA, VS Code).
2. Configura las credenciales de tu base de datos MySQL en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/fakestoredb?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=tu_contrasena
   spring.jpa.hibernate.ddl-auto=update
   
   # Configuración JWT (15 minutos de expiración)
   security.config.expiration=900000
   security.config.prefix=Bearer
   security.config.key=45645612315456486745!$$$

<img width="489" height="348" alt="image" src="https://github.com/user-attachments/assets/e63cc2fd-4144-42af-a094-8ffc067bbf12" />
<img width="844" height="524" alt="image" src="https://github.com/user-attachments/assets/03d8a741-2b2e-4a26-b729-a60dca064e50" />
<img width="1067" height="602" alt="image" src="https://github.com/user-attachments/assets/24927f6c-5ae6-432f-bc54-17ec28196c95" />
<img width="1038" height="659" alt="image" src="https://github.com/user-attachments/assets/6131c87c-cac1-484d-bd50-8182d91cfbfc" />
   


   
