package com.banking.shared.sharedData;

public class CustomerData {

    private static String name;
    private static String nic;


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CustomerData.name = name;
    }

    public static String getNic() {
        return nic;
    }

    public static void setNic(String nic) {
        CustomerData.nic = nic;
    }
}
