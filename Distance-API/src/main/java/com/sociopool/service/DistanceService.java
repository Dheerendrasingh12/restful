package com.sociopool.service;

import java.sql.Timestamp;

import com.sociopool.model.Distance;

public interface DistanceService {
	public void saveDetails(Distance distance);

	public int findDetails(int personId, Timestamp startTime, Timestamp endTime);
}
