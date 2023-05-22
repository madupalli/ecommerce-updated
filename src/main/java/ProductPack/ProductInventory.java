package ProductPack;

import StorageLayer.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductInventory {

	//Find when to use Hashmap
	public Map <String,String> productsNameAndDescription = new LinkedHashMap<>();
	public Map <Integer,Integer> productIdAndPrice = new LinkedHashMap<>();
	public Map <Integer,Integer> productIdAndQuantity = new LinkedHashMap<>();
	public Map <LinkedHashMap,LinkedHashMap> inventory  = new LinkedHashMap<>();

	
	MysqlStorage mysqlStorage;

	public ProductInventory() {
		this.mysqlStorage = mysqlStorage;
	}

	public void updateInventory(Products product) {
		
		mysqlStorage.addProduct(product);
		
	}
	
	public boolean checkAvailability(Products product) {
		
		boolean flag =mysqlStorage.checkProductStock(product);
		
		return flag;
		
	}

}
