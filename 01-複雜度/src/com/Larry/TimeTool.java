package com.Larry;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTool {
    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss:SSS");

    public  interface   Task{
        void  execute();
    }

    public static void check(String title, Task task){
        if(task == null) return;
        title =(title==null)?"":("【"+title+"】");
        System.out.println(title);
        System.out.println("開始"+fmt.format(new Date()));
        long begin =System.currentTimeMillis();
        task.execute();
        long end =System.currentTimeMillis();
        System.out.println("結束"+fmt.format(new Date()));
        double delta =(end-begin)/1000.0;
        System.out.println("耗時"+delta+"秒");
        System.out.println("----------------");
    }
}
