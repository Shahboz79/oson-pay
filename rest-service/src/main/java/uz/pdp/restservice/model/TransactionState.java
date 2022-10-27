package uz.pdp.restservice.model;

public enum TransactionState {

    CREATED,
    CHECKED,
    CHECK_ERROR,
    PAYING,
    SUCCESS,
    PAY_ERROR,
    IN_PROCESS
}
