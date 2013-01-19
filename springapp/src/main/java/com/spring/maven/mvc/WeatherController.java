package com.spring.maven.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.validation.Valid;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonaws.util.json.JSONObject;
import com.spring.maven.domain.Weather;
import com.spring.validator.WeatherValidator;

@Controller
@RequestMapping("/")
public class WeatherController {
	WeatherValidator wrValidator;
	public WeatherController(){
		super();
	}
	@Autowired
	public WeatherController(WeatherValidator wrValidator){
		this.wrValidator = wrValidator;
	}

@RequestMapping(method=RequestMethod.POST)	
public String getWeatherInformation(@Valid @ModelAttribute("newWeather")Weather wr,BindingResult result, Model model){
	try{
	HttpClient httpClient = new DefaultHttpClient();
	HttpConnectionParams
	.setConnectionTimeout(httpClient.getParams(), 10000);
	HttpPut method = new HttpPut("http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/"+wr.getPinCode()+".json");
	BasicHeader basicHeader = new BasicHeader(HTTP.CONTENT_TYPE,"application/json");
	HttpResponse response = httpClient.execute(method);
	InputStream instream = response.getEntity().getContent();
	String returnResult = read(instream);
	JSONObject jsonObject = new JSONObject(returnResult);
	if(jsonObject.getJSONObject("response").has("error")){
		wr.setWeatherFind(false);
		wr.setMsg(jsonObject.getJSONObject("response").getJSONObject("error").get("description").toString());
	}
	if(jsonObject.has("current_observation")){
	String state=jsonObject.getJSONObject("current_observation").getJSONObject("display_location").get("state_name").toString();
	String city=jsonObject.getJSONObject("current_observation").getJSONObject("display_location").get("city").toString();
	String temp=jsonObject.getJSONObject("current_observation").get("temperature_string").toString();
	wr.setState(state);
	wr.setCity(city);
	wr.setTemp(temp);
	wr.setWeatherFind(true);
	}
	
	}catch (Exception e) {
		// TODO: handle exception
	}
	return "index";
}
/*@Override
protected ModelAndView handleRequestInternal(HttpServletRequest request,
	HttpServletResponse response) throws Exception {
	
	ModelAndView model = new ModelAndView("index");
	model.addObject("msg", "hello world");

	return model;
}*/

private static String read(InputStream in) throws IOException {
	StringBuilder sb = new StringBuilder();
	BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
	for (String line = r.readLine(); line != null; line = r.readLine()) {
		sb.append(line);
	}
	in.close();
	return sb.toString();
}

@RequestMapping(method = RequestMethod.GET)
public String initForm(ModelMap model){

	Weather wr = new Weather();
	model.addAttribute("newWeather", wr);

	return "index";
}
}
