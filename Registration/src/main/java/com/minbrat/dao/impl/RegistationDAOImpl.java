package com.minbrat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.minbrat.dao.RegistrationDAO;
import com.minbrat.model.Address;
import com.minbrat.model.Registration;

@Repository
public class RegistationDAOImpl implements RegistrationDAO {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int saveData(Registration registration) {
		// TODO Auto-generated method stub
		int count = 0;
		if (registration == null)
			return 0;
		List<Address> addressList = registration.getAddress();
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO registration ( NAME, FNAME, REGISTRATIONDATE) VALUES ");
		builder.append(" ( :name, :fname, :registrationDate)  ");
		Map namedParameters = new HashMap();
		// namedParameters.put("id", registration.getId());
		namedParameters.put("name", registration.getName());
		namedParameters.put("fname", registration.getFname());
		namedParameters.put("registrationDate", registration.getRegistrationDate());
		try {
			count = namedParameterJdbcTemplate.update(builder.toString(), namedParameters);
			namedParameters = null;
			namedParameters = new HashMap();
			StringBuilder id = new StringBuilder("SELECT MAX(ID) FROM registration");
			try {
				int regId = namedParameterJdbcTemplate.queryForObject(id.toString(), new MapSqlParameterSource(),
						Integer.class);
				namedParameters.put("RegistrationId", regId);
				if (addressList != null && !addressList.isEmpty()) {
					for (Address address : addressList) {
						namedParameters.put("addHno", address.getAddHno());
						namedParameters.put("street", address.getStreet());
						namedParameters.put("city", address.getCity());
						namedParameters.put("state", address.getState());

						namedParameters.put("country", address.getCountry());
						namedParameters.put("pincode", address.getPincode());
						StringBuilder addressBuilder = new StringBuilder();

						addressBuilder.append(
								"INSERT INTO Address ( RegistrationId ,AddHno, street,City, State, Country,PinCode) VALUES ");
						addressBuilder
								.append(" ( :RegistrationId, :addHno, :street, :city, :state, :country,:pincode)  ");
						try {
							count = namedParameterJdbcTemplate.update(addressBuilder.toString(), namedParameters);
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(e.getMessage());

						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return count;
	}

	@Override
	public List<Registration> getData(String name, String pinCode, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuilder listData = new StringBuilder();
		listData.append(
				"SELECT ADDR.ID,NAME,FNAME,REGISTRATIONDATE, addHno, STREET, CITY, STATE, COUNTRY,PINCODE FROM REGISTRATION REG");
		listData.append(" LEFT JOIN ADDRESS ADDR ON REG.ID=ADDR.registrationId WHERE ");
		listData.append(" name =:name or  pinCode =:pinCode ");
		if (startDate != null) {
			listData.append(" startDate => =:startDate and startDate<= endDate ");
		}
		Map namedParameters = new HashMap();
		namedParameters.put("name", name);
		namedParameters.put("pinCode", pinCode);
		namedParameters.put("startDate", startDate);
		namedParameters.put("endDate", endDate);
		try {
			List<Registration> data = namedParameterJdbcTemplate.query(listData.toString(), namedParameters,
					new RegistrationMapper());
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}

	}

	public class RegistrationMapper implements RowMapper {

		public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
			Registration registration = new Registration();
			registration.setId(rs.getInt("ID"));
			registration.setFname(rs.getString("FNAME"));
			registration.setName(rs.getString("NAME"));
			registration.setRegistrationDate(rs.getDate("REGISTRATIONDATE"));
			Address address = new Address();
			address.setAddHno(rs.getString("addHno"));
			address.setStreet(rs.getString("STREET"));
			address.setCity(rs.getString("CITY"));
			address.setState(rs.getString("STATE"));
			address.setCountry(rs.getString("COUNTRY"));
			address.setPincode(rs.getString("PINCODE"));
			return registration;
		}

	}

	@Override
	public List<Registration> getPermutationData(String name) {
		// TODO Auto-generated method stub
		StringBuilder listData = new StringBuilder();
		listData.append(
				"SELECT REG.ID,NAME,FNAME,REGISTRATIONDATE, addHno, STREET, CITY, STATE, COUNTRY,PINCODE FROM REGISTRATION REG");
		listData.append(" LEFT JOIN ADDRESS ADDR ON REG.ID=ADDR.registrationId WHERE ");
		listData.append(" name LIKE :name or  FNAME LIKE :name or addHno LIKE :name or STREET LIKE :name or ");
		listData.append("CITY LIKE :name or COUNTRY LIKE :name ");
		Map namedParameters = new HashMap();
		namedParameters.put("name", "%" + name + "%");
		namedParameters.put("FNAME", "%" + name + "%");
		namedParameters.put("addHno", "%" + name + "%");
		namedParameters.put("STREET", "%" + name + "%");
		namedParameters.put("CITY", "%" + name + "%");
		namedParameters.put("COUNTRY", "%" + name + "%");

		try {
			List<Registration> data = namedParameterJdbcTemplate.query(listData.toString(), namedParameters,
					new RegistrationMapper());
			return data;
		} catch (Exception e) {
			System.out.println(e);
			return null; // TODO: handle exception
		}
	
	}

}
