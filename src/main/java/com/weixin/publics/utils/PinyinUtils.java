package com.weixin.publics.utils;

/**
 * @Author: xiaodong
 * @Date: 2020/12/14 14:38
 */
import com.alibaba.fastjson.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class PinyinUtils {
    /**
     * 将汉字转换为全拼
     *
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    // 将汉字的几种全拼都存到t2数组中
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    // 取出该汉字全拼的第一种读音并连接到字符串t4后
                    t4 += t2[0];
                } else {
                    // 如果不是汉字字符，直接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return t4;
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    public static String getChineseByPinYin(String src) {
        char[] englishChars = src.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < englishChars.length; i++)
        {
            String[] pinYin;
            try {
                pinYin = PinyinHelper.toHanyuPinyinStringArray(englishChars[i], getDefaultOutputFormat());
                if (pinYin != null)
                {
                    sb.append(pinYin[0]);
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 没有音调数字
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // lv显示
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        return format;
    }

    /*public static void main(String [] args) {
        String pinyin = getPinYin("北京");
        String pinyinhead = getPinYinHeadChar("北京");
        System.out.println(pinyin + " ; " + pinyinhead);
    }*/

    /*public static void main(String[] args) throws IOException {
        String text="淮北校";
        StringReader sr=new StringReader(text);
        IKSegmenter ik=new IKSegmenter(sr, true);
        Set<String> list = new HashSet<>();
        Lexeme lex=null;
        while((lex=ik.next())!=null){
            System.out.print(lex.getLexemeText()+"，");
            list.add(lex.getLexemeText());
        }
        System.out.println(list);
    }*/

    public static void main(String[] args) {
        /*{
            "bJ": "高一艺术班",
                "city": "合肥市",
                "gradeName": "小学一年级",
                "header": "",
                "id": 58,
                "openId": "oF5LH5NQ8OOVlpr9A-B0qMzxHVrk",
                "phone": "18855321347",
                "schId": 100075,
                "stuId": "020b15f905c441a5ae2f48cef3b32749",
                "stuName": "德莱厄斯",
                "unionId": "oyRXCt5RY5tumL4ScLYh6vIRcKz8",
                "xXMC": "皖新中学"
        }*/
        Map map = new HashMap();
        map.put("BJ","高一艺术班");
        map.put("City","合肥市");

        String string = JSONObject.toJSONString(map);

        System.out.println(string);
    }
}

