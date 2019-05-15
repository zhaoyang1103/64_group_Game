package com.mad.trafficclient.zy_java;

import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;

<<<<<<< Updated upstream
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
=======
import java.util.Calendar;
>>>>>>> Stashed changes
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
<<<<<<< Updated upstream
        String time = "2019-06-01 03:23:20";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(time);
            System.out.println(parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

=======
        Calendar calendar= Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_WEEK);

//        calendar.setTimeInMillis();
        System.out.println(i);
>>>>>>> Stashed changes
    }
}
