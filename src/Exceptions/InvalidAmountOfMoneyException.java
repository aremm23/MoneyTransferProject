package Exceptions;

import java.io.File;

/*
* InvalidAmountOfMoneyException is used to throw an exception when the amount of money is not enough to perform the transaction.
* It is used in transaction() method in Transaction class and in GetInfoFromInput() - constructor of GetInfoFromInput class.
*/
public class InvalidAmountOfMoneyException extends FullInfoException{
    private String fileName = "";
    private Integer amountOfMoneySum;
    public InvalidAmountOfMoneyException(Integer amountOfMoneySum, File fileName, String fromInvoice, String toInvoice, Integer transactionSum) {
        super(fromInvoice, toInvoice, transactionSum);
        this.amountOfMoneySum = amountOfMoneySum;
        this.fileName = fileName.getName();
    }
    public InvalidAmountOfMoneyException(Integer amountOfMoneySum, String fromInvoice, String toInvoice, Integer transactionSum) {
        super(fromInvoice, toInvoice, transactionSum);
        this.amountOfMoneySum = amountOfMoneySum;
    }
    public String toString() {
        if(fileName.isEmpty())
            return "There are less money on invoice then transaction sum (" + amountOfMoneySum + ")";
        else return "Invalid amount of money (" + amountOfMoneySum + ") in file " + fileName;
    }
}
