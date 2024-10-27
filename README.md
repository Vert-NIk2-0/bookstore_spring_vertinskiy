# Bookstore_Vertinskiy
#### **Project Author**: Nikita Vertinskiy

## Application structure
- Book class data:
  - Long ```id```
  - String ```author```
  - String ```bookname```
  - String ```isbn```
  - Object ```numberOfPages``` *(optional)*
  - BigDecimal ```price``` *(optional)*
  - Object ```year``` *(optional)*


- Methods [implemented](src/implementations/BookDao.java) in the project:
  - **CRUD** methods:
    - **C**reate: ```void createBook(Book book)```
    - **U**pdate: ```void updateRS(Book book);```
    - **R**ead: ```List<Book> getAllBooks();```
    - **D**elete: ```boolean deleteBookByIsbn(String isbn)```
  - Additional methods:
    - ```Book getBookByIsbn(String isbn)``` *displaying information about a book by ISBN*
    - ```List<Book> getByAuthor(String author)``` *list all books by a specific author*
  - Additional methods for exploring the capabilities of **ResultSet** class
    - ```void printTableInfo()``` *сreating an **output.txt** file on the local disk showing the database structure*
    - ```void createRS(Book book)``` *adding a new book to the table*
    - ```void updateRS(Book book)``` *updating existing workbook data*


## Description of the application
- When launching the application, the user is offered a choice of 8 actions:
  > 1. Add new book
  > 2. Update an existing book by ISBN
  > 3. Display information about all books
  > 4. Display information about the author's books
  > 5. Display information about a book by ISBN
  > 6. Delete book by ISBN
  > 7. Print the number of books
  > 0. Exit
       
- After selecting the desired item, it is transferred to the next menu, where the user will be shown the requested information, or offered options for adding/changing existing information.

## Upcoming Updates

- It is planned to add methods such as:
  - ```void update (Book book)``` *implementation of the missing CRUD method*
  - ```Book getBookById (Long id)``` *adding a book by id* 
  - ```boolean deleteBookById (Long id)``` *deleting a book by id*
- Fixing unaccounted bugs/errors
- Completing the assignment from the next lecture