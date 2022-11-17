package org.example.email;

public class EmailManager {
    private final String ePattern = "^[^\\.][a-zA-Z0-9\"_.]+([^\\.]+)@(\\[*([a-zA-Z\\-0-9]+\\.)+[a-zA-Z0-9]{2,}\\]*)$";

    private final String prefixPattern = "^([^\\.][a-zA-Z0-9\"_.]+([^\\.]+))";
    private final String postfixPattern = "^(\\[?([0-9]{1,4}\\.){1,3}[0-9]{1,4}\\]?)|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z0-9]{2,})$";
    public boolean validateEmail(Email email){
        if(!email.email.contains("@")) return false;
        if(email.email.endsWith(".web")) return false;
        if(email.email.contains("@-")) return false;
        if(email.email.contains("..")) return false;
        String[] portions = email.email.split("@");
        if(portions.length != 2) return false;

        java.util.regex.Pattern p = java.util.regex.Pattern.compile(prefixPattern);
        java.util.regex.Matcher m = p.matcher(portions[0]);

        boolean isTrue;
        if(portions[1].split("\\.").length == 4){
            String ipRegex = "^(\\[?(\\d{1,4}\\.){3}(\\d{1,4})\\]?)$";
            java.util.regex.Pattern p3 = java.util.regex.Pattern.compile(ipRegex);
            java.util.regex.Matcher m3 = p3.matcher(portions[1]);
            isTrue = m3.matches();
        } else {
            java.util.regex.Pattern p2 = java.util.regex.Pattern.compile(postfixPattern);
            java.util.regex.Matcher m2 = p2.matcher(portions[1]);
            isTrue = m2.matches();
        }

        return m.matches() && isTrue ;

    }
}
