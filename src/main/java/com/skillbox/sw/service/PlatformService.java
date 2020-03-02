package com.skillbox.sw.service;

import com.skillbox.sw.api.response.CityApi;
import com.skillbox.sw.api.response.CountryApi;
import com.skillbox.sw.api.response.PlatformLanguageApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlatformService {

    private static final List<PlatformLanguageApi> listLanguage = new ArrayList<>();
    private static final List<CityApi> listCity = new ArrayList<>();
    private static final List<CountryApi> listCountry = new ArrayList<>();

    static {
        listLanguage.add(new PlatformLanguageApi(1, "Russian"));
        listLanguage.add(new PlatformLanguageApi(2, "English"));
        listLanguage.add(new PlatformLanguageApi(3, "German"));
        listLanguage.add(new PlatformLanguageApi(4, "France"));

        listCountry.add(new CountryApi(1, "Russian"));
        listCountry.add(new CountryApi(2, "English"));
        listCountry.add(new CountryApi(3, "Germany"));
        listCountry.add(new CountryApi(4, "France"));

        listCity.add(new CityApi(1, "Moscow"));
        listCity.add(new CityApi(2, "London"));
        listCity.add(new CityApi(3, "Berlin"));
        listCity.add(new CityApi(4, "Paris"));
    }

    public List<PlatformLanguageApi> getLanguages(String language, int offset, int itemPerPage) {
        // language, offset, itemPerPage - will be used in queries
        return listLanguage.stream()
                .filter(s -> s.getTitle().toLowerCase().indexOf(language) != -1)
                .skip(offset)
                .limit(itemPerPage)
                .collect(Collectors.toList());
    }

    public int getTotalLanguage() {
        return listLanguage.size();
    }

    public List<CityApi> getCities(int countryId, String city, int offset, int itemPerPage) {
        // countryId, city, offset, itemPerPage - will be used in queries
        return listCity.stream()
                .filter(s -> s.getTitle().toLowerCase().indexOf(city) != -1)
                .skip(offset)
                .limit(itemPerPage)
                .collect(Collectors.toList());
    }

    public int getTotalCities() {
        return listCity.size();
    }

    public List<CountryApi> getCountries(String country, int offset, int itemPerPage) {
        // country, offset, itemPerPage - will be used in queries
        return listCountry.stream()
                .filter(s -> s.getTitle().toLowerCase().indexOf(country) != -1)
                .skip(offset)
                .limit(itemPerPage)
                .collect(Collectors.toList());
    }

    public int getTotalCountries() {
        return listCountry.size();
    }

}
