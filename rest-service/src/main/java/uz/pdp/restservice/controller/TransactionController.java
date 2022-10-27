package uz.pdp.restservice.controller;


import admin.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.restservice.service.TransactionService;

import static uz.pdp.restservice.service.base.ResponseMessage.SUCCESS;

@RequestMapping("/api/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/list/{page}")
    public ApiResponse getTransactionList(@PathVariable int page) {
        return new ApiResponse<>(0, SUCCESS, transactionService.getList(page));
    }
}
