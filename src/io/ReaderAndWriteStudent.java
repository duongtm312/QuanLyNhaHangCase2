package io;

import models.Account;

import java.io.*;
import java.util.ArrayList;

public class ReaderAndWriteStudent {
    public static void Write(ArrayList<Account> accounts, String link) {
        try {
            File file = new File(link);
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Account ac : accounts
            ) {
                bufferedWriter.write(ac.write());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<Account> reader(String link) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {

            File file = new File(link);
            FileReader fileReader = null;
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                String[] arr = str.split(",");
                accounts.add(new Account(arr[0], arr[1]));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

}
