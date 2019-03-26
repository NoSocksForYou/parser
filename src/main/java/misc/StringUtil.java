package misc;

import java.util.regex.Pattern;

public class StringUtil {
    private final static Pattern pattern = Pattern.compile("^(.+?)\\1+$");

    public static int getCurrentCommaPlace(String string) {
        int commaplace = string.indexOf(".");
        if (commaplace == -1) {
            commaplace = string.indexOf(",");
        }

        if (commaplace == -1) {
            return string.length();
        } else {
            return commaplace;
        }
    }


    public static Pair<String, String> findPattern(String s) {
        s = s.replaceAll("([.,])", "");

        for (int i = 0; i < s.length(); i++) {
            if (pattern.matcher(s.substring(i)).matches()) {
                return new Pair<>(s.substring(0, i), s.substring(i).replaceAll("^(.+?)\\1+$", "$1"));
            }
        }
        return new Pair<>(s, "");
    }
    private static String moveComma (String number, final int amount, final int commaplace) {
        int newcommaplace = commaplace + amount;


        if (newcommaplace > number.length() - 1) {
            return moveComma(number + "0", amount, commaplace);
        }
        if (newcommaplace < 1) {
            return moveComma("0" + number, amount);
        }

        String temp = number.replaceAll("[.,]", "");

        if (Pattern.compile("0*").matcher(temp.substring(newcommaplace)).matches()) {
            return temp.substring(0, newcommaplace);
        } else {
            return temp.substring(0, newcommaplace) + "." + temp.substring(newcommaplace);
        }

//        if (toReturn.indexOf(',') == toReturn.length() - 1) {
//            return toReturn
//        }
    }

    public static String moveComma(String number, int amount) {
        return moveComma(number, amount, getCurrentCommaPlace(number));
    }

    public static void main(String[] args) {
        System.out.println(moveComma("1.23", 1));
    }
}