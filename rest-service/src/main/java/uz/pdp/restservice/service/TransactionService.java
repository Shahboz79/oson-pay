package uz.pdp.restservice.service;

import admin.response.TransactionResponseDto;
import org.springframework.stereotype.Service;
import uz.pdp.restservice.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

//    public List<TransactionResponseDto> getList(int page, TransactionResponseDto dto) {
//        String name = "%" + (dto.getTransactionStatus() != null ? dto.getTransactionStatus() : "") + "%";
//        String agentId = "%" + (dto.getA != null ? dto.getMinSum().toString().substring(0, dto.getMinSum().toString().length()-2) : "") + "%";
//        String merchantId = "%" + (dto.getMaxSum() != null ? dto.getMaxSum().toString().substring(0, dto.getMinSum().toString().length()-2) : "") + "%";
//
//        return transactionRepository.getAllSearchResult(name, agentId, merchantId, page, 11).stream().map((transaction) -> new TransactionResponseDto(
//                transaction.getId(),
//                transaction.getMerchantEntity().getName(),
//                transaction.getMerchantEntity().getGatewayMerchantEntity().getName(),
//                transaction.getMerchantEntity().getGatewayMerchantEntity().getGatewayEntity().getName(),
//                transaction.getAgentMerchantEntity().getAgentEntity().getName(),
//                transaction.getAgentMerchantEntity().getName(),
//                transaction.getTransactionAmount(),
//                transaction.getTransactionGatewayAmount(),
//                transaction.getState().name(),
//                transaction.getCreatedDate(),
//                transaction.getUpdatedDate()
//        )).toList();
//    }

    public List<TransactionResponseDto> getList(int page) {
//        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
//        return transactionRepository.findAll(pageable).stream().map((transaction) -> new TransactionResponseDto(
        return transactionRepository.getAllResult(page, 11).stream().map((transaction) -> new TransactionResponseDto(
                transaction.getId(),
                transaction.getMerchantEntity().getName(),
                transaction.getMerchantEntity().getGatewayMerchantEntity().getName(),
                transaction.getMerchantEntity().getGatewayMerchantEntity().getGatewayEntity().getName(),
                transaction.getAgentMerchantEntity().getAgentEntity().getName(),
                transaction.getAgentMerchantEntity().getName(),
                transaction.getTransactionAmount(),
                transaction.getTransactionGatewayAmount(),
                transaction.getState().name(),
                transaction.getCreatedDate(),
                transaction.getUpdatedDate()
        )).toList();
    }
}
