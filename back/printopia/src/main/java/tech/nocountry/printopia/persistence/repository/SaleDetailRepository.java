package tech.nocountry.printopia.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.nocountry.printopia.persistence.entity.SaleDetail;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {

    @Query("SELECT sd FROM SaleDetail sd "
            + "WHERE sd.wasPromotional = true")
    public List<SaleDetail> findAllPromotional();

    @Query("SELECT sd FROM SaleDetail sd "
            + "WHERE product.name = :product")
    public List<SaleDetail> findSaleDetailByProduct(@Param("product") String productName);
}
