package fr.dawan.demoapirest.services;

import fr.dawan.demoapirest.dtos.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class LocationServiceImpl implements ILocationService{
    /*
    Pour faire appel à des APIs externes, Spring propose la classe RestTemplate qui permet l'exécution de requêtes HTTP
     */

    @Autowired
    private RestTemplate restTemplate;

    private String uri = "https://dawan.org/public/location";

    @Override
    public List<LocationDto> importFromExternSystem() throws Exception {
        /*
        Pour le get, 2 méthodes:
        getForEntity: renvoie le code status et le contenu du body
        getForObject: renvoie uniquement le contenu du body
         */

        ResponseEntity<LocationDto[]> response = restTemplate.getForEntity(uri, LocationDto[].class);
        if(response.getStatusCode() == HttpStatus.OK && response.hasBody()){
            LocationDto[] result = response.getBody();
            return Arrays.asList(result);
        }

        return null;
    }
}
