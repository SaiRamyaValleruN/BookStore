# 📚 BookStore  

A **Java Swing + MySQL desktop application** for managing a bookstore.  
This app provides functionality for **users** (search & browse books, login) and **admins** (manage books, checkout, add inventory).  

---

# 🚀 Features  

## 👤 User  
- Register/Login with credentials  
- Search books by **title, author, or ISBN**  
- Browse available books  
- Checkout with simple form  

## 🛠️ Admin  
- Add, update, and delete books  
- Manage user records  
- View checkout history  

---

# 🏗️ Tech Stack  
- **Java Swing** – GUI framework  
- **MySQL** – Database  
- **JDBC (MySQL Connector)** – Database connectivity  
- **OOP Concepts** – Encapsulation, modular design  

---

# 📂 Project Structure  


## ⚙️ Setup & Installation  

1. **Clone this repository**  
   ```bash
   git clone https://github.com/SaiRamyaValleruN/BookStore.git
   cd BookStore
````

2. **Setup MySQL database**

   ```sql
   CREATE DATABASE bookstore;
   USE bookstore;

   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) NOT NULL,
       password VARCHAR(100) NOT NULL,
       role ENUM('user', 'admin') NOT NULL
   );

   CREATE TABLE BookDetails (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(100),
       author VARCHAR(100),
       isbn VARCHAR(50),
       publisher VARCHAR(100),
       publicationYear INT,
       edition INT,
       price DOUBLE,
       availability BOOLEAN,
       imagePath VARCHAR(255),
       description TEXT
   );
   ```

   Add sample users:

   ```sql
   INSERT INTO users (username, password, role) VALUES
   ('ramya', 'test123', 'user'),
   ('admin', 'admin123', 'admin');
   ```

3. **Add MySQL Connector JAR**
   Place `mysql-connector-j-8.4.0.jar` inside the `libs/` folder.

4. **Compile & Run**

   ```bash
   javac -cp ".;libs/mysql-connector-j-8.4.0.jar" bookstore/MainApplication.java
   java -cp ".;libs/mysql-connector-j-8.4.0.jar" bookstore.MainApplication
   ```

---

## 🎯 Screenshots

(Add your login window, admin dashboard, and book search screenshots here – upload images inside `resources/images/` and link them below.)

Example:
![Login Window](resources/images/BookStore.jpg)

---

## 📜 License

This project is licensed under the **Apache 2.0 License** – free to use and modify.

---

````


