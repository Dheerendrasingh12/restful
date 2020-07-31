package com.sociopool.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sociopool.dao.DistanceDao;
import com.sociopool.model.Distance;
import com.sociopool.service.DistanceService;

@Repository
public class DistanceServiceImpl implements DistanceService {
	@Autowired
	private DistanceDao distanceDao;

	@Override
	public void saveDetails(Distance distance) {
		// TODO Auto-generated method stub
		try {
			distanceDao.save(distance);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public int findDetails(int personId, Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub

		try {
			return distanceDao.findByPersonIdOrStartTimeOrEndtime(personId, startTime, endTime);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return 0;
		}
	}

}
