# 📚 Library Management System

Welcome to my **Library Management System**! This is a **Java console application** that helps you manage books, people, and loans using **MySQL**. 🖥️🛠️

The project is **constantly evolving** 🚀, and I will continue improving it to add more features and make it more efficient.

---

## ✨ Features

- **📖 Book Management**: Add, update, list, and delete books. Ensures **unique ISBNs** and auto-incremented IDs.
- **🧑‍🤝‍🧑 Person Management**: Add, update, list, and delete people.
- **📅 Loan Management**: Manage loans with start and end dates, including tracking **non-returned books**.
- **🔍 Search Functions**: 
  - Search loans by **person ID**.
  - Search loans by **book ID**.
  - Search loans **not yet returned**.
- **💾 Database Integration**: MySQL with proper **foreign key constraints**.
- **🗃️ Data Classes**: `Prestamo`, `Persona`, `Libro`, and `PrestamoDetalle` for organized data handling.
- **✅ Input Validation**: Ensures proper data types, prevents duplicates, and validates foreign keys.

---

## 🛠️ How It Works

1. **Database Setup**: Create `libros`, `personas`, and `prestamos` tables in MySQL.  
2. **DAO Layer**: CRUD operations for books, people, and loans.  
3. **Entity Classes**: Use `Libro`, `Persona`, `Prestamo`, and `PrestamoDetalle` for structured data.  
4. **Console Interface**: Interact via a menu using **Scanner**.  
5. **Add Records**: Input books, people, and loans with validation.  
6. **List Records**: View all books, people, or loans.  
7. **Update Records**: Modify data by ID.  
8. **Delete Records**: Remove records by ID safely.  
9. **Advanced Queries**: Join tables to see **detailed loan info**.  
10. **Exception Handling**: Handle errors and invalid inputs gracefully.  

---

## 🚧 Project Status

This project is a **work in progress** and will continue improving over time. 🌱
