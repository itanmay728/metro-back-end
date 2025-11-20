package com.metro.metrobackend.services;

import java.util.List;
import java.util.Map;

import com.metro.metrobackend.models.MetroStation;

public interface MetroStationService {
	
	List<MetroStation> getAllStations();
	
	List<String> findRoute(String from, String to);
	
	List<MetroStation> searchStations(String query);

	Map<String, Object> getRoute(String from, String to);

	
}
