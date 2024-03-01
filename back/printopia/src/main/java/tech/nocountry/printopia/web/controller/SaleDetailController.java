package tech.nocountry.printopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.printopia.persistence.entity.SaleDetail;
import tech.nocountry.printopia.service.SaleDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/saledetails")
public class SaleDetailController {

    @Autowired
    SaleDetailService saleDetailService;

    @GetMapping("")
    public ResponseEntity<List<SaleDetail>> getSaleSetails(){

        List<SaleDetail> saleDetails = saleDetailService.findAll();

        return new ResponseEntity<>(saleDetails, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<SaleDetail> createSaleDetail(@RequestBody SaleDetail saleDetail){

        SaleDetail createdSaleDetail = saleDetailService.save(saleDetail);

        return new ResponseEntity<>(createdSaleDetail,HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SaleDetail> editSaleDetail(@PathVariable Integer id, @RequestBody SaleDetail saleDetail){

        SaleDetail updatedSaleDetail = saleDetailService.update(id,saleDetail);

        return new ResponseEntity<>(updatedSaleDetail,HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSaleDetail(@PathVariable Integer id){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/product/{productName}")
    public ResponseEntity<List<SaleDetail>> findSaleDetailContainingProduct(@PathVariable String productName){

        List<SaleDetail> saleDetails = saleDetailService.getAllContainingProduct(productName);

        return new ResponseEntity<>(saleDetails,HttpStatus.OK);
    }

    @GetMapping("/promotional")
    public ResponseEntity<List<SaleDetail>> getPromotional(){

        List<SaleDetail> promotionalSaleDetails = saleDetailService.getAllPromotional();

        return new ResponseEntity<>(promotionalSaleDetails, HttpStatus.OK);
    }

}
