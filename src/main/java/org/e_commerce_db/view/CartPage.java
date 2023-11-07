package org.e_commerce_db.view;

import org.e_commerce_db.dao.ProductDao;
import org.e_commerce_db.model.Cart;
import org.e_commerce_db.model.ProductInCart;
import org.e_commerce_db.utils.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.e_commerce_db.utils.OutputUtil.println;

public class CartPage {
    private final ProductDao productDao;
    public CartPage() {
        productDao=new ProductDao();
    }

    public void printEmptyCart() {
        println(StringUtils.EMPTY_CART);
    }

    public void printCart(ArrayList<Cart> cartProducts) throws SQLException {
        println(StringUtils.CART);
        double total = 0;
        for (Cart cartProduct : cartProducts) {
            List<ProductInCart> productsInCart = productDao.getCartProduct(cartProduct.getId());

            if (!productsInCart.isEmpty()) {
                ProductInCart product = productsInCart.get(0);
                total += cartProduct.getCount() * product.getPrice();
                println(product.getProductTitle() + " x " + cartProduct.getCount());
            }
        }
        println(StringUtils.TOTAL_PRICE + total);
    }

    public void printCheckout() {
        println("#---------------------#");
        println(StringUtils.PRINT_CHECKOUT);
    }

    public void printBack() {
        println(StringUtils.BACK_OPTION);
    }
}
