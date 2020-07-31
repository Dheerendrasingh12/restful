package com.industrybuying.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.industrybuying.model.Truecaller;

@Repository
public interface TrueCallerDao extends JpaRepository<Truecaller, Integer> {
	public Truecaller findByName(String name);

	public Truecaller findByMobno(String mobno);
}
