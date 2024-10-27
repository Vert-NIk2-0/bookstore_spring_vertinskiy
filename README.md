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
  - Service methods:
    - ```List<BookDto> getAll()``` *displaying partial information about books*

    - ```BookDto getById(Long id)``` *displaying a book by ID*

    - ```void create(BookDto dto)``` *adding a new book to the DB*

    - ```BookDto update(BookDto dto)``` *updating important book data*

    - ```boolean delete(Long id)``` *deleting a book by id*

    - ```BigDecimal getCostAuthorsBooks(String author)``` *displaying the cost of all books by a specific author*


## Description of the application
- When launching the application, the user is offered a choice of 8 actions:
  > 1. [x] Add new book
  > 2. [x] Update an existing book by ISBN
  > 3. [x] Display information about all books
  > 4. [x] Display information about the author's books
  > 5. [x] Display information about a book by ISBN
  > 6. [x] Delete book by ISBN
  > 7. [x] Print the number of books
  > 8. [x] Exit
       
- After selecting the desired item, it is transferred to the next menu, where the user will be shown the requested information, or offered options for adding/changing existing information.

## Upcoming Updates

- Implement more advanced logic for using the application
- Implement service layer methods
- Add flexibility when launching an application
- Fixing unaccounted bugs/errors
- Completing the assignment from the next lecture