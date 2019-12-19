package matchTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lijian
 * @description 正则表达式匹配
 * @date 2019/12/11
 */
public class MatchTest {
    public static void main(String[] args) {
//        String scoreScope = "60<=m1<70";
//        boolean flag = Pattern.matches("\\W[a-z|A-Z][0-9]?\\W",scoreScope);
//        System.out.println(flag);   /\W[a-z|A-Z][0-9]?\W/
//        System.out.println(scoreScope.matches("\\W[a-z|A-Z][0-9]?\\W"));


        String str = "60<=m1<70";

        str.replace("<","");
        System.out.println(str.contains("<"));
//        String pattern = "\\d";
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        System.out.println(m.matches());
    }

    public static boolean matches(String bean){
        Pattern pattern = Pattern.compile("\\d{2}");
        Matcher matcher = pattern.matcher(bean);
        System.out.println(bean);
         if(matcher.find()){
             Integer min = Integer.valueOf(bean.substring(0, bean.indexOf("<")));
             Integer max = Integer.valueOf(bean.substring(bean.lastIndexOf("<") + 1));
                return true;
         }
         return false;
    }
}
