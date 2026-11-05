# Relaciones entre entidades en Spring Boot con JPA e Hibernate

JPA permite representar las relaciones entre tablas mediante anotaciones
ubicadas en las propiedades de las entidades. Hibernate implementa estas
relaciones y se encarga de convertirlas en claves foráneas y operaciones
SQL.

La regla principal es:

-   `@ManyToOne`: muchos registros pertenecen a uno.
-   `@OneToMany`: uno puede relacionarse con muchos.
-   `@OneToOne`: un registro se relaciona con uno.
-   `@ManyToMany`: muchos registros se relacionan con muchos. Cuando la
    asociación tiene datos propios, conviene representarla con una
    entidad intermedia.

En los ejemplos se utiliza `Long` como tipo de identificador y se omiten
getters, setters y constructores para concentrarnos en las relaciones.

------------------------------------------------------------------------

## 1. Relación uno a muchos (1:N): Customers y Orders

Un cliente puede realizar varios pedidos, pero cada pedido pertenece a
un único cliente.

### Representación en la base de datos

**Tabla `customers`**

    customer_id (PK) name         email
  ------------------ ------------ ----------------
                   1 Ana Torres   ana@email.com
                   2 Luis Pérez   luis@email.com

**Tabla `orders`**

    order_id (PK)   customer_id (FK) order_date     total_price
  --------------- ------------------ ------------ -------------
              101                  1 2026-10-01          150000
              102                  1 2026-10-05           85000
              103                  2 2026-10-07          230000

La clave foránea `customer_id` se encuentra en `orders`. Por eso, en
JPA, el lado que contiene la clave foránea se representa con
`@ManyToOne`.

### Entidad `Customer`

``` java
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
```

### Entidad `Order`

``` java
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private LocalDate orderDate;
    private BigDecimal totalPrice;
}
```

**¿Qué indican las anotaciones?**

-   `@OneToMany`: un cliente puede tener muchos pedidos.
-   `mappedBy = "customer"`: indica que la relación se administra desde
    la propiedad `customer` de la entidad `Order`. No crea otra columna.
-   `@ManyToOne`: muchos pedidos pueden pertenecer al mismo cliente.
-   `@JoinColumn(name = "customer_id")`: define la columna que funciona
    como clave foránea en `orders`.
-   `nullable = false`: indica que el pedido debe tener un cliente
    asociado.

En esta relación, `Order` es el lado propietario porque contiene la
clave foránea. Para establecer la relación en el código, se asigna el
cliente al pedido.

------------------------------------------------------------------------

## 2. Relación uno a uno (1:1): User y UserProfile

Cada usuario tiene un único perfil y cada perfil pertenece a un único
usuario.

### Representación en la base de datos

**Tabla `users`**

    user_id (PK) username   password
  -------------- ---------- ----------
               1 atorres    hash_abc
               2 lperez     hash_xyz

**Tabla `user_profiles`**

    profile_id (PK)   user_id (FK, UNIQUE) full_name    phone
  ----------------- ---------------------- ------------ ------------
                 10                      1 Ana Torres   3001112233
                 11                      2 Luis Pérez   3004445566

La columna `user_id` de `user_profiles` es una clave foránea y además es
única. Esto evita que dos perfiles estén asociados al mismo usuario.

### Entidad `User`

``` java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;

    @OneToOne(mappedBy = "user")
    private UserProfile profile;
}
```

### Entidad `UserProfile`

``` java
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    private String fullName;
    private String phone;
}
```

**¿Qué indican las anotaciones?**

-   `@OneToOne`: establece una relación uno a uno.
-   `@JoinColumn(name = "user_id")`: crea o identifica la clave foránea
    en `user_profiles`.
-   `unique = true`: garantiza que un usuario no aparezca asociado a
    varios perfiles.
-   `mappedBy = "user"`: indica que `UserProfile` es el lado propietario
    de la relación.

La clave foránea se ubica en una sola de las tablas. En este ejemplo
está en `user_profiles`.

------------------------------------------------------------------------

## 3. Relación muchos a muchos (N:M): Students y Courses

Un estudiante puede inscribirse en varios cursos y un curso puede tener
varios estudiantes.

Como la inscripción tiene un dato propio (`registration_date`), se crea
una entidad intermedia llamada `StudentCourse`. En lugar de utilizar
directamente `@ManyToMany`, se modela la tabla de unión como una
entidad.

