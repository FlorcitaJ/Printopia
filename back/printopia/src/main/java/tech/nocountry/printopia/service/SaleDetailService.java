package tech.nocountry.printopia.service;

import org.springframework.stereotype.Service;
import tech.nocountry.printopia.persistence.entity.SaleDetail;
import tech.nocountry.printopia.persistence.repository.SaleDetailRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;

    public SaleDetailService(SaleDetailRepository saleDetailRepository) {
        this.saleDetailRepository = saleDetailRepository;
    }


    public List<SaleDetail> findAll() {
        return saleDetailRepository.findAll();
    }

    public SaleDetail getById(Integer id) {
        return saleDetailRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sale detail not found with id: " + id));
    }

    public SaleDetail save(SaleDetail saleDetail) {
        if (saleDetail == null) {
            throw new IllegalArgumentException("Sale Detail cannot be null");
        }
        return saleDetailRepository.save(saleDetail);
    }

    public SaleDetail update(Integer id, SaleDetail updatedSaleDetail) {
        SaleDetail saleDetail = getById(id);
        if (saleDetail != null) {
            // Validate and update name and description

            if (updatedSaleDetail.getConsolidatedSale() != null) {
                saleDetail.setConsolidatedSale(updatedSaleDetail.getConsolidatedSale());
            }
            if (updatedSaleDetail.getProduct() != null) {
                saleDetail.setProduct(updatedSaleDetail.getProduct());
            }
            if (updatedSaleDetail.getPrice() != null) {
                saleDetail.setPrice(updatedSaleDetail.getPrice());
            }
            if (updatedSaleDetail.getQuantity() != null) {
                saleDetail.setQuantity(updatedSaleDetail.getQuantity());
            }
            if (updatedSaleDetail.getWasPromotional() != null) {
                saleDetail.setWasPromotional(updatedSaleDetail.getWasPromotional());
            }
            if (updatedSaleDetail.getWeight() != null) {
                saleDetail.setWeight(updatedSaleDetail.getWeight());
            }

            return saleDetailRepository.save(saleDetail);
        } else {
            throw new NoSuchElementException("Sale Detail not found with id: " + id);
        }
    }

    public List<SaleDetail> getAllPromotional(){
        return saleDetailRepository.findAllPromotional();
    }

    public List<SaleDetail> getAllContainingProduct(String productName){

        return saleDetailRepository.findSaleDetailByProduct(productName);
    }

    public void deleteById(Integer id) {
        if (!saleDetailRepository.existsById(id)) {
            throw new NoSuchElementException("Sale Detail not found with id: " + id);
        }
        saleDetailRepository.deleteById(id);
    }

}
