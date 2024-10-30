package com.Belhard.bookstore.controller;

import com.Belhard.bookstore.dao.BookImpl;
import com.Belhard.bookstore.model.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BookController {
    public static void main(String[] args) {
        BookImpl db = new BookImpl();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println(
                    """
                    
                    Main menu (select action number):
                    
                    1 - Add new book
                    2 - Update an existing book by ID
                    3 - Display information about all books
                    4 - Display information about the author's books
                    5 - Display information about a book by ISBN
                    6 - Delete book by ISBN
                    7 - Print the number of books
                    0 - Exit
                    """);

            int number = -1;
            while (true) {
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    if (number >=0 && number <=7) {
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Invalid input. Please try again");
                    }
                } else {
                    System.out.println("Oops, you entered something wrong... Please try again");
                    scanner.next();
                }
            }
            if (number == 0) {
                System.out.println("Good bye!");
                break;
            }

            Book book = new Book();
            switch (number) {
//================================================CREATE============================================================
                case 1:
                    System.out.println("Adding a new book:\n");
                    System.out.println("Important information. The fields must be filled in!");
                    System.out.println("Enter the author of the book:");
                    while (true) {
                        if (scanner.hasNextLine()) {
                            String input = scanner.nextLine();
                            if (!input.isEmpty()) {
                                book.setAuthor(input);
                                break;
                            } else {
                                System.out.println("This field is required! Please enter the author");
                            }
                        }
                    }

                    System.out.println("Enter the name of the book:");
                    while (true) {
                        if (scanner.hasNextLine()) {
                            String input = scanner.nextLine();
                            if (!input.isEmpty()) {
                                book.setBookname(input);
                                break;
                            } else {
                                System.out.println("This field is required! Please enter the name of the book");
                            }
                        }
                    }

                    System.out.println("Enter the ISBN of the book in 13 digit format without divisions:");
                    while (true) {
                        String input = "";
                        if (scanner.hasNextLine()) {
                            input = scanner.nextLine();
                        }

                        if (input.matches("\\d{13}")) {
                            book.setIsbn(input);
                            break;
                        }else {
                            System.out.println("Invalid input. Please try again");
                        }

                    }

                    System.out.println("\nAdditional information. If there is no information, enter null");
                    System.out.println("Enter number of pages:");
                    while (true) {
                        String input = "";
                        if (scanner.hasNextLine()) {
                            input = scanner.nextLine();
                        }

                        if (input.equals("null")) {
                            book.setNumberOfPages(null);
                            break;
                        } else {
                            try {
                                int pages = Integer.parseInt(input);

                                if (pages > 0) {
                                    book.setNumberOfPages(pages);
                                    break;
                                } else {
                                    System.out.println("The number of pages must be greater than 0!");

                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please try again");
                            }
                        }
                    }

                    System.out.println("Enter the price of the book:");
                    while (true) {
                        String input = "";
                        if (scanner.hasNextLine()) {
                            input = scanner.nextLine();
                        }

                        if (input.equals("null")) {
                            book.setPrice(null);
                            break;
                        } else {
                            try {
                                BigDecimal price = new BigDecimal(input);

                                if (price.compareTo(new BigDecimal("0")) >= 0) {
                                    book.setPrice(price);
                                    break;
                                } else {
                                    System.out.println("Еhe price of the book must not be negative!");
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please try again");
                            }
                        }
                    }

                    System.out.println("Enter the year the book was published:");
                    while (true) {
                        String input = scanner.nextLine();

                        if (input.equals("null")) {
                            book.setYear(null);
                            break;
                        } else {
                            try {
                                int year = Integer.parseInt(input);

                                if (year > 0) {
                                    book.setYear(year);
                                    break;
                                } else {
                                    System.out.println("The year must be greater than 0!");
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please try again");
                            }
                        }
                    }
                    db.createBook(book);
                    System.out.println("You have added a book:\n" + book);
                    break;
//================================================UPDATE============================================================
                case 2:
                    System.out.println("Book update:");

                    System.out.println("Enter the ID of the book you want to change");

                    Long idUpdate;
                    while (true) {
                        if (scanner.hasNextInt()) {
                            idUpdate = scanner.nextLong();
                            if (idUpdate >=0) {
                                book = db.getBookById(idUpdate);
                                scanner.nextLine();
                                break;
                            } else {
                                System.out.println("Invalid input. Please try again");
                            }
                        } else {
                            System.out.println("Oops, you entered something wrong... Please try again");
                            scanner.next();
                        }
                    }

                    while (true) {
                        System.out.println("""
                                Select the field you want to change:
                                
                                1 - Author
                                2 - Bookname
                                3 - ISBN
                                4 - Number of pages
                                5 - Price
                                6 - Year
                                0 - Save and exit
                                """);

                        int numberUpdate = -1;
                        while (true) {
                            if (scanner.hasNextInt()) {
                                numberUpdate = scanner.nextInt();
                                if (numberUpdate >=0 && numberUpdate <=6) {
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please try again");
                                }
                            } else {
                                System.out.println("Oops, you entered something wrong... Please try again");
                                scanner.next();
                            }
                        }
                        if (numberUpdate == 0) {
                            db.updateBook(book);
                            System.out.println("Update result:\n" + book);
                            break;
                        }

                        switch (numberUpdate) {
                            case 1:
                                System.out.println("Enter a new book author:");
                                while (true) {
                                    if (scanner.hasNextLine()) {
                                        String input = scanner.nextLine();
                                        if (!input.isEmpty()) {
                                            book.setAuthor(input);
                                            break;
                                        } else {
                                            System.out.println("This field is required! Please enter the author");
                                        }
                                    }
                                }
                                break;

                            case 2:
                                System.out.println("Enter a new book name:");
                                while (true) {
                                    if (scanner.hasNextLine()) {
                                        String input = scanner.nextLine();
                                        if (!input.isEmpty()) {
                                            book.setBookname(input);
                                            break;
                                        } else {
                                            System.out.println("This field is required! Please enter the name of the book");
                                        }
                                    }
                                }
                                break;

                            case 3:
                                System.out.println("Enter new book ISBN in 13 digit format without divisions:");
                                while (true) {
                                    String input = scanner.nextLine();

                                    if (input.matches("\\d{13}")) {
                                        book.setIsbn(input);
                                        break;
                                    } else {
                                        System.out.println("Invalid input. Please try again");
                                    }
                                }
                                break;

                            case 4:
                                System.out.println("Enter the new number of book pages:");
                                while (true) {
                                    String input = scanner.nextLine();

                                    if (input.equals("null")) {
                                        book.setNumberOfPages(null);
                                        break;
                                    } else {
                                        try {
                                            int pages = Integer.parseInt(input);

                                            if (pages > 0) {
                                                book.setNumberOfPages(pages);
                                                break;
                                            } else {
                                                System.out.println("The number of pages must be greater than 0!");

                                            }

                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid input. Please try again");
                                        }
                                    }
                                }
                                break;

                            case 5:
                                System.out.println("Enter a new book price:");
                                while (true) {
                                    String input = scanner.nextLine();

                                    if (input.equals("null")) {
                                        book.setPrice(null);
                                        break;
                                    } else {
                                        try {
                                            BigDecimal price = new BigDecimal(input);

                                            if (price.compareTo(new BigDecimal("0")) >= 0) {
                                                book.setPrice(price);
                                                break;
                                            } else {
                                                System.out.println("The price of the book must not be negative!");
                                            }

                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid input. Please try again");
                                        }
                                    }
                                }
                                break;

                            case 6:
                                System.out.println("Enter the new book publication year:");
                                while (true) {
                                    String input = scanner.nextLine();

                                    if (input.equals("null")) {
                                        book.setYear(null);
                                        break;
                                    } else {
                                        try {
                                            int year = Integer.parseInt(input);

                                            if (year > 0) {
                                                book.setYear(year);
                                                break;
                                            } else {
                                                System.out.println("The year must be greater than 0!");
                                            }

                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid input. Please try again");
                                        }
                                    }
                                }
                                break;

                        }
                    }
                    break;
//=====================================================ALL=BOOKS=======================================================
                case 3:
                    System.out.println("Display information about all books:\n");
                    db.getAllBooks().forEach(info -> System.out.println(info.printSomeFields()));
                    break;
//======================================================BY=AUTHOR=====================================================
                case 4:
                    System.out.println("Display information about the author's books:\n");
                    System.out.println("Enter the author of the book:");
                    String author = null;
                    while (true) {
                        if (scanner.hasNextLine()) {
                            author = scanner.nextLine();
                            if (!author.isEmpty()) {
                                break;
                            } else {
                                System.out.println("Please enter the author");
                            }
                        }
                    }
                    List<Book> list = db.getByAuthor(author);
                    if (!list.isEmpty()){
                        list.forEach(System.out::println);
                    } else {
                        System.out.println("There are no such authors");
                    }
                    break;
//======================================================BY=ISBN=====================================================
                case 5:
                    System.out.println("Display information about a book by ISBN:\n");
                    System.out.println("Enter the ISBN of the book you want to display");
                    String isbnDisplay = "";
                    while (true) {

                        isbnDisplay = scanner.nextLine();

                        if (isbnDisplay.matches("\\d{13}")) {
                            try {
                                book = db.getBookByIsbn(isbnDisplay);
                            }catch (RuntimeException e) {
                                System.out.println("This ISBN does not exist");
                                break;
                            }
                            System.out.println(book);
                            break;
                        }else {
                            System.out.println("Invalid input. Please try again");
                        }
                    }
                    break;
//============================================================DELETE================================================
                case 6:
                    System.out.println("Removing a book by ISBN:\n");
                    System.out.println("Enter the ISBN of the book you want to remove");
                    String isbnRemove = "";
                    while (true) {
                        isbnRemove = scanner.nextLine();

                        if (isbnRemove.matches("\\d{13}")) {
                            try {
                                book = db.getBookByIsbn(isbnRemove);
                            }catch (RuntimeException e) {
                                System.out.println("This ISBN does not exist");
                                break;
                            }

                            System.out.println(book);
                            break;
                        }else {
                            System.out.println("Invalid input. Please try again");
                        }
                    }

                    try {
                        if (db.deleteBookByIsbn(isbnRemove)) {
                            System.out.println("Successfully!");
                        }
                    }catch (RuntimeException e) {
                        System.out.println("This ISBN does not exist");
                    }

                    break;
//=====================================================NUMBER=BOOKS======================================================
                case 7:
                    System.out.println("Displaying the number of books:\n");
                    System.out.println("Number of books in the database: " + db.countAll());
                    break;
            }
        }

    }
}
