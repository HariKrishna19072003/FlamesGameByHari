# 🔥 FLAMES Game - Java, JDBC, MySQL

FLAMES is a fun love compatibility game based on names. This project implements the FLAMES game using **Java, JDBC, and MySQL**.

---

## 📌 Features
✔️ Takes two names as input  
✔️ Calculates the FLAMES result (Friend, Love, Affection, Marriage, Enemy, Sibling)  
✔️ Uses **MySQL database** to store results  
✔️ Simple and easy-to-use interface  

---

## 🛠️ Tech Stack
- **Java** (Core Logic)  
- **JDBC** (Database Connectivity)  
- **MySQL** (Stores user data)  

---

## 🚀 How to Run the Project

### **1️⃣ Setup MySQL Database**
1. Open MySQL and create a new database:  
   ```sql
   CREATE DATABASE flames_db;


**
Switch to the database:**
USE flames_db;

Create the table for storing results:
CREATE TABLE flames_results (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name1 VARCHAR(50),
    name2 VARCHAR(50),
    result VARCHAR(10)
);

Save and exit.

