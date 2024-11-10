# Bookstore_Vertinskiy
#### **Project Author**: Nikita Vertinskiy

## Application structure
### Book class
- Data:
  - Long ```id```
  - String ```author```
  - String ```bookname```
  - String ```isbn```
  - Object ```numberOfPages``` *(optional)*
  - BigDecimal ```price``` *(optional)*
  - Object ```year``` *(optional)*


- Methods [implemented](src/implementations/BookDao.java) in the project:
  - **CRUD** methods:
    - **C**reate: ```void create(Book book)```
    - **U**pdate: ```void update(Book book);```
    - **R**ead: ```List<Book> getAll();```
    - **D**elete: ```boolean deleteByIsbn(String isbn)```
  - Additional methods:
    - ```Book getByIsbn(String isbn)``` *displaying information about a book by ISBN*
    - ```List<Book> getByAuthor(String author)``` *list all books by a specific author*
  - Service methods:
    - ```List<BookDto> getAll()``` *displaying partial information about books*

    - ```BookDto getById(Long id)``` *displaying a book by ID*

    - ```void create(BookDto dto)``` *adding a new book to the DB*

    - ```BookDto update(BookDto dto)``` *updating important book data*

    - ```boolean delete(Long id)``` *deleting a book by id*

    - ```BigDecimal getCostAuthorsBooks(String author)``` *displaying the cost of all books by a specific author*

### User class
- Data:
  - Long ```id```
  - String ```firstName```
  - String ```lastName```
  - String ```email``` *(optional)*
  - LocalDate ```dateOfBirth``` *(optional)*
  - String ```phoneNumber```
  - Gender ```gender``` *(optional)*
  - String ```login``` 
  - String ```password``` *(optional)*
  - Role ```role``` *(optional)*


- Methods [implemented](src/implementations/UserDao.java) in the project:
  - **CRUD** methods:
    - **C**reate: ```void create(User user)```
    - **U**pdate: ```User update(User user)```
    - **R**ead: ```List<User> getAll()```
    - **D**elete: ```boolean deleteById(Long id)```
  - Additional methods:
    - ```List<User> getByLastName(String lastName)``` *displaying information about a user by last name*
    - ```User getById(Long id)``` *Displaying information about a user by ID*
    - ```User getByEmail(String email)``` *Displaying information about a user by email*
    - ```long countAll()``` Displaying count of users
  - Service methods:
    - ```void create(UserDto dto)``` *create new user*

    - ```User login(String email, String password)``` *user authorization*

## Description of the application
The user can view a list of all users or a list of all books. When you click on a nickname, it is redirected to the user’s page under that nickname. By clicking on a book, he can view more detailed information about this book.

## Upcoming Updates

- Implement more advanced logic for using the application
- Add flexibility when launching an application
- Fixing unaccounted bugs/errors
- Completing the assignment from the next lecture