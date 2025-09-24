# 📚 BookStore

A **Java Swing + MySQL desktop application** for managing a bookstore.  
This app provides functionality for **users** (search & browse books, login) and **admins** (manage books, checkout, add inventory).

---

## 🚀 Features

### 👤 User
- Register/Login with credentials  
- Search books by **title, author, or ISBN**  
- Browse available books  
- Checkout with simple form  

### 🛠️ Admin
- Add, update, and delete books  
- Manage user records  
- View checkout history  

---

## 🏗️ Tech Stack
- **Java Swing** – GUI framework  
- **MySQL** – Database  
- **JDBC (MySQL Connector)** – Database connectivity  
- **Modular Java Packages** – Separation of concerns (Login, Cart, Checkout, Admin, Homepage)  

---

## 📂 Project Structure
BookStore/
│── admin/                # Admin operations (Add/Update books)
│── bookDetails/          # Book model class
│── cart/                 # Cart management
│── checkout/             # Checkout functionality
│── databaseConnector/    # Database connection + queries
│── homepage/             # Homepage & UI components
│── loginWindow/          # Login GUI
│── logout/               # Logout functionality
│── libs/                 # MySQL Connector JAR
│── resources/images/     # UI images and assets
│── MainApplication.java  # Main entry point
│── README.md             # Documentation

## ⚙️ Setup & Installation  

### 1️⃣ Clone the repository  
```bash
git clone https://github.com/SaiRamyaValleruN/BookStore.git
cd BookStore
````

### 2️⃣ Setup MySQL database

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

👉 Insert sample users:

```sql
INSERT INTO users (username, password, role) VALUES
('ramya', 'test123', 'user'),
('admin', 'admin123', 'admin');
```

### 3️⃣ Add MySQL Connector JAR

* Place `mysql-connector-j-8.4.0.jar` inside the `libs/` folder.

### 4️⃣ Compile & Run the Project

```bash
javac -cp ".;libs/mysql-connector-j-8.4.0.jar" bookstore/MainApplication.java
java -cp ".;libs/mysql-connector-j-8.4.0.jar" bookstore.MainApplication
```

---

## 📸 Screenshots

### 🔑 Login Window
![Login Window](resources/images/login.png)

### 🏠 Homepage
![Homepage](resources/images/homepage.png)

### 🛒 Checkout Window
![Checkout Window](resources/images/checkout.png)

---

## 🔮 Future Enhancements

* Payment integration (Credit/Debit/UPI)
* Online order & delivery module
* Dark/Light mode toggle for UI
* Export checkout history to PDF

---

## 📜 License

Licensed under the **Apache 2.0 License** – free to use and modify.

```

---

