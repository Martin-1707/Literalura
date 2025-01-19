# 📚 **Literalura**

Este proyecto es una aplicación de consola en Java que permite gestionar una librería. Los usuarios pueden buscar libros, listar autores, filtrar libros por idioma y mucho más, interactuando a través de un menú dinámico y fácil de usar.

---

## ✨ **Características**
- Búsqueda de libros por título.
- Listado de libros y autores registrados.
- Consulta de autores vivos en un año específico.
- Filtrado de libros por idioma.
- Menú interactivo con opciones numeradas.
- Validación de entradas del usuario para evitar errores.

---

## 📝 **Menú de Opciones**
Al iniciar la aplicación, se muestra el siguiente menú principal:

![image](https://github.com/user-attachments/assets/be9f5ea4-bc5c-400b-9b4d-b40256e4d7ce)

### **Opciones Detalladas**
1. **Buscar libro por título**  
   Permite buscar un libro ingresando su título exacto o una palabra clave.  
2. **Listar libros registrados**  
   Muestra todos los libros almacenados en la base de datos.  
3. **Listar autores registrados**  
   Presenta una lista completa de los autores disponibles.  
4. **Listar autores vivos en un año específico**  
   Solicita un año y muestra los autores que estaban vivos en ese período.  
5. **Listar libros por idioma**  
   Filtra los libros por el idioma seleccionado.  
0. **Salir**  
   Finaliza la ejecución de la aplicación.

---

## 🛠️ **Requisitos**
Para ejecutar esta aplicación, necesitas tener lo siguiente:
- **Java 8 o superior**: Para compilar y ejecutar el código.
- **Hibernate (JPA)**: Para la conexión y gestión de datos en la base de datos.
- **PostgreSQL**: Base de datos utilizada para almacenar información de libros y autores.  
   - Versión recomendada: **PostgreSQL 13 o superior**.  
   - **Credenciales y configuración**:
     - Nombre de la base de datos: `literalura`
     - Usuario: `postgres` (o el usuario configurado en tu sistema).
     - Contraseña: `tu_password`
- Archivo de configuración: `persistence.xml` debe incluir los datos correctos para conectarse a tu instancia de PostgreSQL.

---

## 🎨 **Diseño del Menú**
El menú está diseñado para ser intuitivo y dinámico, permitiendo al usuario navegar entre las opciones sin dificultad. Se valida cada entrada para evitar errores y proporcionar una experiencia fluida.

---

## 🚀 **Cómo Ejecutar el Proyecto**
1. Clona o descarga este repositorio en tu máquina.
2. Configura la conexión a tu base de datos PostgreSQL en el archivo `persistence.xml`. Ejemplo de configuración:
   ```xml
   <properties>
       <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
       <property name="hibernate.connection.driver_class" value="org.postgresql.Driver"/>
       <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/literalura"/>
       <property name="hibernate.connection.username" value="postgres"/>
       <property name="hibernate.connection.password" value="tu_password"/>
       <property name="hibernate.hbm2ddl.auto" value="update"/>
   </properties>
