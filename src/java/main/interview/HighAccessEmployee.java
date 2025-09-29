package src.java.main.interview;

import java.util.*;

/**
 * Walmart Interview Question
 */
public class HighAccessEmployee {
    // Online Java Compiler
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        List<HashMap<String, List<String>>> allLogs = new ArrayList<>();
        HashMap<String, List<String>> day1Log = new HashMap<>();
        day1Log.put("A", Arrays.asList("0830", "0915", "0930", "0945"));
        day1Log.put("B", Arrays.asList("0845", "0955"));

        HashMap<String, List<String>> day2Log = new HashMap<>();
        day2Log.put("A", Arrays.asList("1015", "1118", "1234"));
        day2Log.put("B", Arrays.asList("1015", "1030", "1045"));

        allLogs.add(day1Log);
        allLogs.add(day2Log);
        System.out.println(getTop3Employee(allLogs));
    }

    //assuming timing will 4 numeric characters format always
    //assuming input as ["A" -> ["0830","0930"]]
    //timings of the day can be in any order
    static boolean isHighAccess(List<String> dayTimings) {
        List<Double> doubleList = new ArrayList<Double>(dayTimings.size());
        for (String hhmm : dayTimings) {
            doubleList.add(getTime(hhmm));
        }
        Collections.sort(doubleList);
        int times = 1;
        Double prev = doubleList.get(0);
        for (int i = 1; i < doubleList.size(); i++) {
            System.out.println((doubleList.get(i) - prev));
            if ((doubleList.get(i) - prev) <= 59)
                times++;
            prev = doubleList.get(i);
        }
        System.out.println(doubleList);
        return times >= 3;
    }

    static Double getTime(String hhmm) {
        int hour = ((hhmm.charAt(0) - '0') * 10) + (hhmm.charAt(1) - '0');
        int minute = ((hhmm.charAt(2) - '0') * 10) + (hhmm.charAt(3) - '0');
        return new Double(hour * 60 + (minute));
    }

    static List<String> getTop3EmployeeNewFormat(HashMap<String, List<HashMap<String, String>>> allLogs) {
        List<HashMap<String, List<String>>> dayLogs = new ArrayList<>();
       /* for(Map.Entry<String,List<HashMap<String,String>>> entry:allLogs.entrySet()) {
         //   List<HashMap<String,String>> oneDay = entry.getValue();
           // HashMap<String,List<String>> employeeToAccessLogs = new HashMap<>();
         //   for()
        }*/
        return null;
    }

    static List<String> getTop3Employee(List<HashMap<String, List<String>>> dayLogs) {
        List<String> highAccessEmployees = new ArrayList<String>();
        for (HashMap<String, List<String>> dayLog : dayLogs) {
            for (Map.Entry<String, List<String>> entry : dayLog.entrySet()) {
                if (isHighAccess(entry.getValue()) && !highAccessEmployees.contains(entry.getKey())) {
                    highAccessEmployees.add(entry.getKey());
                }
            }
        }
        //todo keep only 3 employyes
        return highAccessEmployees;
    }
}
