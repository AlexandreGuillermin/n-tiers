package com.tse.ntiers.service;

import java.util.List;

import com.tse.ntiers.business.Eleve;
import com.tse.ntiers.business.Mobility;

public interface MobilityService {
	
	public Eleve addMobility(Mobility mobility);
	public List<Mobility> getMobility();
	public List<Mobility> getMobilityFilter(Mobility mobility);
	public Mobility getMobility(Long studentId);
	public Boolean delMobility(Mobility mobility);
}
