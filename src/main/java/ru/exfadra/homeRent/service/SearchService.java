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
    public List<Housing> getSearchedHousing(String search){
        return housingRepository
                .findAllByCityContainingOrCountryContainingOrRentDaysContainingOrPriceContainingOrRatingContaining(search, search, Long.parseLong(search) ,
                        Long.parseLong(search),Long.parseLong(search));
    }
}
