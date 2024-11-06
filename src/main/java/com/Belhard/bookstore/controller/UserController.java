package com.belhard.bookstore.controller;

import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.dao.impl.UserDaoImpl;
import com.belhard.bookstore.dao.entity.Gender;
import com.belhard.bookstore.dao.entity.Role;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.connection.impl.PropertiesUtilImpl;
import com.belhard.bookstore.service.entity.UserDto;
import com.belhard.bookstore.service.impl.UserDtoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private static final PropertiesUtilImpl PROPERTIES_UTIL_IMPL = new PropertiesUtilImpl("application.properties");
    private static final String URL = PROPERTIES_UTIL_IMPL.get("db.url");
    private static final String HOSTNAME = PROPERTIES_UTIL_IMPL.get("db.username");
    private static final String PASSWORD = PROPERTIES_UTIL_IMPL.get("db.password");
    private static final ConnectionManagerImpl CONNECTION_MANAGER_IMPL = new ConnectionManagerImpl(URL, HOSTNAME, PASSWORD);
    private static final UserDaoImpl userImpl = new UserDaoImpl(CONNECTION_MANAGER_IMPL);
    private static final UserDtoImpl userDto = new UserDtoImpl(userImpl);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        while (true) {

            System.out.println("""
                    
                    Select the desired action:
                    
                    1 - Sign in
                    2 - Sign up
                    0 - Exit
                    """);

            int number;
            while (true) {
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    if (number >=0 && number <=2) {
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


            switch (number) {
                case 1:

                    System.out.print("Enter your email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.println();

                    System.out.print("Enter your password: ");
                    String loginPassword = scanner.nextLine();
                    System.out.println();

                    User user = userDto.login(loginEmail, loginPassword);

                    if (user == null) {
                        System.out.println("!!!Invalid login or password!!!\n");
                        continue;
                    }
//=======================================================USER=======================================================
                    if (user.getRole() == Role.USER) {
                        boolean existenceAccount = true;

                        while (true) {
                            if (!existenceAccount) {
                                break;
                            }
                            System.out.println("""
                                    
                                    Main menu (select action number):
                                    
                                    1 - Update account information
                                    2 - Change password
                                    3 - Display all users
                                    4 - Display user by ID
                                    5 - Delete account
                                    0 - Exit
                                    """);

                            while (true) {
                                if (scanner.hasNextInt()) {
                                    number = scanner.nextInt();
                                    if (number >= 0 && number <= 5) {
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
                                break;
                            }

                            switch (number) {
//=======================================================UPDATE=======================================================
                                case 1:
                                    updateLoggedUser(user);
                                    break;

//=======================================================PASSWORD=======================================================
                                case 2:
                                    editPasswordLoggerUser(loginPassword, user);
                                    userImpl.update(user);
                                    break;

//=======================================================ALL=USERS======================================================
                                case 3:
                                    System.out.println("Display all users");
                                    List<User> userList = userImpl.getAll();
                                    for (User printUser : userList) {
                                        System.out.println("User{" +
                                                "id=" + printUser.getId() +
                                                ", firstName='" + printUser.getFirstName() + '\'' +
                                                ", lastName='" + printUser.getLastName() + '\'' +
                                                ", email='" + printUser.getEmail() + '\'' +
                                                '}');
                                    }
                                    break;

//=======================================================USER=BY=ID=====================================================
                                case 4:
                                    displayUserById();
                                    break;

//=======================================================DELETE=======================================================
                                case 5:
                                    existenceAccount = deleteLoggerUser(existenceAccount, user);
                                    break;
                            }
                        }
                    }

//=======================================================ADMIN=======================================================
                    if (user.getRole() == Role.ADMIN) {
                        boolean existenceAccount = true;

                        while (true) {
                            if (!existenceAccount) {
                                break;
                            }
                            System.out.println("""
                                    
                                    Main menu (select action number):
                                    
                                    1 - Update my account information
                                    2 - Change password
                                    3 - Update user's role by ID
                                    4 - Display all users
                                    5 - Display users by last name
                                    6 - Display user by ID
                                    7 - Display count of users
                                    8 - Delete account
                                    0 - Exit
                                    """);

                            while (true) {
                                if (scanner.hasNextInt()) {
                                    number = scanner.nextInt();
                                    if (number >= 0 && number <= 8) {
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
                                break;
                            }

                            switch (number) {
//=======================================================UPDATE=======================================================
                                case 1:
                                    updateLoggedUser(user);
                                    break;

//=======================================================PASSWORD=======================================================
                                case 2:
                                    editPasswordLoggerUser(loginPassword, user);
                                    userImpl.update(user);
                                    break;

//=======================================================ROLE===========================================================
                                case 3:
                                    updateUserRole();
                                    break;

//=======================================================ALL=USERS======================================================
                                case 4:
                                    System.out.println("Display all users");
                                    List<User> userList = userImpl.getAll();
                                    for (User printUser : userList) {
                                        System.out.println(printUser);
                                    }
                                    System.out.println();
                                    break;

//=======================================================USER=BY=LAST=NAME==============================================
                                case 5:
                                    System.out.println("Display users by last name");
                                    System.out.print("Enter last name: ");
                                    List<User> userListLastName = userImpl.getByLastName(scanner.nextLine());
                                    if (userListLastName == null) {
                                        System.out.println("There is no such last name");
                                        break;
                                    }
                                    for (User printUser : userListLastName) {
                                        System.out.println(printUser);
                                    }
                                    break;

//=======================================================USER=BY=ID=====================================================
                                case 6:
                                    displayUserById();
                                    break;

//=======================================================USER=BY=ID=====================================================
                                case 7:
                                    System.out.println("Display count of users");

                                    System.out.print("Number of users: " + userImpl.countAll());
                                    System.out.println();
                                    break;

//=======================================================DELETE=========================================================
                                case 8:
                                    existenceAccount = deleteLoggerUser(existenceAccount, user);
                                    break;
                            }
                        }
                    }
                    break;

                case 2: //New User
                    System.out.println("Create account:");
                    UserDto newUserDto = new UserDto();

                    {//EMAIL
                        while (true) {
                            System.out.print("\nEnter email: ");
                            if (scanner.hasNextLine()) {
                                String email = scanner.nextLine();
                                if (userImpl.getByEmail(email) == null) {
                                    newUserDto.setEmail(email);
                                    break;
                                } else {
                                    System.out.println("This email already exists");
                                    continue;
                                }

                            }
                            System.out.println();
                        }
                    }

                    {//PASSWORD
                        System.out.print("\nEnter password: ");
                        if (scanner.hasNextLine()) {
                            newUserDto.setPassword(scanner.nextLine());
                        }
                    }

                    {//LOGIN
                        System.out.print("\nEnter login: ");
                        if (scanner.hasNextLine()) {
                            newUserDto.setLogin(scanner.nextLine());
                        }
                    }

                    {//DATE OF BIRTH
                        System.out.println("\nEnter your date of birth");
                        newUserDto.setDateOfBirth(getDate());
                    }

                    {//GENDER
                        System.out.println("\nChoose your gender:");
                        System.out.println("""
                                
                                1 - male
                                2 - female
                                """);

                        while (true) {
                            if (scanner.hasNextInt()) {
                                number = scanner.nextInt();
                                if (number < 1 || number > 2) {
                                    System.out.println("Invalid input. Please try again");
                                    continue;
                                }
                                System.out.println();
                                break;
                            } else {
                                System.out.println("Invalid input. Please try again");
                                scanner.next();
                            }
                        }
                        newUserDto.setGender(number == 1 ? Gender.MALE : Gender.FEMALE);
                    }

                    userDto.createNewUser(newUserDto);
                    break;
            }

        }
    }

    private static LocalDate getDate() {
        LocalDate currentDate = LocalDate.now();

        int year;
        int month;
        int day;

        LocalDate date = null;
        while (true) {
            System.out.println("Enter year");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year < 0 || year > currentDate.getYear()) {
                    System.out.println("There can't be a year like this");
                    continue;
                }
                System.out.println();
                break;
            } else {
                System.out.println("Invalid input. Please try again");
                scanner.next();
            }
        }

        while (true) {
            System.out.println("Enter month");
            if (scanner.hasNextInt()) {
                month = scanner.nextInt();
                if (month < 0 || month > 12 || (year == currentDate.getYear() && month > currentDate.getMonthValue())) {
                    System.out.println("There can't be a month like this");
                    continue;
                }
                System.out.println();
                break;
            } else {
                System.out.println("Invalid input. Please try again");
                scanner.next();
            }
        }

        while (true) {
            System.out.println("Enter day");
            if (scanner.hasNextInt()) {
                day = scanner.nextInt();
                if (day < 0 || day > 31 || (year == currentDate.getYear() && month == currentDate.getMonthValue() && day > currentDate.getDayOfMonth())) {
                    System.out.println("There can't be a day like this");
                } else {
                    try {
                        date = LocalDate.of(year, month, day);
                        break;
                    } catch (DateTimeException e) {
                        logger.error("Date validity error");
                        System.out.println("There can't be a day like this");
                    }
                }
            } else {
                System.out.println("Invalid input. Please try again");
                scanner.next();
            }
        }
        return date;
    }

    private static void updateUserRole() {
        System.out.println("Update user's role by ID");

        User updateUser;

        while(true) {
            System.out.println("Enter user ID: ");
            if (scanner.hasNextLong()) {
                Long userId = scanner.nextLong();

                updateUser = userImpl.getById(userId);
                if (updateUser == null) {
                    System.out.println("There is no user with this ID");
                    continue;
                }
                break;

            } else {
                System.out.println("Oops, you entered something wrong... Please try again");
                scanner.next();
            }
        }

        System.out.println("The user has a role: " + updateUser.getRole());
        boolean changeRole = false;

        if (updateUser.getRole() == Role.USER) {
            System.out.println("Do you want to promote to admin? (y/n)");
            scanner.nextLine();
            while (true) {
                if (scanner.hasNextLine()){
                    String agreement = scanner.nextLine();
                    if(agreement.equals("y")) {
                        updateUser.setRole(Role.ADMIN);
                        changeRole = true;
                        break;
                    } else if (agreement.equals("n")) {
                        break;
                    } else {
                        System.out.println("Enter 'y' (if yes) or 'n' (if no)");
                    }
                }
            }
        }

        if (updateUser.getRole() == Role.ADMIN && !changeRole) {
            System.out.println("Do you want to demote to user? (y/n)");
            scanner.nextLine();
            while (true) {
                if (scanner.hasNextLine()) {
                    String agreement = scanner.nextLine();
                    if (agreement.equals("y")) {
                        updateUser.setRole(Role.USER);
                        break;
                    } else if (agreement.equals("n")) {
                        break;
                    } else {
                        System.out.println("Enter 'y' (if yes) or 'n' (if no)");
                    }
                }
            }
        }
        userImpl.update(updateUser);
    }

    private static boolean deleteLoggerUser(boolean existenceAccount, User user) {
        System.out.println("Delete account");

        System.out.println("Are you sure? (y/n)");
        while (true) {
            String agreement = scanner.nextLine();
            if(agreement.equals("y")) {
                existenceAccount = false;
                userImpl.deleteById(user.getId());
                break;
            } else if (agreement.equals("n")) {
                break;
            } else {
                System.out.println("Enter 'y' (if yes) or 'n' (if no)");
            }
        }
        return existenceAccount;
    }

    private static void displayUserById() {
        System.out.println("Display user by ID\n");



        while (true) {
            try {
                System.out.print("Enter user ID: ");
                if (scanner.hasNextLong()) {
                    System.out.println(userImpl.getById(scanner.nextLong()));
                    break;
                } else {
                    System.out.println("Invalid input. Please try again");
                    scanner.next();
                }
            } catch (RuntimeException e) {
                System.out.println();
                logger.error("Error displaying user by id");
                System.out.println("There is no such ID");
            }
        }
    }

    private static void editPasswordLoggerUser(String loginPassword, User user) {
        System.out.println("Change password");

        while (true) {
            System.out.print("Enter old password: ");
            String oldPassword = scanner.nextLine();
            if (oldPassword.equals("exit")){
                break;
            }
            if (!oldPassword.equals(loginPassword)) {
                System.out.println("Password error. You can print 'exit' or try again");
                continue;
            }

            System.out.print("Enter new password: ");
            user.setPassword(scanner.nextLine());
            break;
        }
    }

    private static void updateLoggedUser(User user) {
        int number;
        System.out.println("Update an account:");
        System.out.println(userImpl.getById(user.getId()));

        while(true) {
            System.out.println("""
                Select the field you want to change:
                
                1 - First Name
                2 - Last Name
                3 - Date of birth
                4 - Phone number
                5 - Gender
                6 - Login
                0 - Save and exit
                """);

            while (true) {
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    if (number >=0 && number <=6) {
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
                userImpl.update(user);
                System.out.println("Update result:\n" + user);
                break;
            }

            switch (number) {
                case 1:
                    System.out.println("Enter your first name");
                    user.setFirstName(scanner.nextLine());
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Enter your last name");
                    user.setLastName(scanner.nextLine());
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Enter your date of birth");
                    LocalDate date = getDate();
                    user.setDateOfBirth(date);
                    break;

                case 4:
                    System.out.println("Enter your phone number");
                    if (scanner.hasNextLine()){
                        user.setPhoneNumber(scanner.nextLine());
                    }
                    break;

                case 5:
                    System.out.println("Choose your gender");

                    System.out.println("""
                            
                            1 - male
                            2 - female
                            """);
                    while (true) {
                        if (scanner.hasNextInt()) {
                            number = scanner.nextInt();
                            if (number < 1 || number > 2) {
                                System.out.println("Invalid input. Please try again");
                                continue;
                            }
                            System.out.println();
                            break;
                        } else {
                            System.out.println("Invalid input. Please try again");
                            scanner.next();
                        }
                    }
                    user.setGender(number == 1 ? Gender.MALE : Gender.FEMALE);
                    break;

                case 6:
                    System.out.println("Enter new login");
                    if (scanner.hasNextLine()) {
                        user.setLogin(scanner.nextLine());
                    }
                    break;
            }
        }
    }
}
