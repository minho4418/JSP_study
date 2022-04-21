package service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

class JunitTest {
	CovidXMLService covidService = new CovidXMLService();
	@Test
	void testCovid(){
		//코로나 확진자
		covidService.covidParsing("20220405", "20220412");
	}
	
	
	//컨트리 테스트
	CountryJSONService countryService = new CountryJSONService(); 
	@Test
	void testCountry() {
		countryService.countryParsing("GB");

	}
	
	
	

}
