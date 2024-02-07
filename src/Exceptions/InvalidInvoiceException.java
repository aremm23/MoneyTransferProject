package Exceptions;

/*
* InvalidInvoiceException is used to throw an exception when there is no such invoice.
* It is used in transaction() method in Transaction class.
*/
public class InvalidInvoiceException extends RuntimeException{
    private String invalidInvoice;
    private String fileName;
    public InvalidInvoiceException(String invalidInvoice, String fileName) {
        this.invalidInvoice = invalidInvoice;
        this.fileName = fileName;
    }
    public String toString() {
        return "Invalid invoice \"" + this.invalidInvoice + "\" founded in file " + this.fileName;
    }
}
