package com.spring_1.Service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
@Service 
 
public class TrendingMoviesService {
private RestTemplate rt;
String api_key= "a7ce488950478f4dea93ac867b75e14f";

public TrendingMoviesService(RestTemplate rt)
{
	this.rt=rt;
}
public List<Map<String,Object>> trends(){
	
	
String url = "https://api.themoviedb.org/3/trending/movie/day?api_key="+api_key;
Map<String,Object> res= rt.getForObject(url,Map.class);
return (List<Map<String, Object>>) res.get("results");


	
}

}
