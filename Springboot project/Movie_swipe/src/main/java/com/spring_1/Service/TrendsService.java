package com.spring_1.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring_1.Entity.TrendingMovieRepo;
import com.spring_1.Entity.TrendsRepo;

@Service
public class TrendsService {
private TrendingMovieRepo tmr;
private TrendingMoviesService ts;
public TrendsService(TrendingMovieRepo tmr, TrendingMoviesService ts)
{
	this.tmr=tmr;
	this.ts=ts;
}
@Transactional
public void UpdateMovie()
{
	tmr.deleteAll();
	List<Map<String,Object>> trendmovie= ts.trends();
	
	 for (Map<String, Object> movie : trendmovie) {
         TrendsRepo trendingMovie = new TrendsRepo(
             String.valueOf(movie.get("id")),
             String.valueOf(movie.get("title"))
         );
         tmr.save(trendingMovie);
     }
}

}
