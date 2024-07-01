package com.weather;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class FetchCity
 */
@WebServlet("/FetchCity")
public class FetchCity extends HttpServlet {
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city = request.getParameter("city");
		String apiKey = "728eb648d27f221a4eab18c1ba948cbb";
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey;
		
		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		InputStream inputStream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream);
		StringBuilder responseContent = new StringBuilder();
		Scanner scanner = new Scanner(reader);
		
		while(scanner.hasNext()) {
			responseContent.append(scanner.nextLine());
			
		}
		scanner.close();
		Gson gson= new Gson();
		JsonObject jsonObject = gson.fromJson(responseContent.toString(),JsonObject.class);
		
		long dateTimeStamp = jsonObject.get("dt").getAsLong()*1000;
		String date = new Date(dateTimeStamp).toString();
		
		//Temp
		double tempretureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		int tempretureCelsius = (int)(tempretureKelvin-273.15);
		
		//humidty
		int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
		
		//windspeed
		double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
		System.out.println(tempretureCelsius);
		
		//weather condition
		String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		
		//set the data
		request.setAttribute("date", date);
		request.setAttribute("city", city);
		request.setAttribute("temperature", tempretureCelsius);
		request.setAttribute("weatherCondition", weatherCondition);
		request.setAttribute("humidity", humidity);
		request.setAttribute("windSpeed", windSpeed);
		request.setAttribute("weatherData", responseContent.toString());
		connection.disconnect();
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
		
		
		
	}

}
