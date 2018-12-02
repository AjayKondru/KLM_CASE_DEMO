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
//	static String groupByClause;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public int insert(Stockdetails sd) {
	//	jdbcTemplate.update("INSERT INTO stockdetails VAL-UES sd.getStockDate(),sd.getOpen(),sd.getHigh(),sd.getLow(),sd.getClose(),sd.getAdjClose(),sd.getVolume()");
		
		return  jdbcTemplate.update("insert into stockdetails values(?,  ?,?,  ?, ?,?,  ?, ?)",new Object[] {sd.getId(),sd.getStockDate(),sd.getOpen(),sd.getHigh(),sd.getLow(),sd.getClose(),sd.getAdjClose(),sd.getVolume() });
	}


	public List<Map<String, Object>> getAverageClose(String year,String month,String day) {
		StringBuffer  groupByClause = new StringBuffer(" year(STOCKDATE ) ");
		StringBuffer sql = new StringBuffer(" nvl(avg(close),0) average  from STOCKDETAILS  where    ");
		
		if(year!=null)
		{
		sql = 	sql.append("year(STOCKDATE )="+year+"");
		groupByClause =groupByClause.append(",month(STOCKDATE ) ");
			if(month!=null)
			{
				sql = 	sql.append("and month(STOCKDATE ) = "+month+" ");
				
				groupByClause =groupByClause.append(",day(STOCKDATE )");
				
					if(day!=null)
					{
						sql = 	sql.append(" and day(STOCKDATE ) = "+day+"");
					}
				}
			sql =(new StringBuffer("select ")).append(groupByClause).append(" ,").append(sql.append("group by ").append(groupByClause).append ("order by ").append(groupByClause ));
		}
		//return !jdbcTemplate.queryForList(sql).get(0).get("average").toString().equalsIgnoreCase("0")?jdbcTemplate.queryForList(sql).get(0).get("average").toString():"NO DATA FOR GIVEN PERIOD";
		return jdbcTemplate.queryForList(sql.toString());
		
	}


	public List<Map<String,Object>> getCloseOverPeriod(String year, String month, String day) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(" select  STOCKDATE,close   from STOCKDETAILS  where    ");
		if(year!=null)
		{
		sql = 	sql.append("year(STOCKDATE )="+year+"");
			if(month!=null)
			{
				sql = 	sql.append("and month(STOCKDATE ) = "+month+"");
					if(day!=null)
					{
						sql = 	sql.append(" and day(STOCKDATE ) = "+day+"");
					}
				}
		}
		List<Map<String,Object>> l = jdbcTemplate.queryForList(sql.toString());
		
		return l;
		
	}

}
     