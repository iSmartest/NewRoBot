package com.furuiintelligence.newrobot.utils;


//                    _ooOoo_
//                   o8888888o
//                   88" . "88
//                   (| -_- |)
//                   O\  =  /O
//                ____/`---'\____
//              .'  \\|     |//  `.
//             /  \\|||  :  |||//  \
//            /  _||||| -:- |||||-  \
//            |   | \\\  -  /// |   |
//            | \_|  ''\---/''  |   |
//            \  .-\__  `-`  ___/-. /
//          ___`. .'  /--.--\  `. . ___
//       ."" '<  `.___\_<|>_/___.'  >' "".
//      | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//      \  \ `-.   \_ __\ /__ _/   .-` /  /
// ======`-.____`-.___\_____/___.-`____.-'======
//                    `=---='
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//          佛祖保佑     永不宕机      永无BUG
//                      大姨夫 2018/2/7
//                站在顶峰,看世界
//                跌落谷底,思人生

import java.util.HashMap;
import java.util.Map;

/**
 * 信用代码验证
 */
public class CreditCodeUtils {


    public static boolean isValid(String businessCode) {
        if ((businessCode.equals("")) || businessCode.length() != 18) {
            return false;
        }
        String baseCode = "0123456789ABCDEFGHJKLMNPQRTUWXY";
        char[] baseCodeArray = baseCode.toCharArray();
        Map<Character, Integer> codes = new HashMap<>();
        for (int i = 0; i < baseCode.length(); i++) {
            codes.put(baseCodeArray[i], i);
        }
        char[] businessCodeArray = businessCode.toCharArray();
        Character check = businessCodeArray[17];
        if (baseCode.indexOf(check) == -1) {
            return false;
        }
        int[] wi = { 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28 };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            Character key = businessCodeArray[i];
            if (baseCode.indexOf(key) == -1) {
                return false;
            }
            sum += (codes.get(key) * wi[i]);
        }
        int value = 31 - sum % 31;
        return value == codes.get(check);
    }
}
