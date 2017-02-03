package com.tinyhabits.razorthink.tinyhabits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak Detni on 31-01-2017.
 */

public class GlobalData {

    private static GlobalData instance;

    public static GlobalData getInstance(){
        if(instance == null)
            return instance = new GlobalData();
        else
            return instance;
    }

    public List<EmployeesDetails> employeesDetailsList = new ArrayList<>();
}
