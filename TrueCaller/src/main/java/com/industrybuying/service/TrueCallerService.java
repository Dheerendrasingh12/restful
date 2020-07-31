package com.industrybuying.service;

import java.util.List;

import com.industrybuying.model.Truecaller;

public interface TrueCallerService {

	public int saveUser(Truecaller caller);

	public List<Truecaller> getAll();

	public Truecaller getByName(String name);

	public Truecaller getByNumber(long mobno);

}
