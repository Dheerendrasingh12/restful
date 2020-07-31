package com.industrybuying.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.industrybuying.dao.TrueCallerDao;
import com.industrybuying.model.Truecaller;
import com.industrybuying.service.TrueCallerService;

@Repository
public class TrueCallerServiceImpl implements TrueCallerService {
	@Autowired
	private TrueCallerDao callerDao;

	@Override
	public int saveUser(Truecaller caller) {
		// TODO Auto-generated method stub
		try {
			callerDao.save(caller);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return 0;
		}

	}

	@Override
	public List<Truecaller> getAll() {
		// TODO Auto-generated method stub
		return callerDao.findAll();
	}

	@Override
	public Truecaller getByName(String name) {
		// TODO Auto-generated method stub
		try {
			Truecaller caller = callerDao.findByName(name);
			if (caller != null)
				return caller;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	@Override
	public Truecaller getByNumber(long mobno) {
		// TODO Auto-generated method stub
		Truecaller optional = callerDao.findByMobno(String.valueOf(mobno));
		if (optional != null)
			return optional;

		return null;
	}

}
