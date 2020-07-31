package com.sociopool.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sociopool.model.Distance;

@Repository
public interface DistanceDao extends JpaRepository<Distance, Integer> {
	public int findByPersonIdOrStartTimeOrEndtime(long personId,Timestamp startTime, Timestamp endTime);
}
