package com.telran.ilcarro.qa26.application;

import com.telran.ilcarro.qa26.model.User;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {


    //открытый датапровайдер
    @DataProvider
    public Iterator<Object[]> validLoginFromList() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"my.email1611782936999@mail.com","iL12345678"});
        list.add(new Object[]{"my.Email1608821574952@gmail.com", "iL12345678"});
        list.add(new Object[]{"my.email1608881337043@gmail.com", "Aa1234567"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFromValidLoginCsvFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(("src/test/resources/validLoginCsv.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new User().withEmail(split[0]).withPassword(split[1])});
            line = reader.readLine();
        }

        return list.iterator();
    }
    
}
