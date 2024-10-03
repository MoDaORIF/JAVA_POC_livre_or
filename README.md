# POC JAVA depedencies

## System depedency
- java 21
- maven 3.9.9
- spring-boot 3.3.3

### Java 21 => Linux arch-based
`sudo pacman -S jdk21-openjdk`
`JAVA_HOME` in zshrc => `sudo find /usr/ -name java-21-openjdk`

### Docker containerized mariadb
`docker exec -it <contaner_name> mariadb -u root -p`
`-p` is defined in compose.yaml

## Spring boot depedency (pom.xml)

### ORM
JPA ⇒ simple depedency
#### Source
https://spring.io/guides/gs/accessing-data-jpa#scratch

### REST API
#### Source
https://spring.io/guides/gs/accessing-data-rest





-------------------------------------------------------------------------------




# POC JAVA documentation
## Rename main file
The name doesn't matter. it just need to be in the root of the project e.g:
root/exemple/com/MY_FILE.java

The most important part is `@SpringBootApplication`, which tells Spring
where to start.

## MariaDB init (arch-based)

### Without docker
`sudo pacman -S mariadb`
`sudo mariadb-install-db --user=MySQL --basedir=/usr --datadir=/var/lib/mariadb`
`sudo systemctl start mariadb`
`sudo mariadb-admin -u root password [YOUR_PASSWORD]`
`mariadb -u root -p`
`[mariadb] # create database springbootdb;`

### With docker
Here’s a summary of the steps needed to make MariaDB work with Spring Boot and Docker:

    Docker Setup:
        Create a docker-compose.yml file to set up MariaDB:

        yaml

    version: '3.8'
    services:
      mariadb:
        image: 'mariadb:latest'
        environment:
          - MYSQL_ROOT_PASSWORD=root_password
          - MYSQL_DATABASE=mydatabase
          - MYSQL_USER=myuser
          - MYSQL_PASSWORD=mypassword
        ports:
          - "3307:3306"

Spring Boot Configuration:

    Update application.properties:

    properties

    spring.datasource.url=jdbc:mariadb://localhost:3307/mydatabase
    spring.datasource.username=myuser
    spring.datasource.password=mypassword
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update

Entity Configuration:

    Update your entity class to use GenerationType.IDENTITY for ID auto-increment:

    java

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // HERE
    private Integer id;

Database Creation:

    Log into the MariaDB container and create the required table:

    sql

        CREATE TABLE User (
          id INT AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(255),
          email VARCHAR(255)
        );

    Testing:
        Run your Spring Boot app and use curl to add and retrieve data from the database.


These steps ensure that MariaDB works smoothly with Spring Boot via Docker.

## Explications:

Le problème c'est que par défaut, java spring utilise par dwfaut des "séquences" au lieu d'auto incrémentation pour les ID dans la DB.
 
Sauf que mariaDB ne supporte pas les séquences.
 
donc, il faut spécifier manuellement dans le code avec l'annotation @GeneratedValue qu'on utilise de l'auto increment

curl http://localhost:8080/demo/add -d name=First -d email=someemail@someemailprovider.com
