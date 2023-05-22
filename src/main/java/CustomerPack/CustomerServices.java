package CustomerPack;

import ProductPack.Products;
import StorageLayer.MysqlStorage;

public class CustomerServices {
    private MysqlStorage storage = new MysqlStorage();


    public void shopping() {
        storage.customerShopping();

    }

    public void addToCart(int requiredQuantity, String productName) {

        storage.customerAddingToCart(requiredQuantity, productName);

    }

    public void updateCart(int requiredQuantity, Products product) {
        storage.customerUpdateCart(requiredQuantity, product);

    }

    public void checkout() {
        storage.customerCheckout();

    }

}