### Representación en la base de datos

**Tabla `students`**

    student_id (PK) name          email
  ----------------- ------------- ------------------
                  1 María Gómez   maria@email.com
                  2 Carlos Ruiz   carlos@email.com

**Tabla `courses`**

    course_id (PK) name             description
  ---------------- ---------------- ------------------------
                10 Java             Programación con Java
                20 Bases de datos   Diseño y consultas SQL

**Tabla `student_courses`**

    student_id (PK, FK)   course_id (PK, FK) registration_date
  --------------------- -------------------- -------------------
                      1                   10 2026-09-01
                      1                   20 2026-09-03
                      2                   10 2026-09-04

En este diseño, `student_id` y `course_id` forman una clave primaria
compuesta. Cada fila representa la inscripción de un estudiante en un
curso.

### Entidad `Student`

``` java
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> enrollments = new ArrayList<>();
}
```

### Entidad `Course`

``` java
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> enrollments = new ArrayList<>();
}
```

### Clave primaria compuesta: `StudentCourseId`

``` java
@Embeddable
public class StudentCourseId implements Serializable {

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;

    // equals() y hashCode() deben implementarse.
}
```

### Entidad intermedia `StudentCourse`

``` java
@Entity
@Table(name = "student_courses")
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate registrationDate;
}
```

**¿Qué indican las anotaciones?**

-   `@Embeddable`: declara una clase cuyos campos pueden formar parte de
    la clave de otra entidad.
-   `@EmbeddedId`: utiliza un objeto como clave primaria compuesta.
-   `@ManyToOne`: cada inscripción pertenece a un estudiante y a un
    curso.
-   `@MapsId`: vincula la clave foránea de la relación con el campo
    correspondiente de la clave compuesta.
-   `@JoinColumn`: identifica la columna de clave foránea.
-   `@OneToMany`: permite consultar las inscripciones desde un
    estudiante o desde un curso.

La relación N:M original queda convertida en dos relaciones 1:N:

1.  Un estudiante puede tener muchas inscripciones.
2.  Un curso puede tener muchas inscripciones.

La entidad intermedia permite guardar información adicional de la
asociación, como la fecha de inscripción.

------------------------------------------------------------------------

## 4. Resumen de las anotaciones principales

  -----------------------------------------------------------------------
  Anotación                           Función
  ----------------------------------- -----------------------------------
  `@OneToOne`                         Relaciona una entidad con una única
                                      instancia de otra entidad.

  `@OneToMany`                        Relaciona una entidad con varias
                                      instancias de otra.

  `@ManyToOne`                        Indica que varias instancias pueden
                                      pertenecer a una misma entidad.

  `@ManyToMany`                       Representa una relación de muchos a
                                      muchos mediante una tabla de unión.

  `@JoinColumn`                       Define la columna que almacena la
                                      clave foránea.

  `mappedBy`                          Indica qué propiedad de la otra
                                      entidad administra la relación.

  `@JoinTable`                        Permite configurar explícitamente
                                      la tabla de unión en una relación
                                      `@ManyToMany`.

  `@EmbeddedId`                       Define una clave primaria compuesta
                                      mediante un objeto.

  `@Embeddable`                       Marca una clase que puede
                                      incorporarse como parte de otra
                                      entidad.

  `@MapsId`                           Vincula una relación con un campo
                                      de la clave primaria compuesta.
  -----------------------------------------------------------------------

## 5. Ideas clave para recordar

-   La clave foránea normalmente se encuentra en el lado `@ManyToOne` o
    en el lado propietario de una relación `@OneToOne`.
-   `mappedBy` se utiliza en el lado inverso: hace referencia al nombre
    de la propiedad Java que contiene la relación, no al nombre de la
    columna SQL.
-   `@JoinColumn` permite definir la columna de clave foránea.
-   En una relación bidireccional, ambos objetos deben mantenerse
    sincronizados en el código. Por ejemplo, al agregar un pedido a un
    cliente, también se debe asignar ese cliente al pedido.
-   Si una relación muchos a muchos necesita atributos propios, como
    fecha de inscripción, estado o calificación, es preferible crear una
    entidad intermedia en lugar de usar una relación `@ManyToMany`
    directa.

> **Nota:** Las tablas muestran una representación conceptual del
> esquema. La estructura final puede variar según la estrategia de
> generación de esquema, el motor de base de datos y la configuración de
> Hibernate.
