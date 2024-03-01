package tech.nocountry.printopia.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.nocountry.printopia.persistence.entity.ConsolidatedSale;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsolidatedSaleRepository extends JpaRepository<ConsolidatedSale,Integer> {



    @Query("SELECT cs FROM ConsolidatedSale cs "
            + "WHERE cs.user.email = :email")
    public List<ConsolidatedSale> findSaleByEmail(@Param("email") String email);


    @Query("SELECT cs FROM ConsolidatedSale cs "
            + "WHERE cs.saleDate = :date")
    public List<ConsolidatedSale> findSaleByDate(@Param("date") LocalDate date);

}
