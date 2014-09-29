package hello;

import java.util.Scanner;
import org.springframework.web.client.RestTemplate;

public class GiftFinder {

	/**
	 * Main method which calls on the Zappos API in order
	 * to perform a simple search and return a list of items
	 * whose prices add up to be less than or equal to the amount
	 * specified by the user.
	 */
    public static void main(String args[]) {
    	int numProducts;
    	int totalAmount;
    	Scanner kb = new Scanner(System.in);
    	
        RestTemplate restTemplate = new RestTemplate();
        Page page = restTemplate.getForObject("http://api.zappos.com/Search/term/gifts?limit=20&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73", Page.class);
        
        System.out.print("Desired number of products: ");
    	numProducts = Integer.parseInt(kb.nextLine());
    	System.out.print("Desired dollar amount: $");
    	totalAmount = Integer.parseInt(kb.nextLine());
    	int[] suggestions = new int[numProducts];

        double[] prices = new double[20];
        String[] productNames = new String[20];
        for(int i = 0; i < 20; i++) {
        	prices[i] = Double.parseDouble(page.getPriceInfo(i).substring(7));
        }
        
        System.out.println();
        for(int i = 0; i < 20; i++) {
        	productNames[i] = page.getNameInfo(i).substring(13);
        }
        
        double sum = 0;
        int j = 0;
        for(int i = 0; i < 20; i++) {
        	if(sum < totalAmount && j < numProducts && numProducts != 1) {
        		double temp = sum;
        		if (temp + prices[i] < totalAmount) {
        			sum = prices[i] + sum;
        			suggestions[j++] = i;
        		}
        	}
        	if(numProducts == 1) {
        		double temp = sum;
        		if (temp < prices[i] && prices[i] <= totalAmount){
        			sum = prices[i];
        			suggestions[j] = i;
        		}
        	}
        	if(i == 19 && suggestions[numProducts-1] != 0) {
        		i++;
        	}
        }
        
        for(int i = 0; i < numProducts; i++) {
        	if (i > 1 && suggestions[i] == 0) {
        		i = numProducts;
        	}
        	else {
        		System.out.println(productNames[suggestions[i]] + ": $" + prices[suggestions[i]] + 0);
        	}
        }
        System.out.println("Total: $" + sum + 0);
        kb.close();
    }

}