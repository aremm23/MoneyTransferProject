package fileHandling;

import Exceptions.FullInfoException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class PutInfoToReport {
    public static void putInfoToReport(String fileName, RuntimeException exception, String invoiceFrom, String invoiceTo, Integer transactionSum) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/report.txt", true))) {
            LocalDateTime currentDate = LocalDateTime.now();
            String putToReport = currentDate.toString() + " | " + fileName + " | from " + invoiceFrom + "  to " + invoiceTo + " | " + transactionSum + " | ";
            if(exception != null) bufferedWriter.write(putToReport + exception.toString() + " |\n");
            else bufferedWriter.write(putToReport + " Ended successfully\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void putInfoToReport(String fileName, FullInfoException exception) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/report.txt", true))) {
            LocalDateTime currentDate = LocalDateTime.now();
            String putToReport = currentDate.toString() + " | " + fileName + " | from " + exception.getFromInvoice() + "  to " + exception.getToInvoice() + " | " + exception.getTransactionSum() + " | ";
            bufferedWriter.write(putToReport + exception.toString() + " |\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
