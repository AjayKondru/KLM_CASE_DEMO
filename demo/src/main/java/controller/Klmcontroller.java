package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import repository.StockDetailsRepository;

@RestController
public class Klmcontroller {
	@Autowired
	StockDetailsRepository repo;	
	
/*@RequestMapping("/getAvgClose/{year}")
 public int getAverageClose(@PathVariable (name = "year")String year)
 {
	return repo.getAverageClose(year);
	
	
 }*/
@RequestMapping("/getAverageClose")
public List<Map<String, Object>> getAverageClose(@RequestParam(name="year", required = false) String year,@RequestParam(name="month", required = false) String month,
		@RequestParam(name="day", required = false) String day)
{
	
	List<Map<String, Object>> avgClose = null ;
	if(year!=null)
	{
	avgClose = repo.getAverageClose( year, month, day);
	}
	
	
	return avgClose;
	
	
}


@RequestMapping("/getCloseOverPeriod")
public List<Map<String,Object>> getCloseOverPeriod(@RequestParam(name="year", required = false) String year,@RequestParam(name="month", required = false) String month,
		@RequestParam(name="day", required = false) String day)
{
	List<Map<String, Object>> avgClose = null ;
	if(year!=null)
	{
	avgClose = repo.getCloseOverPeriod( year, month, day);
	}
	
	
	return avgClose;
	
	
	
	
}
		 
}
