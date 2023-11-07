package org.e_commerce_db.view;

import org.e_commerce_db.dao.CartDao;
import org.e_commerce_db.dao.ProductDao;
import org.e_commerce_db.model.Category;
import org.e_commerce_db.model.OrderDetail;
import org.e_commerce_db.model.Orders;
import org.e_commerce_db.model.ProductInCart;
import org.e_commerce_db.utils.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.e_commerce_db.utils.OutputUtil.println;

public class OrderPage {
    private final ProductDao productDao;
    public OrderPage() {
     productDao=new ProductDao();
    }

    public void printSuccess() {
        try {
            println("#---------------------#");
            println(StringUtils.PLACE_ORDER);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOrder(ArrayList<Orders> orders) {
        println("#---------------------#");
        println(StringUtils.ORDERS);
        println("#---------------------#");

        for (Orders order : orders) {
            println(order.getId() + ". " + order.getStatus()+" "+order.getDate());
        }
        println(StringUtils.BACK_OPTION);
    }

    public void printDesign() {
        println("#---------------------#");
    }

    public void printNoOrders() {
        try {
            println("#---------------------#");
            println(StringUtils.NO_ORDER);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOrderDetail(ArrayList<OrderDetail> orderDetail) throws SQLException {
        println("#---------------------#");
        println("Order Detail");
        println("#---------------------#");

        for (OrderDetail order : orderDetail) {
            List<ProductInCart> productsInOrder = productDao.getCartProduct(order.getProductId());

            if (!productsInOrder.isEmpty()) {
                ProductInCart product = productsInOrder.get(0);
                println(product.getProductTitle() + " * " + order.getCount());
            }
        }
        println(StringUtils.BACK_OPTION);
    }
}
