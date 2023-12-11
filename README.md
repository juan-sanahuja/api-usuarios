# SpringBoot CRUD User Creation - README

This repository contains a sample Spring Boot CRUD application with Maven and Swagger for user creation.

## Setup

Siga estas instrucciones para configurar y ejecutar el proyecto:

1. Prerequisitos:
  - Java Development Kit (JDK) 11
  - Apache Maven
  - Git (optional)

2. Clona el repositorio localmente usando el siguiente comando:
   ```
   $ git clone https://github.com/juan-sanahuja/api-usuarios
   ```

3. Navegar al directorio del proyecto:
   ```
   $ cd api-usuarios-main
   ```

4. Build del proyecto utilizando Maven:
   ```
   $ mvn clean install
   ```

5. Run the application:
   ```
   $ mvn spring-boot:run
   ```

## Testing with Swagger

   Siga estos pasos para probar la aplicación usando Swagger:

1. Abra su navegador web y vaya a la siguiente URL:
   ```
   http://localhost:8080/swagger-ui.html
   ```
   
2. Verá la interfaz de usuario de Swagger con una lista de endpoints.

3. Si la solicitud requiere autenticación, siga la siguiente sección para agregar un token bearer.

## Agregar token bearer en Swagger (Importante!)

Para agregar un token bearer en el campo "Authorize" en Swagger, siga estos pasos:

1. Obtener un token del endpoint de **Registro** o **Autenticacion**.

2. En la interfaz de usuario de Swagger, haga clic en el botón **Authorize** ubicado en la esquina superior derecha.

3. Un cuadro de diálogo aparecerá. Ingrese la palabra "Bearer" seguida de un espacio y el token obtenido en el campo **Value**.

4. Haga clic en el botón **Authorize** para aplicar el token

5. Ahora, al enviar solicitudes que requieren autenticación, el token se agregará automáticamente al header de Autorización..

## Endpoints

Visualizar en swagger
