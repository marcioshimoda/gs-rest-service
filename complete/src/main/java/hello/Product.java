package hello;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class Product {

    private final long id;
    private final String productName;
    private final String description;
    private final String targetMarket[];
    private final String stack[];

    public Product(long id, String productName, String description, String targetMarket[], String stack[]) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.targetMarket = targetMarket;
        this.stack = stack;
    }

    public long getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String[] getTargetMarket() {
        return this.targetMarket;
    }    
    
    public String[] getStack() {
        return this.stack;
    }
    
    public static String getFilter(String[] market, String[] stack) {
    	String filter = "";
    	
    	if (market != null) {
    		for (String mkt: market) {
    			if (filter != "") {
    				filter += " || ('" + mkt + "' in @['targetMarket'])";
    			} else {
    				filter = "('" + mkt + "' in @['targetMarket'])";
    			}
    		}
    	}
		
    	if (stack != null) {
    		for (String stk: stack) {
    			if (filter != "") {
    				filter += " || ('" + stk + "' in @['stack'])";
    			} else {
    				filter = "('" + stk + "' in @['stack'])";
    			}
    		}
    	}
		
		if (filter != "") {
			filter = "$[?("+filter+")]";
		} else {
			filter = "$";
		}
		
		return filter;
    }
    
    public static Product[] parseJson(String jsonString) {
    	return parseJson(jsonString, "$");
    }
    
    public static Product[] parseJson(String jsonString, String filter) {
    	List<Map<String, Object>> dataList = JsonPath.parse(jsonString).read(filter);
		int numberOfItens = dataList.size();
		if (numberOfItens > 0) {
			Product[] result = new Product[numberOfItens];
			for (int i=0; i<numberOfItens; i++) {
				String name = (String) dataList.get(i).get("productName");
				String description = (String) dataList.get(i).get("description");
				String[] targetMarket = toStringArray((JSONArray)dataList.get(i).get("targetMarket"));
				String[] productStack = toStringArray((JSONArray)dataList.get(i).get("stack"));
				
				result[i] = new Product(i, name, description, targetMarket, productStack);		
			}
			
			return result;
		}
		
		return null;
    }
    
    public static Product[] find(String[] market, String[] stack) {
    	String filename = "resource/gubee-teste.json";
    	try {
			String jsonString = new String(Files.readAllBytes(Paths.get(filename)));
			String filter = getFilter(market, stack);
			
			return parseJson(jsonString, filter);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
            	
    	return null;
    }
    
    public static String[] toStringArray(JSONArray array) {
        if(array==null)
            return null;

        String[] arr=new String[array.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=array.get(i).toString();
        }
        return arr;
    }
}
