package com.skillbox.sw.controller;

import com.skillbox.sw.api.response.CityApi;
import com.skillbox.sw.api.response.CountryApi;
import com.skillbox.sw.api.response.PlatformLanguageApi;
import com.skillbox.sw.api.response.ResponsePlatformApi;
import com.skillbox.sw.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platform/")
@ResponseBody
public class PlatformController {

    private PlatformService platformService;

    @Autowired
    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping("languages")
    public ResponsePlatformApi getLanguages(@RequestParam String language,
                                            @RequestParam(defaultValue = "0") int offset,
                                            @RequestParam(defaultValue = "20") int itemPerPage) {
        List<PlatformLanguageApi> listLanguage = platformService.getLanguages(language, offset, itemPerPage);
        int total = platformService.getTotalLanguage();
        return new ResponsePlatformApi("done", total, offset, itemPerPage, listLanguage);
    }

    @GetMapping("countries")
    public ResponsePlatformApi getCountries(@RequestParam String country,
                                            @RequestParam(defaultValue = "0") int offset,
                                            @RequestParam(defaultValue = "20") int itemPerPage) {
        List<CountryApi> listCountry = platformService.getCountries(country, offset, itemPerPage);
        int total = platformService.getTotalCountries();
        return new ResponsePlatformApi("done", total, offset, itemPerPage, listCountry);
    }

    @GetMapping("cities")
    public ResponsePlatformApi getCities(@RequestParam int countryId,
                                         @RequestParam String city,
                                         @RequestParam(defaultValue = "0") int offset,
                                         @RequestParam(defaultValue = "20") int itemPerPage) {
        List<CityApi> listCity = platformService.getCities(countryId, city, offset, itemPerPage);
        int total = platformService.getTotalCities();
        return new ResponsePlatformApi("done", total, offset, itemPerPage, listCity);
    }

}
