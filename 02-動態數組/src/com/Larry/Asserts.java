package com.Larry;

public class Asserts {
    public static void test(boolean value){
        try{
            if(!value) throw new Exception("測試未通過");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
