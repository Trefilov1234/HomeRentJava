package ru.exfadra.homeRent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.exfadra.homeRent.model.Housing;
import ru.exfadra.homeRent.repository.HousingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final HousingRepository housingRepository;

    public List<Housing> getSearchedHousingByCountry(String country){
        return housingRepository.findAllByCountry(country);
    }
    public List<Housing> getSearchedHousingByCity(String city){
        return housingRepository.findAllByCity(city);
    }
    public List<Housing> getSearchedHousingByRentDays(String rentDays){
        return housingRepository.findAllByRentDays(Long.parseLong(rentDays));
    }
    public List<Housing> getSearchedHousingByPrice(String price){
        return housingRepository.findAllByPrice(Long.parseLong(price));
    }
    public List<Housing> getSearchedHousingByRating(String rating){
        return housingRepository.findAllByRating(Long.parseLong(rating));
    }
}
