package ru.exfadra.homeRent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.exfadra.homeRent.model.Housing;
import ru.exfadra.homeRent.repository.HousingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HousingService {
    private final HousingRepository housingRepository;

    public List<Housing> getAllHousings()
    {
        return housingRepository.findAll();
    }
    public void saveHousing(Housing housing)
    {
        housingRepository.save(housing);
    }
    public Housing getHousingById(Long id)
    {
        return housingRepository.getById(id);
    }
}
