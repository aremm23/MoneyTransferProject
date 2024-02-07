package Exceptions;

/*
* InvalidFilePatternException is used to throw an exception when the file pattern is invalid.
* It is used in transaction() method in Transaction class and in GetInfoFromInvoices() - constructor of GetInfoFromInvoices class.
*/
public class InvalidFilePatternException extends FullInfoException {
    private String invalidString;
    private String fileName;
    boolean fullInfo;
    public InvalidFilePatternException(String invalidString, String fileName, String fromInvoice, String toInvoice, Integer transactionSum) {
        super(fromInvoice, toInvoice, transactionSum);
        this.invalidString = invalidString;
        this.fileName = fileName;
        this.fullInfo = true;
    }
    public InvalidFilePatternException(String invalidString, String fileName) {
        super(null, null, null);
        this.invalidString = invalidString;
        this.fileName = fileName;
        this.fullInfo = false;
    }

    public String toString() {
        if(fileName.isEmpty())
            return "Invalid string \"" + this.invalidString + "\" founded";
        else return "Invalid string \"" + this.invalidString + "\" founded in file " + this.fileName;
    }
}
