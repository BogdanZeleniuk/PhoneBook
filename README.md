Online web application project that gives you the possibility to work with your 
contacts (server side is built with Maven, Spring (Boot, Security, Data), 
Hibernate(ORM, Validator), client side is built with Bootstrap, JQuery; testing with JUnit, 
Spring Testing; implementation of conservation in MySQL database) 
Please choose profiles you want to use (in Profiles.class, application.properties),
set @EnableAutoConfiguration (if you use MySQL and JPA profiles) or @EnableAutoConfiguration
(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, 
TransactionAutoConfiguration.class}) (if you use File and MOCK profiles) and run application.
