package ru.exfadra.homeRent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.exfadra.homeRent.model.Housing;

import java.util.List;

public interface HousingRepository extends JpaRepository<Housing, Long> {
    List<Housing> findAllByCity(String city);
    List<Housing> findAllByCountry(String country);
    List<Housing> findAllByRentDays(Long rentDays);
    List<Housing> findAllByPrice(String price);
    List<Housing> findAllByRating(String rating);
    List<Housing> findAllByCityContainingOrCountryContainingOrRentDaysContainingOrPriceContainingOrRatingContaining(
            String city, String country, Long rentDays, Long price,Long rating
    );
}
