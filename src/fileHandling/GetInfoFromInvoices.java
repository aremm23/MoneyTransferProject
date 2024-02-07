package fileHandling;

import Exceptions.InvalidFilePatternException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/*
* GetInfoFromInvoices class is used to get information from invoices.txt file
* It has one field: hashMapWithInvoices
* It has one method: getHashMapWithInvoices
*/
public class GetInfoFromInvoices {
    private HashMap<String, Integer> hashMapWithInvoices = new HashMap<>();//hashMapWithInvoices is used to store invoices from invoices.txt file
    //GetInfoFromInvoices() method is used to get information from invoices.txt file
    public GetInfoFromInvoices(File invoicesFile) throws InvalidFilePatternException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(invoicesFile))) {
            String stringFromFile;
            while((stringFromFile = bufferedReader.readLine()) != null) {//read line by line
                if(!stringFromFile.matches("\\d{5}-\\d{5}_\\d+")){
                    throw new InvalidFilePatternException(stringFromFile, invoicesFile.getName());//if the pattern is invalid
                }
                int indexOf_ = stringFromFile.indexOf('_');
                //stringFromFile: XXXXX-XXXXX_yyyyyyyy
                //hashMapWithInvoices: XXXXX-XXXXX   ->    yyyyyyy
                this.hashMapWithInvoices.put(stringFromFile.substring(0, indexOf_), Integer.parseInt(stringFromFile.substring(indexOf_ + 1)));
            }
        }
        catch (IOException e){//if there is no such file
            System.out.println(e.getMessage());
        }
    }
    public HashMap<String, Integer> getHashMapWithInvoices() {
        return hashMapWithInvoices;
    }
}
