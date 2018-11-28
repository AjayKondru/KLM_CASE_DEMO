package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Stockdetails;

@Repository
public class StockDetailsRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public int insert(Stockdetails sd) {
	//	jdbcTemplate.update("INSERT INTO stockdetails VAL-UES sd.getStockDate(),sd.getOpen(),sd.getHigh(),sd.getLow(),sd.getClose(),sd.getAdjClose(),sd.getVolume()");
		
		return  jdbcTemplate.update("insert into stockdetails values(?,  ?,?,  ?, ?,?,  ?, ?)",new Object[] {sd.getId(),sd.getStockDate(),sd.getOpen(),sd.getHigh(),sd.getLow(),sd.getClose(),sd.getAdjClose(),sd.getVolume() });
	}


	public String getAverageClose(String year,String month,String day) {
		
		String sql =" select  nvl(avg(close),0) average  from STOCKDETAILS  where    ";
		if(year!=null)
		{
		sql = 	sql.concat("year(STOCKDATE )="+year+"");
			if(month!=null)
			{
				sql = 	sql.concat("and month(STOCKDATE ) = "+month+"");
					if(day!=null)
					{
						sql = 	sql.concat(" and day(STOCKDATE ) = "+day+"");
					}
				}
		}
		return !jdbcTemplate.queryForList(sql).get(0).get("average").toString().equalsIgnoreCase("0")?jdbcTemplate.queryForList(sql).get(0).get("average").toString():"NO DATA FOR GIVEN PERIOD";
		 
		
	}


	public List<Map<String,Object>> getCloseOverPeriod(String year, String month, String day) {
		// TODO Auto-generated method stub
		String sql =" select  STOCKDATE,close   from STOCKDETAILS  where    ";
		if(year!=null)
		{
		sql = 	sql.concat("year(STOCKDATE )="+year+"");
			if(month!=null)
			{
				sql = 	sql.concat("and month(STOCKDATE ) = "+month+"");
					if(day!=null)
					{
						sql = 	sql.concat(" and day(STOCKDATE ) = "+day+"");
					}
				}
		}
		List<Map<String,Object>> l = jdbcTemplate.queryForList(sql);
		
		return l;
		
	}

}
     