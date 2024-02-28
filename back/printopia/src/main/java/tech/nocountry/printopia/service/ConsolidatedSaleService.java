package tech.nocountry.printopia.service;

import org.springframework.stereotype.Service;
import tech.nocountry.printopia.persistence.entity.ConsolidatedSale;
import tech.nocountry.printopia.persistence.repository.ConsolidatedSaleRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ConsolidatedSaleService {
    private final ConsolidatedSaleRepository consolidatedSaleRepository;

    public ConsolidatedSaleService(ConsolidatedSaleRepository consolidatedSaleRepository) {
        this.consolidatedSaleRepository = consolidatedSaleRepository;
    }

    public List<ConsolidatedSale> findAll() {
        return consolidatedSaleRepository.findAll();
    }


    public ConsolidatedSale getById(Integer id) {
        return consolidatedSaleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Consolidated Sale not found with id: " + id));
    }

    public List<ConsolidatedSale> getAllByUserEmail(String email) {
        return consolidatedSaleRepository.findSaleByEmail(email);
    }

    public List<ConsolidatedSale> getAllByDate(LocalDate date){
        return consolidatedSaleRepository.findSaleByDate(date);
    }

    public ConsolidatedSale save(ConsolidatedSale consolidatedSale) {
        if (consolidatedSale == null) {
            throw new IllegalArgumentException("Consolidated Sale cannot be null");
        }
        return consolidatedSaleRepository.save(consolidatedSale);
    }

    public ConsolidatedSale update(Integer id, ConsolidatedSale updatedConsolidatedSale) {
        ConsolidatedSale consolidatedSale = getById(id);
        if (consolidatedSale != null) {
            // Validate and update name and description

            if (updatedConsolidatedSale.getSaleDetails() != null) {
                consolidatedSale.setSaleDetails(updatedConsolidatedSale.getSaleDetails());
            }
            if (updatedConsolidatedSale.getSaleDate() != null) {
                consolidatedSale.setSaleDate(updatedConsolidatedSale.getSaleDate());
            }
            if (updatedConsolidatedSale.getUser() != null) {
                consolidatedSale.setUser(updatedConsolidatedSale.getUser());
            }
            if (updatedConsolidatedSale.getShippingCost() != null) {
                consolidatedSale.setShippingCost(updatedConsolidatedSale.getShippingCost());
            }
            if (updatedConsolidatedSale.getTotalCost() != null) {
                consolidatedSale.setTotalCost(updatedConsolidatedSale.getTotalCost());
            }

            return consolidatedSaleRepository.save(consolidatedSale);
        } else {
            throw new NoSuchElementException("Consolidated sale not found with id: " + id);
        }
    }


    public void deleteById(Integer id) {
        if (!consolidatedSaleRepository.existsById(id)) {
            throw new NoSuchElementException("Consolidated Sale not found with id: " + id);
        }
        consolidatedSaleRepository.deleteById(id);
    }

}


