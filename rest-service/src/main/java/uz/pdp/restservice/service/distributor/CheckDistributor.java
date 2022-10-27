package uz.pdp.restservice.service.distributor;

import org.springframework.stereotype.Service;
import uz.pdp.restservice.model.*;
import uz.pdp.restservice.model.receive.DefaultCheckReceiveDto;
import uz.pdp.restservice.model.response.BaseCheckAgentResponse;
import uz.pdp.restservice.repository.*;

import java.math.BigDecimal;

@Service
public class CheckDistributor {

    private final MerchantRepository merchantRepository;
    private final TransactionRepository transactionRepository;
    private final GatewayMerchantRepository gatewayMerchantRepository;
    private final GatewayRepository gatewayRepository;
    private final AgentMerchantRepository agentMerchantRepository;

    public CheckDistributor(MerchantRepository merchantRepository, TransactionRepository transactionRepository, GatewayMerchantRepository gatewayMerchantRepository, GatewayRepository gatewayRepository, AgentMerchantRepository agentMerchantRepository) {
        this.merchantRepository = merchantRepository;
        this.transactionRepository = transactionRepository;
        this.gatewayMerchantRepository = gatewayMerchantRepository;
        this.gatewayRepository = gatewayRepository;
        this.agentMerchantRepository = agentMerchantRepository;
    }

    public BaseCheckAgentResponse check(
            AgentEntity agentEntity,
            DefaultCheckReceiveDto defaultCheckReceiveDto
    ) {
        TransactionError transactionError = null;
        MerchantEntity merchantEntity = null;
        GatewayMerchantEntity gatewayMerchantEntity = null;
        AgentMerchantEntity agentMerchantEntity = null;
        GatewayEntity gatewayEntity = null;
        TransactionEntity transactionEntity = null;


        if (!defaultCheckReceiveDto.isFull()) {
            transactionError = TransactionError.PARAMETERS_INVALID;
        }

        if (transactionError == null) {
            merchantEntity = merchantRepository.findById(defaultCheckReceiveDto.getMerchantId()).get();

            transactionEntity = new TransactionEntity(TransactionState.CREATED);
            transactionEntity.setTransactionAmount(defaultCheckReceiveDto.getAmount());
            transactionEntity.setMerchantEntity(merchantEntity);
            transactionRepository.save(transactionEntity);

            if (merchantEntity == null) {
                transactionError = TransactionError.MERCHANT_NOT_FOUND;
            }

            if (transactionError == null) {
                gatewayMerchantEntity = merchantEntity.getGatewayMerchantEntity();
                agentMerchantEntity = agentMerchantRepository.findByMerchantEntity_IdAndAgentEntity_Id(merchantEntity.getId(), agentEntity.getId()).get();
                gatewayEntity = gatewayMerchantEntity.getGatewayEntity();

                if (defaultCheckReceiveDto.getAmount().compareTo(BigDecimal.valueOf(agentMerchantEntity.getMaxSum())) > 0) {
                    transactionError = TransactionError.MAX_AMOUNT;
                }

                if (defaultCheckReceiveDto.getAmount().compareTo(BigDecimal.valueOf(agentMerchantEntity.getMinSum())) < 0) {
                    transactionError = TransactionError.MIN_AMOUNT;
                }

                if (gatewayEntity.isPaynet()) {
                    if (getRandomNumber() < 5) {
                        transactionEntity.setState(TransactionState.CHECK_ERROR);
                    } else {
                        transactionEntity.setState(TransactionState.CHECKED);
                    }
                }

                if (gatewayEntity.isPayme()) {
                    if (getRandomNumber() < 5) {
                        transactionEntity.setState(TransactionState.CHECK_ERROR);
                    } else {
                        transactionEntity.setState(TransactionState.CHECKED);
                    }
                }
            }
            transactionEntity.setAgentMerchantEntity(agentMerchantEntity);
            transactionRepository.save(transactionEntity);
        }

        return null;
    }

    private int getRandomNumber() {
        return (int) (Math.random() * 10);
    }
}
