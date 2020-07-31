package com.minbrat.dao;

import java.util.Date;
import java.util.List;

import com.minbrat.model.Registration;

public interface RegistrationDAO {
	public int saveData(Registration registration);

	public List<Registration> getData(String name, String pinCode, Date startDate, Date endDate);

	List<Registration> getPermutationData(String name);

}
