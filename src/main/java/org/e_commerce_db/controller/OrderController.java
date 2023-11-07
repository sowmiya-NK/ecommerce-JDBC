package org.e_commerce_db.controller;


import org.e_commerce_db.dao.CartDao;
import org.e_commerce_db.dao.OrderDao;
import org.e_commerce_db.model.Cart;
import org.e_commerce_db.model.OrderDetail;
import org.e_commerce_db.model.Orders;
import org.e_commerce_db.model.User;
import org.e_commerce_db.utils.AppException;
import org.e_commerce_db.utils.StringUtils;
import org.e_commerce_db.view.OrderPage;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.e_commerce_db.utils.AppInput.enterInt;
import static org.e_commerce_db.utils.OutputUtil.println;
import static org.e_commerce_db.utils.UserUtil.getLoggedInUser;

public class OrderController {
    private final HomeController homeController;
    private final OrderDao orderDao;
    private final CartDao cartDao;
    private final OrderPage orderPage;

    public OrderController(HomeController homeController) {
        this.homeController = homeController;
        orderDao = new OrderDao();
        cartDao = new CartDao();
        orderPage = new OrderPage();
    }

    public void checkout() throws SQLException {
        User loggedInUser = getLoggedInUser();
        orderDao.createOrder(loggedInUser.getId());
        int orderId = orderDao.getOrderId();
        if (cartDao.getUserCart(loggedInUser.getId()) != null) {
            ArrayList<Cart> cart = cartDao.getUserCart(loggedInUser.getId());
            for (Cart i : cart) {
                orderDao.insertOrderDetail(orderId, i.getProductId(), i.getCount());
                cartDao.updateOrderCart(loggedInUser.getId(), i.getProductId());
            }
        }
        orderPage.printSuccess();
        homeController.printMenu();
    }

    public void printOrders() throws SQLException, AppException {
        User loggedInUser = getLoggedInUser();
        if (orderDao.getUserOrder(loggedInUser.getId()) == null) {
            orderPage.printNoOrders();
            homeController.printMenu();
        } else {
            ArrayList<Orders> orders = orderDao.getUserOrder(loggedInUser.getId());
            orderPage.printOrder(orders);

                int orderId = enterInt(StringUtils.ENTER_CHOICE);
                if (orderId == 99) {
                    homeController.printMenu();
                } else {
                    boolean orderFound=false;
                    for(Orders order:orders) {
                        if (orderId ==order.getId()) {
                            orderFound=true;
                        }

                    }
                    if(orderFound){
                        ArrayList<OrderDetail> orderDetail=orderDao.printOrderDetail(orderId);
                        orderPage.printOrderDetail(orderDetail);
                        printOrders();
                    }else{
                        println(StringUtils.INVALID_CHOICE);
                        printOrders();
                    }
                }

        }
    }
}
