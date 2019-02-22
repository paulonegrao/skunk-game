package servlet;

import java.util.HashMap;

public class Score {
	private HashMap<Integer, Integer> rideScore = new HashMap<Integer, Integer>();
	private int totalScore;
	
	public Score() {
		zeroScore();

	}
	
	public void setRideScore(int ride, int value) {
		rideScore.put(ride, value);
		totalScore = totalScore + value;
	}
	public int getRideScore(int ride) {
		return rideScore.get(ride);
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void addScore(int ride, int value) {
		rideScore.put(ride, rideScore.get(ride) + value);
		totalScore = totalScore + value;
		
	}

	public void zeroScore() {
		rideScore.put(1, 0);
		rideScore.put(2, 0);
		rideScore.put(3, 0);
		rideScore.put(4, 0);
		rideScore.put(5, 0);
	
		totalScore = 0;
	}
}
