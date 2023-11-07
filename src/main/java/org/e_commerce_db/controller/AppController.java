package org.e_commerce_db.controller;

import org.e_commerce_db.view.WelcomePage;

public class AppController {
    private final WelcomePage welcomePage;
    private final AuthController authController;

    public AppController() {
        welcomePage = new WelcomePage();
        authController = new AuthController();
    }

    public void init() {
        welcomePage.welcome();
        authController.authMenu();
    }
}
