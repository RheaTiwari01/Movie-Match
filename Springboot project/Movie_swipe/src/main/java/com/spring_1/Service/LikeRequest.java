package com.spring_1.Service;

public class LikeRequest {
	 
	    private int userId;
	    private String movieId;
	    private boolean liked;
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getMovieId() {
			return movieId;
		}
		public void setMovieId(String movieId) {
			this.movieId = movieId;
		}
		public boolean isLiked() {
			return liked;
		}
		public void setLiked(boolean liked) {
			this.liked = liked;
		}

	    // Getters and setters
	

}
