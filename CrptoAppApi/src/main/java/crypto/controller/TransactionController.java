package crypto.controller;

import crypto.entity.Transaction;
import crypto.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController extends ControllerBase{

    @Autowired
    CryptoService service;

    @GetMapping("/{portfolioId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsForPortfolio(@PathVariable int portfolioId) {
        List<Transaction> transactions = service.getTransactionByPortfolioId(portfolioId);

        if(transactions == null) {
            return new ResponseEntity("Portfolio not found.", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/{portfolioId}/newtransaction")
    public ResponseEntity<Transaction> addTransaction(@PathVariable int portfolioId, @RequestBody Transaction transaction) {
        return null;
    }
}
