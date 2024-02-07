package transaction;

import Exceptions.InvalidAmountOfMoneyException;
import Exceptions.InvalidFilePatternException;
import Exceptions.InvalidInvoiceException;
import fileHandling.GetInfoFromInput;
import fileHandling.GetInfoFromInvoices;
import fileHandling.PutInfoToReport;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transaction {
    /*
    * transaction() method is used to perform transactions between invoices.
    * It reads invoices from invoices.txt file and transactions from input directory.
    * It checks if the invoices are valid and if the amount of money is enough to perform the transaction.
    * If the transaction is valid, it updates the invoices in the invoices.txt file.
    * transaction() calls such methods as GetInfoFromInvoices and GetInfoFromInput.
    */
    public void transaction() {
        File inputDir = new File("src/input");
        File archiveDir = new File("src/archive");
        File invoicesFile = new File("src/invoices.txt");
        GetInfoFromInvoices infoFromInvoices = new GetInfoFromInvoices(invoicesFile);
        HashMap<String, Integer> hashMapWithInvoices = infoFromInvoices.getHashMapWithInvoices();//hashMapWithInvoices is used to store invoices from invoices.txt file
        File[] arrayWithFilesFromInput = inputDir.listFiles();
        List<File> listWithFilesFromInput = null;

        if(arrayWithFilesFromInput != null) {//if there are any files in input directory
            listWithFilesFromInput = new ArrayList<>(List.of(arrayWithFilesFromInput));//add files to list
            String invoiceFrom, invoiceTo;
            Integer transactionSum;
            for (File transactionFile:listWithFilesFromInput) {
                GetInfoFromInput infoFromInput = null; //infoFromInput is used to get information from transaction file
                try {
                        infoFromInput = new GetInfoFromInput(transactionFile);
                        invoiceFrom = infoFromInput.getInvoiceFrom();
                        invoiceTo = infoFromInput.getInvoiceTo();
                        transactionSum = infoFromInput.getAmountOfMoney();
                        Integer amountOfMoney = hashMapWithInvoices.get(invoiceFrom);
                        if (amountOfMoney < transactionSum) {
                            throw new InvalidAmountOfMoneyException(transactionSum, invoiceFrom, invoiceTo, transactionSum);
                        }
                        if (!hashMapWithInvoices.containsKey(invoiceFrom))
                            throw new InvalidInvoiceException(invoiceFrom, transactionFile.getName());
                        if (!hashMapWithInvoices.containsKey(invoiceTo))
                            throw new InvalidInvoiceException(invoiceTo, transactionFile.getName());
                        try {
                            hashMapWithInvoices.replace(invoiceFrom, amountOfMoney - transactionSum);
                            hashMapWithInvoices.replace(invoiceTo, hashMapWithInvoices.get(invoiceTo) + transactionSum);
                        } catch (NullPointerException e) {
                            System.out.println(e.getMessage());
                            PutInfoToReport.putInfoToReport(transactionFile.getName(), e, invoiceFrom, invoiceTo, transactionSum);
                        }
                        PutInfoToReport.putInfoToReport(transactionFile.getName(), null, invoiceFrom, invoiceTo, transactionSum);//put information about the transaction to report.txt file
                    } catch (InvalidFilePatternException e) { //if the transaction file has invalid pattern
                        System.out.println(e.toString());
                        PutInfoToReport.putInfoToReport(transactionFile.getName(), e);
                    } catch (InvalidAmountOfMoneyException e) {//if the amount of money is not enough to perform the transaction
                        System.out.println(e.toString());
                    PutInfoToReport.putInfoToReport(transactionFile.getName(), e);
                    } catch (InvalidInvoiceException e) { //if there is no such invoice
                        System.out.println(e.toString());
                        PutInfoToReport.putInfoToReport(transactionFile.getName(), e, infoFromInput.getInvoiceFrom(), infoFromInput.getInvoiceTo(), infoFromInput.getAmountOfMoney());
                    }
                }
            System.out.println(hashMapWithInvoices);
        }
    }
}
