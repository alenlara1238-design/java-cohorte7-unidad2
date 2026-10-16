# ¿Por qué existen las bases de datos relacionales?

Antes de aprender a conectar una aplicación Spring Boot con PostgreSQL, necesitamos comprender un problema fundamental: ¿cómo podemos organizar grandes cantidades de información para almacenarla, relacionarla y obtener respuestas útiles a partir de ella?

Imaginemos que estamos desarrollando una tienda virtual. La aplicación debe registrar clientes, almacenar productos, gestionar compras y permitir que el administrador conozca el comportamiento de las ventas.

## 1. El problema de almacenar toda la información en una sola tabla

Al principio, podríamos guardar toda la información en una sola tabla:

| Cliente     | Correo           | Producto |    Precio | Fecha de compra | Ciudad   |
| ----------- | ---------------- | -------- | --------: | --------------- | -------- |
| Laura Gómez | laura@email.com  | Portátil | 3.000.000 | 10/10/2026      | Bogotá   |
| Laura Gómez | laura@email.com  | Mouse    |    80.000 | 10/10/2026      | Bogotá   |
| Carlos Ruiz | carlos@email.com | Portátil | 3.000.000 | 11/10/2026      | Medellín |
| Laura Gómez | laura@email.com  | Teclado  |   150.000 | 12/10/2026      | Bogotá   |

Esta tabla parece sencilla, pero pronto aparecen problemas.

## 2. Problemas de almacenar la información de esta manera

- **Información repetida:** los datos de Laura se almacenan varias veces porque ha comprado diferentes productos.
- **Inconsistencias:** si Laura cambia su correo electrónico, tendríamos que actualizar todas las filas en las que aparece.
- **Dificultad para crecer:** una compra puede contener muchos productos y un producto puede aparecer en miles de compras.
- **Mayor riesgo de errores:** podríamos registrar el mismo cliente de diferentes maneras o asociar una compra con información incorrecta.

## 3. ¿Cuál es el verdadero problema?

El problema no es que una tabla sea incapaz de almacenar estos datos. El problema es que, a medida que el sistema crece, resulta más difícil mantener la información organizada y confiable.

## 4. La solución: dividir la información y establecer relaciones

Las bases de datos relacionales permiten organizar la información en tablas especializadas y establecer relaciones entre ellas.

En lugar de guardar todos los datos juntos, podemos diseñar cuatro tablas.

### Tabla: CLIENTES

| id_cliente | nombre      | ciudad   |
| ---------: | ----------- | -------- |
|          1 | Laura Gómez | Bogotá   |
|          2 | Carlos Ruiz | Medellín |

### Tabla: COMPRAS

| id_compra | id_cliente | fecha      |
| --------: | ---------: | ---------- |
|       101 |          1 | 10/10/2026 |
|       102 |          2 | 11/10/2026 |
|       103 |          1 | 12/10/2026 |

### Tabla: PRODUCTOS

| id_producto | nombre   |    precio |
| ----------: | -------- | --------: |
|          10 | Portátil | 3.000.000 |
|          20 | Mouse    |    80.000 |
|          30 | Teclado  |   150.000 |

### Tabla: DETALLES_COMPRA

| id_compra | id_producto | cantidad |
| --------: | ----------: | -------: |
|       101 |          10 |        1 |
|       101 |          20 |        2 |
|       102 |          10 |        1 |
|       103 |          30 |        1 |

### ¿Cómo se relacionan las tablas?

Los identificadores permiten relacionar los registros de las distintas tablas. En este ejemplo, `DETALLES_COMPRA` conecta las compras con los productos que contiene cada una.

Observemos qué hemos conseguido:

- **La información de cada cliente se almacena una sola vez.**
- Cada compra queda asociada a un cliente mediante `id_cliente`.
- Los productos se registran independientemente de las compras.
- Una compra puede incluir varios productos y un mismo producto puede estar presente en muchas compras.

### Conceptos fundamentales

Para conseguirlo utilizamos dos conceptos fundamentales:

**Llave primaria (Primary Key):** identifica de manera única cada registro de una tabla. Por ejemplo, `id_cliente` identifica a cada cliente.

**Llave foránea (Foreign Key):** permite relacionar una tabla con otra. Por ejemplo, `id_cliente` en `COMPRAS` hace referencia al cliente que realizó la compra.

Esta organización no solo facilita el almacenamiento. También nos permite hacer preguntas complejas sobre el negocio.

## 6. El verdadero poder: obtener información que no está almacenada en una sola tabla

Imaginemos que el administrador de la tienda nos hace esta pregunta:

> ¿Cuánto dinero han gastado los clientes de Bogotá en cada producto durante octubre de 2026, cuáles son los productos más vendidos y cuántas unidades se han comprado?

