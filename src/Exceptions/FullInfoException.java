package Exceptions;

public class FullInfoException extends RuntimeException{
    private final String fromInvoice;
    private final String toInvoice;
    private final Integer transactionSum;
    protected FullInfoException(String fromInvoice, String toInvoice, Integer transactionSum) {
        this.fromInvoice = fromInvoice;
        this.toInvoice = toInvoice;
        this.transactionSum = transactionSum;
    }

    public String getFromInvoice() {
        return fromInvoice;
    }

    public String getToInvoice() {
        return toInvoice;
    }

    public Integer getTransactionSum() {
        return transactionSum;
    }
}
