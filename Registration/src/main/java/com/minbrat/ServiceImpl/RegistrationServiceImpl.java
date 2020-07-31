package com.minbrat.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minbrat.Service.RegistrationService;
import com.minbrat.dao.RegistrationDAO;
import com.minbrat.model.Registration;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private RegistrationDAO dao;

	@Override
	public int saveData(Registration registration) {
		// TODO Auto-generated method stub
		return dao.saveData(registration);
	}

	@Override
	public List<Registration> getData(String name, String pinCode, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return dao.getData(name, pinCode, startDate, endDate);

	}

	@Override
	public List<Registration> getPermutationData(String name) {
		// TODO Auto-generated method stub
		return dao.getPermutationData(name);
	}

}