### ¿Qué información necesitamos?

Pensemos en lo que necesitamos para responder.

| Tabla             | Información necesaria                       |
| ----------------- | ------------------------------------------- |
| `CLIENTES`        | La ciudad del cliente.                      |
| `COMPRAS`         | La fecha de la compra.                      |
| `PRODUCTOS`       | El nombre y el precio de cada producto.     |
| `DETALLES_COMPRA` | Las cantidades adquiridas de cada producto. |

Ninguna de estas tablas contiene por sí sola toda la información necesaria. La respuesta aparece cuando relacionamos sus datos.

### ¿Qué hace la base de datos para responder?

La base de datos puede ejecutar una consulta que:

1. Relacione cada compra con el cliente que la realizó.
2. Relacione cada detalle de compra con el producto correspondiente.
3. Filtre únicamente los clientes de Bogotá y las compras de octubre.
4. Calcule el dinero gastado multiplicando el precio por la cantidad.
5. Agrupe los resultados por producto.
6. Ordene los productos desde el que generó más ingresos hasta el que generó menos.

### Resultado de la consulta

El resultado podría ser el siguiente:

| Producto | Unidades vendidas | Ingresos |
| -------- | ----------------: | -------: |
| Mouse    |                 2 | $160.000 |
| Teclado  |                 1 | $150.000 |

En este ejemplo, solo se consideran las compras de Laura, porque es la única cliente de Bogotá.

Aunque la consulta necesita combinar información de cuatro tablas, el administrador recibe un resultado sencillo que le ayuda a tomar decisiones.

El próximo paso es aprender a crear estas tablas y a relacionarlas en PostgreSQL, para luego conectarlas con una aplicación Spring Boot. Con esto, la arquitectura de nuestros proyectos quedarían así:

![Diagrama de arquitectura](/clase1/assets/parte1/1-arquitectura.jpg)

La arquitectura que ya conocen no desaparece. Lo que cambia es la manera en que el repositorio accede a los datos.

Más adelante, podrán desarrollar una API que no solo registre productos, sino que también responda preguntas como:

- ¿Cuáles son los cinco productos más vendidos?
- ¿Qué clientes han comprado más durante el último mes?
- ¿Cuánto dinero se ha vendido en cada ciudad?
- ¿Qué productos nunca se han vendido?
- ¿Cuál es el promedio de compra por cliente?

Estas preguntas requieren relacionar información y aplicar operaciones de consulta y análisis. PostgreSQL permite realizarlas con SQL, sin tener que implementar manualmente toda la lógica de procesamiento en Java.

# Guía práctica: instalación de PostgreSQL y creación de las primeras tablas

PostgreSQL es un sistema gestor de bases de datos relacionales. Nos permite almacenar información de forma persistente, definir tablas y ejecutar consultas SQL.

## 1. Herramientas que utilizaremos

Para esta clase utilizaremos dos herramientas:

| Herramienta | Función                                                                                                                                                                  |
| ----------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| PostgreSQL  | Es el sistema que administra y almacena los datos.                                                                                                                       |
| pgAdmin 4   | Es una herramienta gráfica que permite conectarse a PostgreSQL, crear bases de datos, ejecutar SQL y consultar resultados sin necesidad de instalar un editor adicional. |

## 2. PostgreSQL

PostgreSQL es el motor de base de datos. Se ejecuta como un servicio en el equipo y administra las operaciones de almacenamiento y consulta.

