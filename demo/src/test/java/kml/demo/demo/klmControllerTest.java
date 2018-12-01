package kml.demo.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import controller.Klmcontroller;
import entities.Stockdetails;
import junit.framework.Assert;
import repository.StockDetailsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
	    classes = Klmcontroller.class
	)
@WebAppConfiguration
@AutoConfigureMockMvc
public class klmControllerTest {

	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	StockDetailsRepository repo;
	@Autowired
	StockDetailsRepository repoOrg;
	
	@Test
	public void getAverageClose() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Stockdetails sd = new Stockdetails();
		sd.setId(1);
		try {
			sd.setStockDate(dateFormat.parse("01/06/72"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sd.setOpen(Double.valueOf("2.149165"));
		sd.setHigh(Double.valueOf("2.173495"));
		sd.setLow(Double.valueOf("2.149165"));
		sd.setClose(Double.valueOf("2.15322"));
		sd.setAdjClose(Double.valueOf("0.003195"));
		sd.setVolume(Integer.valueOf("1089200"));
		List avgClose = repoOrg.getAverageClose( "1972", "6", "7");
		Mockito.when(
				repo.getAverageClose(Mockito.anyString(),Mockito.anyString(),
						Mockito.anyString())).thenReturn(avgClose);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"getCloseOverPeriod?year=1972");
		MvcResult result = null;
		try {
			 result = mockMVC.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String expected = "{YEAR(STOCKDATE):1972,MONTH(STOCKDATE):1,AVERAGE:2.1248345}";

		Assert.assertNotNull(result);
		
	}
	
	@Test
	public void getCloseOverPeriod() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Stockdetails sd = new Stockdetails();
		sd.setId(1);
		try {
			sd.setStockDate(dateFormat.parse("01/06/72"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sd.setOpen(Double.valueOf("2.149165"));
		sd.setHigh(Double.valueOf("2.173495"));
		sd.setLow(Double.valueOf("2.149165"));
		sd.setClose(Double.valueOf("2.15322"));
		sd.setAdjClose(Double.valueOf("0.003195"));
		sd.setVolume(Integer.valueOf("1089200"));
		List avgClose = repoOrg.getCloseOverPeriod( "1972", "6", "7");
		Mockito.when(
				repo.getCloseOverPeriod(Mockito.anyString(),Mockito.anyString(),
						Mockito.anyString())).thenReturn(avgClose);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"getCloseOverPeriod?year=1972");
		MvcResult result = null;
		try {
			 result = mockMVC.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String expected = "{STOCKDATE:1972-01-06,CLOSE:2.15322}";

		Assert.assertNotNull(result);
		
	}

}
