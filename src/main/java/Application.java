
import User.*;
import ProductPack.*;
import AdminPack.*;
import CustomerPack.*;

import java.sql.SQLException;

import StorageLayer.*;

public class Application {
    private static String update;

    public static void main (String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        MysqlStorage mysqlStorage = new MysqlStorage();


        ProductInventory inventory = new ProductInventory();
        AdminServices adminServices = new AdminServices();
        CustomerServices customerServices = new CustomerServices();


        User user = new User(1,"mounisha","maduapalli");

        UserService us = new UserService();
        us.signup(user);

        Products product = new Products();
        product.setPid(6);
        product.setProductName("Hugsy");
        product.setPrice(150);
        product.setDescription("soft toy");
        product.setQuantity(15);
//
//
//		//us.signup(u);
//
//
        us.login(user);
//
//
//		inventory.updateInventory(product);
//        if(inventory.checkAvailability(product) == true) {
//            System.out.println("Product is accessible to user");
//        }

//		adminServices.updateShoppingList();

        customerServices.shopping();

        adminServices.updateProducts(2, 15, 2);
        customerServices.addToCart(2, "Hugsy");
        customerServices.updateCart(1, product);
        customerServices.checkout();







    }

}

