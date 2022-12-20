package by.zastr.service.exception;

public enum ExceptionCode {
    PRODUCT_NOT_FOUND(40401),
    DISCOUNT_CARD_NOT_FOUND(40402),
    RECEIPT_NOT_FOUND(40403);

    private final int errorCode;

    ExceptionCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
