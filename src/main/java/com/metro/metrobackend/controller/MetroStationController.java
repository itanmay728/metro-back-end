package com.metro.metrobackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.metro.metrobackend.models.MetroStation;
import com.metro.metrobackend.services.MetroStationService;

@RestController
@RequestMapping("/api/public/metro")
public class MetroStationController {

    private final MetroStationService metroStationService;

    public MetroStationController(MetroStationService metroStationService) {
        this.metroStationService = metroStationService;
    }

    // ===============================================
    // 1) GET ALL STATIONS
    // ===============================================
    @GetMapping("/stations")
    public List<MetroStation> getAllStations() {
        return metroStationService.getAllStations();
    }

    // ===============================================
    // 2) AUTOCOMPLETE SEARCH
    // ===============================================
    @GetMapping("/search")
    public List<MetroStation> searchStations(@RequestParam String query) {
        return metroStationService.searchStations(query);
    }

    // ===============================================
    // 3) GET FULL ROUTE DETAILS (Station Objects)
    // ===============================================
    @GetMapping("/route")
    public Map<String, Object> getRoute(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return metroStationService.getRoute(from, to);
    }
}