**Descarga oficial:** [PostgreSQL para Windows](https://www.postgresql.org/download/windows/)

## 3. pgAdmin 4

pgAdmin 4 es la interfaz gráfica con la que administraremos el servidor y escribiremos nuestras primeras instrucciones SQL.

**Sitio oficial:** [Descargar pgAdmin 4](https://www.pgadmin.org/download/)

## 4. Diferencia entre PostgreSQL y pgAdmin 4

| Característica                  | PostgreSQL                       | pgAdmin 4                                             |
| ------------------------------- | -------------------------------- | ----------------------------------------------------- |
| Tipo de herramienta             | Sistema gestor de bases de datos | Interfaz gráfica de administración                    |
| Almacena los datos              | Sí                               | No; permite administrarlos a través de PostgreSQL     |
| Ejecuta consultas SQL           | Sí                               | Permite escribirlas y enviarlas al servidor           |
| Crea y administra tablas        | Sí                               | Permite hacerlo mediante su interfaz y el editor SQL  |
| Requiere el servidor PostgreSQL | Es el propio servidor gestor     | Sí, para conectarse y trabajar con sus bases de datos |

**Importante:** pgAdmin 4 no reemplaza a PostgreSQL. Es la herramienta que utilizaremos para interactuar visualmente con el sistema gestor de bases de datos.

## Paso 1. Descargar el instalador

1. Abre el sitio oficial de PostgreSQL: [Descargar PostgreSQL para Windows](https://www.postgresql.org/download/windows/).
2. En la página, selecciona el enlace del instalador para Windows proporcionado por EDB.
3. Descarga una versión estable compatible con Windows y con el equipo que utilizarás durante el curso.
4. Espera a que termine la descarga del archivo instalador.

**Recomendación:** utiliza la distribución oficial y evita descargar instaladores de páginas de terceros.

## Paso 2. Ejecutar el instalador

1. Busca el archivo descargado, normalmente en la carpeta `Descargas`.
2. Haz doble clic sobre el instalador.
3. Si Windows solicita autorización, verifica que el instalador provenga del distribuidor oficial y autoriza su ejecución.
4. Pulsa `Next` para continuar.

## Paso 3. Seleccionar los componentes

El instalador permite elegir los componentes que se instalarán. Para esta clase, selecciona:

- PostgreSQL Server.
- pgAdmin 4.
- Command Line Tools.

Si el instalador incluye Stack Builder, no lo necesitamos para esta práctica inicial.

Continúa con `Next`.

## Paso 4. Elegir la carpeta de instalación

Puedes conservar la ruta predeterminada que propone el instalador.

Por ejemplo, podría ser similar a:

```text
C:\Program Files\PostgreSQL\18
```

La carpeta y el número de versión pueden cambiar dependiendo del instalador descargado. No es necesario modificar esta configuración para la clase.

## Paso 5. Elegir la carpeta de datos

El instalador solicitará una carpeta para almacenar los archivos de la base de datos.

Conserva la ruta predeterminada, salvo que tengas una razón específica para cambiarla.

Esta carpeta es importante porque allí PostgreSQL mantiene físicamente los datos que administra.

**Importante:** no se debe borrar ni modificar manualmente su contenido mientras PostgreSQL está funcionando.

## Paso 6. Configurar la contraseña del usuario administrador

El instalador solicitará una contraseña para el usuario administrador de PostgreSQL, cuyo nombre habitual es `postgres`.

1. Define una contraseña que puedas recordar durante la clase.
2. Escríbela en el campo de contraseña.
3. Confírmala en el siguiente campo.
4. Guárdala de forma segura.

Esta contraseña no es la de Windows ni la de tu cuenta de pgAdmin. Es la contraseña del usuario administrador de PostgreSQL.

No la compartas con otras personas ni la publiques en repositorios de código.

## Paso 7. Configurar el puerto

El instalador normalmente propone el puerto `5432`.

Conserva ese valor, a menos que ya exista otra instalación de PostgreSQL que lo esté utilizando.

El puerto permite que las aplicaciones, como Spring Boot, se comuniquen con el servidor de base de datos.

| Configuración          | Valor habitual                              | Función                                                      |
| ---------------------- | ------------------------------------------- | ------------------------------------------------------------ |
| Usuario administrador  | `postgres`                                  | Permite administrar PostgreSQL según los permisos asignados. |
| Puerto                 | `5432`                                      | Permite establecer conexiones con el servidor.               |
| Carpeta de instalación | `C:\Program Files\PostgreSQL\18`            | Contiene los archivos de instalación del programa.           |
| Carpeta de datos       | La ruta seleccionada durante la instalación | Almacena físicamente los datos administrados por PostgreSQL. |

_Nota: los valores y las rutas pueden variar según la instalación._

## Paso 8. Seleccionar la configuración regional

Si el instalador muestra opciones de configuración regional, puedes conservar la configuración predeterminada para esta práctica.

Continúa hasta llegar al resumen de instalación.

## Paso 9. Instalar y finalizar

1. Revisa las opciones seleccionadas.
2. Pulsa `Next` o `Install`, según la pantalla.
3. Espera a que termine el proceso.
4. Finaliza el asistente.

Si aparece una opción para iniciar Stack Builder al terminar, puedes desmarcarla: no lo necesitamos hoy.

## Paso 10. Abrir pgAdmin 4

1. Abre el menú Inicio de Windows.
2. Busca `pgAdmin 4`.
3. Ejecuta la aplicación.
4. Si solicita una contraseña maestra para proteger las contraseñas guardadas en pgAdmin, crea una y guárdala de forma segura.
5. En el panel izquierdo, localiza `Servers`.
6. Expande el servidor PostgreSQL instalado.
7. Cuando lo solicite, introduce la contraseña del usuario `postgres` que definiste durante la instalación.

Si el servidor se conecta correctamente, ya tienes PostgreSQL funcionando y estás listo para trabajar.
