package fr.dawan.demoapirest.controllers;

import fr.dawan.demoapirest.dtos.LocationDto;
import fr.dawan.demoapirest.services.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private ILocationService locationService;

    @GetMapping(value = "", produces = "application/json")
    public List<LocationDto> getLocations() throws Exception{
        return locationService.importFromExternSystem();
    }
}
