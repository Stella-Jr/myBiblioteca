📚 Library Management System

Welcome to my Library Management System! This is a Java console application that helps you manage books, people, and loans (prestamos) using MySQL. 🖥️🛠️

The project is constantly evolving 🚀, and I keep improving it to add more features and make it more efficient.

✨ Features

📖 Book Management: Add, update, list, and delete books. Ensures unique ISBNs and auto-incremented IDs.

🧑‍🤝‍🧑 Person Management: Add, update, list, and delete people.

📅 Loan Management: Manage loans with start and end dates, including tracking non-returned books.

🔍 Search Functions:

Search loans by person ID.

Search loans by book ID.

Search loans not yet returned.

💾 Database Integration: MySQL with proper foreign key constraints.

🗃️ Data Classes: Prestamo, Persona, Libro, and PrestamoDetalle for organized data handling.

✅ Input Validation: Ensures proper data types, prevents duplicates, and validates foreign keys.

🛠️ How It Works (Step by Step)

Database Setup: Create libros, personas, and prestamos tables in MySQL.

DAO Layer: CRUD operations for books, people, and loans.

Entity Classes: Use Libro, Persona, Prestamo, and PrestamoDetalle for structured data.

Console Interface: Interact via a menu using Scanner.

Add Records: Input books, people, and loans with validation.

List Records: View all books, people, or loans.

Update Records: Modify data by ID.

Delete Records: Remove records by ID safely.

Advanced Queries: Join tables to see detailed loan info.

Exception Handling: Handle errors and invalid inputs gracefully.

🚧 Project Status

This project is a work in progress. Current focus: core functionalities of a library system.

Future improvements include:

Better input validation and error handling.

A GUI or web interface 🌐.

Reports and statistics 📊.

New features: reservations, categories, fines 💰.

Stay tuned as this project keeps evolving! 🌱
