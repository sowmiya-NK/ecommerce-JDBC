package org.e_commerce_db.controller;

import org.e_commerce_db.dao.CartDao;
import org.e_commerce_db.dao.ProductDao;
import org.e_commerce_db.model.Cart;
import org.e_commerce_db.model.Product;
import org.e_commerce_db.model.User;
import org.e_commerce_db.utils.AppException;
import org.e_commerce_db.utils.StringUtils;
import org.e_commerce_db.view.CartPage;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.e_commerce_db.utils.AppInput.enterInt;
import static org.e_commerce_db.utils.OutputUtil.println;
import static org.e_commerce_db.utils.UserUtil.getLoggedInUser;

public class CartController {

    private final HomeController homeController;
    private final OrderController orderController;
    private final CartPage cartPage;
    private final CartDao cartDao;
    private final ProductDao productDao;

    public CartController(HomeController homeController) {
        this.homeController = homeController;
        orderController = new OrderController(homeController);
        cartPage = new CartPage();
        cartDao = new CartDao();
        productDao = new ProductDao();
    }

    public void addToCart(int productId) throws SQLException {
        User loggedInUser = getLoggedInUser();
        ArrayList<Product> products = productDao.getProducts();

        int userProductId;
        for (Product product : products) {
            if (product.getId() == productId) {
                userProductId = product.getId();
                break;
            }
        }

        if (cartDao.getUserCart(loggedInUser.getId()) != null) {
            ArrayList<Cart> cart = cartDao.getUserCart(loggedInUser.getId());

            boolean isFound = false;
            for (Cart cartProduct : cart) {
                if (cartProduct.getProductId() == productId) {
                    cartDao.updateCart(loggedInUser.getId(), productId);
                    isFound = true;
                }
            }

            if (!isFound) {
                cartDao.addToCart(loggedInUser.getId(), productId, 1);
            }
        } else {
            cartDao.addToCart(loggedInUser.getId(), productId, 1);
        }
    }

    public void printCart() throws SQLException {
        int loggedInUser = getLoggedInUser().getId();
        if (cartDao.getUserCart(loggedInUser) == null) {
            cartPage.printEmptyCart();
            homeController.printMenu();
        } else {
            ArrayList<Cart> cartProducts = cartDao.getUserCart(loggedInUser);
            cartPage.printCart(cartProducts);
            if (!cartProducts.isEmpty()) {
                cartPage.printCheckout();
            } else {
                println(StringUtils.EMPTY_CART);
            }
            cartPage.printBack();

            try {
                int choice = enterInt(StringUtils.ENTER_CHOICE);
                if (choice == 88) {
                    orderController.checkout();
                } else if (choice == 99) {
                    homeController.printMenu();
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            } catch (AppException appException) {
                invalidChoice(appException);
            }

        }
    }

    private void invalidChoice(AppException appException) throws SQLException {
        println(appException.getMessage());
        printCart();
    }
}
