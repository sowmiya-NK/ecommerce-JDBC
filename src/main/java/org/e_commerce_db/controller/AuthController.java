package org.e_commerce_db.controller;

import org.e_commerce_db.dao.RegisterDao;
import org.e_commerce_db.dao.UserDao;
import org.e_commerce_db.model.User;
import org.e_commerce_db.utils.AppException;
import org.e_commerce_db.utils.StringUtils;
import org.e_commerce_db.view.AuthPage;
import org.e_commerce_db.view.LoginPage;
import org.e_commerce_db.view.RegisterPage;

import java.sql.SQLException;

import static org.e_commerce_db.utils.AppInput.enterInt;
import static org.e_commerce_db.utils.AppInput.enterString;
import static org.e_commerce_db.utils.OutputUtil.println;
import static org.e_commerce_db.utils.UserUtil.setLoggedInUser;

public class AuthController {

    private final AuthPage authPage;
    private final RegisterPage registerPage;
    private final RegisterDao registerDao;
    private final UserDao userDao;
    private final LoginPage loginPage;
    private final HomeController homeController;

    public AuthController() {
        authPage = new AuthPage();
        registerPage = new RegisterPage();
        registerDao = new RegisterDao();
        userDao=new UserDao();
        loginPage=new LoginPage();
        this.homeController=new HomeController(this);

    }

    public void authMenu() {
        authPage.printAuthMenu();
        int choice;
        try {
            choice = enterInt(StringUtils.ENTER_CHOICE);
            if (choice == 99) {
                authPage.printThankYou();
                System.exit(0);
            } else {
                if (choice == 1) {
                    login();
                } else if (choice == 2) {
                        register();
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void login() {
        String email, password;
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);

        User user = userDao.validateUser(email, password);
        if (user != null) {
//            System.out.println(user.getRole());
            if(user.getRole()==2) {
                setLoggedInUser(user);
                homeController.printMenu();
            }
        } else {
            loginPage.printInvalidCredentials();
            authMenu();
        }
    }

    public void register() throws SQLException {
        String email, password, c_password;
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);
        c_password = enterString(StringUtils.ENTER_PASSWORD_AGAIN);

        if (password.equals(c_password)) {
            registerDao.addUser(email, password);

        } else {
            registerPage.passwordMisMatch();
        }
        authMenu();
    }

    private void invalidChoice(AppException e) {
        println(e.getMessage());
        authMenu();
    }

}
