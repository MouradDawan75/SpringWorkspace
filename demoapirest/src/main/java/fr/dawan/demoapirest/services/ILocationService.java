package fr.dawan.demoapirest.services;

import fr.dawan.demoapirest.dtos.LocationDto;

import java.util.List;

public interface ILocationService {
    List<LocationDto> importFromExternSystem() throws Exception;
}
