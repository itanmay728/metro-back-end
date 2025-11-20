package com.metro.metrobackend.services.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import org.springframework.stereotype.Service;
import com.metro.metrobackend.models.MetroStation;
import com.metro.metrobackend.services.MetroStationService;
import com.opencsv.CSVReader;

@Service
public class MetroStationServiceImpl implements MetroStationService {

	// ==============================
	// MASTER DATA STRUCTURES
	// ==============================
	private Map<String, MetroStation> stations = new HashMap<>();
	private Map<String, List<String>> graph = new HashMap<>();

	// GTFS Mappings
	private Map<String, String> tripToRoute = new HashMap<>();
	private Map<String, String> stopToTrip = new HashMap<>();

	private Map<String, String> routeNames = new HashMap<>();
	private Map<String, String> routeColors = new HashMap<>();

	// ==============================
	// CONSTRUCTOR: load all data
	// ==============================
	public MetroStationServiceImpl() {
		loadRoutes("data/routes.txt");
		loadTrips("data/trips.txt");
		loadStations("data/stops.txt");
		loadGraph("data/stop_times.txt");
	}

	// ==============================
	// PUBLIC API METHODS
	// ==============================

	@Override
	public List<MetroStation> getAllStations() {
		return new ArrayList<>(stations.values());
	}

	@Override
	public List<String> findRoute(String from, String to) {
		String fromId = findStationIdByName(from);
		String toId = findStationIdByName(to);
		return shortestPath(fromId, toId);
	}

	@Override
	public List<MetroStation> searchStations(String query) {
//		getAllStations();
		String q = query.toLowerCase();
		return stations.values().stream()
				.filter(s -> s.getName().toLowerCase().contains(q))
				.limit(10)
				.toList();
	}

