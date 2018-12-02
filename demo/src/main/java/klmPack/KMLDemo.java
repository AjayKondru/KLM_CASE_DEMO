package klmPack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import entities.Stockdetails;
import repository.StockDetailsRepository;

@SpringBootApplication
@ComponentScan("repository")
@ComponentScan("controller")
public class KMLDemo implements CommandLineRunner{
	
	@Autowired
	StockDetailsRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(KMLDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	//	BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ajay\\Downloads\\f.csv"));
		try (BufferedReader br = new BufferedReader(new FileReader("f.csv"))){
		String line;
		Stockdetails sd = new Stockdetails() ;
		int i=1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		while(br.readLine()!=null)
		{
			line = br.readLine();
			sd.setId(i);
			i++;
	
			sd.setStockDate(dateFormat.parse(line.split(",")[0]));
			sd.setOpen(Double.valueOf(line.split(",")[1]));
			sd.setHigh(Double.valueOf(line.split(",")[2]));
			sd.setLow(Double.valueOf(line.split(",")[3]));
			sd.setClose(Double.valueOf(line.split(",")[4]));
			sd.setAdjClose(Double.valueOf(line.split(",")[5]));
			sd.setVolume(Integer.valueOf(line.split(",")[6]));
			repo.insert(sd);
		}
		
		
	}
		catch (IOException e) {
			e.printStackTrace();
		}
}
}


