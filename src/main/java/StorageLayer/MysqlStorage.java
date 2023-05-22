package StorageLayer;

import ProductPack.Products;
import User.User;

import java.sql.*;


public class MysqlStorage {
    public static final String COMPLETED = "COMPLETED";
    private static Connection dbDriver = null;
    private static String databaseName = "Ecommerce";
    private static String url = "jdbc:mysql://host.docker.internal:3306/" + databaseName + "?useSSL=false";
    private static String userName = "root";
    private static String password = "rootroot";

    public MysqlStorage() {
        try {
            dbDriver = createConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbDriver = DriverManager.getConnection(url, userName, password);

        return dbDriver;
    }

    public void userSignUp(User user) {
        String query = " insert into user (id, name, password) values (?, ?, ?)";

        try {

            PreparedStatement ps = dbDriver.prepareStatement(query);

            ps.setInt(1, user.getUser_id());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());


            ps.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void userLogin(User user) {
        String query = "select password from user where id = " + user.getUser_id();
        try {
            Statement stmt = dbDriver.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            String givenPassword = user.getPassword();
            String storedPassword = rs.getString("password");
            if (givenPassword.equals(storedPassword)) {

                System.out.println("User Logged In");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }


    public void addProduct(Products product) {
        String query = "Insert into ProductInventory (pid , productName, price, description,quantity) values (?,?,?,?,?)";
        try {

            PreparedStatement ps = dbDriver.prepareStatement(query);

            ps.setInt(1, product.getPid());
            ps.setString(2, product.getProductName());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getQuantity());


            ps.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public boolean checkProductStock(Products product) {
        String query = "select quantity from ProductInventory where pid = " + product.getPid();
        boolean bool = false;
        try {
            Statement stmt = dbDriver.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            rs.next();

            int quantity = rs.getInt("quantity");
            if (quantity > 0)
                bool = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return bool;


    }

    public void shoppingStock() {
        String query = "select * from ProductInventory where quantity > 1";
        String query1 = "Insert into shoppingStock (id , productName, price, description) values (?,?,?,?)";

        try {
            Statement s = dbDriver.createStatement();

            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                PreparedStatement ps = dbDriver.prepareStatement(query1);

                ps.setInt(1, rs.getInt("pid"));
                ps.setString(2, rs.getString("productName"));
                ps.setInt(3, rs.getInt("price"));
                ps.setString(4, rs.getString("description"));
                //-ps.setInt(5, rs.getInt("quantity"));


                ps.executeUpdate();


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void customerShopping() {
        String query = "select * from shoppingStock";
        try {
            Statement s = dbDriver.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                System.out.println("ProductId: " + rs.getInt("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    // INSERT INTO cart (pid,quantity) SELECT pid , (quantity-2) FROM ProductInventory WHERE productName = "Thor";

    public void customerAddingToCart(int requiredQuantity, String productName) {
        String query = "INSERT INTO cart (pid,quantity) "
                + "SELECT pid, " + requiredQuantity
                + " FROM ProductInventory"
                + " WHERE productName =\"" + productName + "\"";
        try {
            Statement s = dbDriver.createStatement();
            int rs = s.executeUpdate(query);
            System.out.println("Product successfully added to cart");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void customerUpdateCart(int latestQuantity, Products product) {
        String query = "UPDATE cart "
                + "SET quantity =" + latestQuantity
                + " WHERE pid =" + product.getPid();
        try {
            Statement s = dbDriver.createStatement();
            int rs = s.executeUpdate(query);
            System.out.println("Product successfully updated in cart");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void customerCheckout() {
        int totalPayable = 0;
        String query = "select ProductInventory.price, cart.quantity from ProductInventory "
                + "inner join cart on"
                + " ProductInventory.pid = cart.pid";
        try {
            Statement s = dbDriver.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                totalPayable += rs.getInt("price") * rs.getInt("quantity");

            }
            System.out.println("Your total Payable Amount:" + totalPayable);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void updateProductDetails(int id, int latestPrice, int latestQuantity) {
        String query = "UPDATE ProductInventory "
                + "SET price =" + latestPrice + ", quantity =" + latestQuantity
                + " WHERE pid=" + id;
        try {
            Statement s = dbDriver.createStatement();
            int rs = s.executeUpdate(query);
            System.out.println("Product successfully updated in inventory");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}