	// ==============================
	// NEW: FULL ROUTE RESPONSE (NO DTO)
	// ==============================
	@Override
	public Map<String, Object> getRoute(String fromName, String toName) {

	    String fromId = findStationIdByName(fromName);
	    String toId = findStationIdByName(toName);

	    List<String> ids = shortestPath(fromId, toId);
	    List<Map<String, Object>> result = new ArrayList<>();

	    String previousLine = null;

	    for (String stopId : ids) {

	        MetroStation st = stations.get(stopId);

	        String tripId = stopToTrip.get(stopId);
	        String routeId = tripToRoute.get(tripId);

	        String line = routeNames.getOrDefault(routeId, "Unknown Line");
	        String color = routeColors.getOrDefault(routeId, "#999999");

	        // Detect interchange
	        boolean isInterchange = previousLine != null && !previousLine.equals(line);

	        Map<String, Object> node = new HashMap<>();
	        node.put("id", st.getId());
	        node.put("name", st.getName());
	        node.put("lineName", line);
	        node.put("color", color);
	        node.put("interchange", isInterchange);

	        result.add(node);

	        previousLine = line;
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("totalStations", result.size());
	    response.put("stations", result);

	    return response;
	}


	// ==============================
	// PRIVATE UTILITY METHODS
	// ==============================

	private String findStationIdByName(String name) {
		return stations.values().stream()
				.filter(s -> s.getName().equalsIgnoreCase(name))
				.map(MetroStation::getId)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Station not found: " + name));
	}

	// ---------- BFS shortest path ----------
	private List<String> shortestPath(String src, String dest) {

		Queue<String> q = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		Map<String, String> parent = new HashMap<>();

		q.add(src);
		visited.add(src);
		parent.put(src, null);

		while (!q.isEmpty()) {
			String curr = q.poll();

			if (curr.equals(dest)) break;

			List<String> neighbors = graph.getOrDefault(curr, new ArrayList<>());

			for (String next : neighbors) {
				if (!visited.contains(next)) {
					visited.add(next);
					parent.put(next, curr);
					q.add(next);
				}
			}
		}

		// reconstruct path
		List<String> path = new ArrayList<>();
		String step = dest;

		while (step != null) {
			path.add(step);
			step = parent.get(step);
		}

		Collections.reverse(path);
		return path;
	}

	// ==============================
	// LOAD GTFS FILES
	// ==============================

	// ---------- ROUTES (route_id -> line name/color) ----------
	private void loadRoutes(String fileName) {
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {

			if (is == null) {
				System.out.println("❌ routes.txt NOT found");
				return;
			}

			CSVReader reader = new CSVReader(new InputStreamReader(is));
			reader.readNext();

			String[] row;

			while ((row = reader.readNext()) != null) {
				String routeId = row[0];
				String routeShortName = row[2];

				String lineName = extractLineName(routeShortName);
				String color = getColorFromLine(lineName);

				routeNames.put(routeId, lineName);
				routeColors.put(routeId, color);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------- TRIPS (trip_id -> route_id) ----------
	private void loadTrips(String fileName) {
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {

			if (is == null) {
				System.out.println("❌ trips.txt NOT found");
				return;
			}

			CSVReader reader = new CSVReader(new InputStreamReader(is));
			reader.readNext();

			String[] row;

			while ((row = reader.readNext()) != null) {
				String routeId = row[0];
				String tripId = row[2];
				tripToRoute.put(tripId, routeId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------- STATIONS LOADER ----------
	private void loadStations(String fileName) {

		try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {

			if (is == null) {
				System.out.println("❌ stops.txt NOT found");
				return;
			}

			CSVReader reader = new CSVReader(new InputStreamReader(is));
			reader.readNext();

			String[] row;

			while ((row = reader.readNext()) != null) {
				String id = row[0];
				String name = row[2];
				double lat = Double.parseDouble(row[4]);
				double lon = Double.parseDouble(row[5]);

				stations.put(id, new MetroStation(id, name, lat, lon));
			}

			System.out.println("✔ Stations loaded: " + stations.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------- GRAPH BUILDER ----------
	private void loadGraph(String stopTimesFile) {

		try (InputStream is = getClass().getClassLoader().getResourceAsStream(stopTimesFile)) {

			if (is == null) {
				System.out.println("❌ stop_times.txt NOT found");
				return;
			}

			CSVReader reader = new CSVReader(new InputStreamReader(is));
			reader.readNext();

			Map<String, List<String[]>> tripStops = new HashMap<>();

			String[] row;

			while ((row = reader.readNext()) != null) {
				String tripId = row[0];
				String stopId = row[3];
				String seq = row[4];

				stopToTrip.put(stopId, tripId);

				tripStops.putIfAbsent(tripId, new ArrayList<>());
				tripStops.get(tripId).add(new String[]{stopId, seq});
			}

			// Build adjacency list
			for (String tripId : tripStops.keySet()) {

				List<String[]> stopsList = tripStops.get(tripId);
				stopsList.sort(Comparator.comparingInt(s -> Integer.parseInt(s[1])));

				for (int i = 0; i < stopsList.size() - 1; i++) {
					addEdge(stopsList.get(i)[0], stopsList.get(i + 1)[0]);
				}
			}

			System.out.println("✔ Graph loaded. Nodes: " + graph.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------- Add edge to graph ----------
	private void addEdge(String from, String to) {
		graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
		graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
	}

	// ==============================
	// LINE NAME & COLOR HELPERS
	// ==============================
	private String extractLineName(String s) {
	    if (s == null || s.isEmpty()) return "Unknown Line";

	    s = s.toUpperCase();

	    // Get the first character of route short name
	    char c = s.charAt(0);

	    return switch (c) {
	        case 'B' -> "Blue Line";
	        case 'Y' -> "Yellow Line";
	        case 'R' -> "Red Line";
	        case 'P' -> "Pink Line";
	        case 'M' -> "Magenta Line";
	        case 'G' -> "Green Line";
	        case 'V' -> "Violet Line";
	        case 'A' -> "Aqua Line";
	        case 'O' -> "Airport Line"; // Orange Line (Airport)
	        default -> "Unknown Line";
	    };
	}


	private String getColorFromLine(String line) {
	    return switch (line) {
	        case "Blue Line" -> "#005BBB";
	        case "Yellow Line" -> "#FFD700";
	        case "Red Line" -> "#E31E3C";
	        case "Pink Line" -> "#E91E63";
	        case "Magenta Line" -> "#D81B60";
	        case "Violet Line" -> "#6A1B9A";
	        case "Green Line" -> "#1A9A5B";
	        case "Aqua Line" -> "#00BCD4";
	        case "Grey Line" -> "#9E9E9E";
	        case "Airport Line" -> "#FF8C00";
	        case "Rapid Metro" -> "#7B1FA2";
	        default -> "#999999";
	    };
	}

}
