package com.minbrat.Service;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.minbrat.model.Registration;

public interface RegistrationService {
	public int saveData(Registration registration);

	public List<Registration> getData(String name, String pinCode, Date startDate, Date endDate);

	public List<Registration> getPermutationData(String name);

}
