# HeatNews
It's a web server that designed for posting articles.

## What is it?
This is a server that allows users to post articles.
This server was written on java.
It was created with help of the Spring Framework, Apache Tomcat, Thymeleaf Template and Hibernate.
As a database was used PostgreSQL.

## What features have been implemented?
Registration and authorization systems were implemented to the server.
When registering, the fields are checked for validity.
The server is secured by Spring Security. Some actions are prohibited for unlogged users or users that have no permissions to this actions.
The server uses role system. Each user has their own role(user or admin).

All articles are available to watch by every guest as well as logged users.
Every logged user has ability to post, edit, delete their own articles.
Every logged user has ability to like articles(one like to a single article from a single user).

All the information is stored in PostgreSQL database.
To work with database the server uses JPA.
