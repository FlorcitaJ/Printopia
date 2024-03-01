package tech.nocountry.printopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.printopia.persistence.entity.ConsolidatedSale;
import tech.nocountry.printopia.service.ConsolidatedSaleService;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/consolidatedsales")
@RestController
public class ConsolidatedSaleController {

    @Autowired
    ConsolidatedSaleService consolidatedSaleService;

    @GetMapping("")
    public ResponseEntity<List<ConsolidatedSale>> getConsolidatedSales(){

        List<ConsolidatedSale> consolidatedSales = consolidatedSaleService.findAll();

        return new ResponseEntity<>(consolidatedSales, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ConsolidatedSale> createConsolidatedSale(@RequestBody ConsolidatedSale consolidatedSale){

        ConsolidatedSale createdConsolidatedSale = consolidatedSaleService.save(consolidatedSale);

        return new ResponseEntity<>(createdConsolidatedSale,HttpStatus.CREATED);
    }


    @GetMapping("/user/{email}")
    public ResponseEntity<List<ConsolidatedSale>> getConsolidatedSalesByUser(@PathVariable String email) {

        List<ConsolidatedSale> consolidatedSales = consolidatedSaleService.getAllByUserEmail(email);

        return new ResponseEntity<>(consolidatedSales,HttpStatus.OK);
    }

    @GetMapping("/date/{stringDate}")
    public ResponseEntity<List<ConsolidatedSale>> getConsolidatedSalesByDate(@PathVariable String stringDate) {

        LocalDate date = LocalDate.parse(stringDate);

        List<ConsolidatedSale> consolidatedSales = consolidatedSaleService.getAllByDate(date);

        return new ResponseEntity<>(consolidatedSales,HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ConsolidatedSale> updateConsolidatedSale(@PathVariable Integer id, @RequestBody ConsolidatedSale consolidatedSale){

        ConsolidatedSale updatedConsolidatedSale = consolidatedSaleService.update(id,consolidatedSale);

        return new ResponseEntity<>(updatedConsolidatedSale,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConsolidatedSale(@RequestParam Integer id){

        consolidatedSaleService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
