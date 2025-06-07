# BlackVault - Gestor de Contraseñas

BlackVault es una aplicación servidor desarrollada en Java con Spring Boot que permite a los usuarios almacenar y gestionar credenciales de forma segura. Utiliza una base de datos MySQL (compatible con Azure Database for MySQL) y proporciona endpoints REST protegidos mediante autenticación básica.

## Requisitos

- Java 17 o superior
- Maven 3.6+
- MySQL 8+ (o Azure Database for MySQL)
- (Opcional) Docker y Docker Compose

## Instalación

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/jinxAlex/password-manager-server
   cd server
   ```

2. **Configura la base de datos:**
   - Crea una base de datos llamada `blackvault` en tu servidor MySQL.
   - Crea un usuario y otórgale permisos, o usa las credenciales por defecto:
     - Usuario: `springuser`
     - Contraseña: `BOMfv2C%8vU7#Nx4ffIS3LTnK`

3. **Configura las variables de conexión:**
   - Edita `src/main/resources/application.properties` si necesitas cambiar la URL, usuario o contraseña de la base de datos.

## Ejecución

### Usando Maven

```bash
./mvnw spring-boot:run
```

### Usando Docker Compose (solo base de datos local)

```bash
docker-compose up -d
```

Luego ejecuta la aplicación como se indica en el apartado de Maven

## Endpoints principales

- `POST /user/signup` - Registro de usuario
- `POST /user/login` - Login de usuario
- `POST /credentials/add` - Añadir credencial (requiere autenticación)
- `POST /credentials/getall` - Obtener todas las credenciales (requiere autenticación)
- `POST /credentials/delete` - Eliminar una credencial (requiere autenticación)
- `POST /credentials/deleteAll` - Eliminar todas las credenciales (requiere autenticación)
- `POST /credentials/edit` - Editar una credencial (requiere autenticación)

## Descargar documentación

[Descargar documentación (ZIP)](https://github.com/jinxAlex/password-manager-server/blob/main/docs/javadoc.zip)
