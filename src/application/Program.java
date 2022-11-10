package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import entities.Sale;

public class Program {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			List<Sale> sales = new ArrayList<>();
			String line = bufferedReader.readLine();
			while(line != null) {
				String [] fields = line.split(",");
				sales.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]),
						fields[2],Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
				line = bufferedReader.readLine();
			}
		
			Set<String> sellers = new HashSet<>();
			
			for(Sale sale : sales) {
				sellers.add(sale.getSeller());
			}
			System.out.println();
			System.out.println("Total de vendas por vendedor: ");
			
			for(String seller : sellers) {
				double sum = sales.stream()
						.filter(x -> x.getSeller().contains(seller))
						.map(x -> x.getTotal())
						.reduce(0.0, (x,y) -> x + y);
				
				System.out.printf(seller + " - R$ %.2f%n", sum);
			}
		} 
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}

}
