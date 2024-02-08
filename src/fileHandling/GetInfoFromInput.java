package fileHandling;

import Exceptions.InvalidAmountOfMoneyException;
import Exceptions.InvalidFilePatternException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
* GetInfoFromInput class is used to get information from input file
* It has three fields: invoiceFrom, invoiceTo, amountOfMoney
* It has three methods: getInvoiceFrom, getInvoiceTo, getAmountOfMoney
*/
public class GetInfoFromInput {
    private String invoiceFrom = "";//invoiceFrom is used to store the invoice from which the transaction is made
    private String invoiceTo = "";//invoiceTo is used to store the invoice to which the transaction is made
    private Integer amountOfMoney;//amountOfMoney is used to store the amount of money of the transaction
    public GetInfoFromInput(File getFromFile) throws InvalidFilePatternException, InvalidAmountOfMoneyException {//GetInfoFromInput() method is used to get information from input file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFromFile))) {
            int divideSymbol = '_';//XXXXX-XXXXX_XXXXX-XXXXX_YYYYYYY_
            int symbol;           //            |           |       |
            StringBuilder stringFromFile = new StringBuilder();//stringFromFile is used to store the information from input file
            while ((symbol = bufferedReader.read()) != -1) {
                if ((char)symbol != (char)divideSymbol) {
                    stringFromFile.append((char) symbol);
                }
                else {
                    if(invoiceFrom.isEmpty()) {
                        this.invoiceFrom = stringFromFile.toString();
                        if(!this.invoiceFrom.matches("\\d{5}-\\d{5}")){
                            throw new InvalidFilePatternException(invoiceFrom, getFromFile.getName(), null, null, null);
                        }
                        stringFromFile.delete(0, stringFromFile.length());//clear stringFromFile
                    }
                    else if (invoiceTo.isEmpty()){
                        this.invoiceTo = stringFromFile.toString();
                        if (!this.invoiceTo.matches("\\d{5}-\\d{5}")){
                            throw new InvalidFilePatternException(invoiceFrom, getFromFile.getName(), this.invoiceFrom, null, null);
                        }
                        stringFromFile.delete(0, stringFromFile.length());//clear stringFromFile
                    }
                    else {
                        if(!stringFromFile.toString().matches("-?\\d+")){
                            throw new InvalidFilePatternException(invoiceFrom, getFromFile.getName(), this.invoiceFrom, this.invoiceTo, null);
                        }
                        this.amountOfMoney = Integer.parseInt(stringFromFile.toString());
                        if (this.amountOfMoney <= 0 || this.amountOfMoney > 1000000) {//if the amount of money is not in the range from 1 to 1000000
                            throw new InvalidAmountOfMoneyException(this.amountOfMoney, getFromFile, this.invoiceFrom, this.invoiceTo, this.amountOfMoney);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if(this.amountOfMoney == null) {//if the amount of money is not given, or given incorrectly, ex: 123-123_123-123_YYY(without '_' in the end)
            throw new InvalidAmountOfMoneyException(null, getFromFile, this.invoiceFrom, this.invoiceTo, null);
        }
    }
    public String getInvoiceFrom() {
        return invoiceFrom;
    }
    public String getInvoiceTo() {
        return invoiceTo;
    }
    public Integer getAmountOfMoney() {
        return amountOfMoney;
    }
}
