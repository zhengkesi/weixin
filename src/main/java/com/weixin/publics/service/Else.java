package com.weixin.publics.service;

import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @Author: xiaodong
 * @Date: 2020/9/24 15:10
 */
@Service
public class Else {

    //常量list
    private final static List list = Arrays.asList("合肥一中",
            "合肥168中学","合肥八中","安庆一中","蚌埠二中","淮北一中","淮南二中","太和一中","铜陵一中","滁州中学");
    //常量map
    private final static Map map = new HashMap();

    static {
        map.put("num",1);
        map.put("ss",2);
    }


    /**
     * 学校排行借阅
     * @return
     */
    public List<Map> selectBorrowingList(){
        List<Map> listMap = new ArrayList<>();
        for (int i=0;i<10;i++){
            Map map = new HashMap<>();
            map.put("num",randomNum());
            map.put("XXMC",list.get(i));
            listMap.add(map);
        }
        List<Map> comparator = comparator(listMap,"num");
        return comparator;
    }


    public List<Map> selectBorrowing(){
        List<Map> listMap = new ArrayList<>();
        for (int i=0;i<10;i++){
            Map map = new HashMap<>();
            map.put("total",randomNum());
            map.put("school_id","1000".concat(list.get(i).toString()));
            map.put("schoolname",list.get(i));

            listMap.add(map);
        }
        List<Map> comparator = comparator(listMap,"total");
        return comparator;
    }

    /**
     *
     * @param listMap 排序集合
     * @param com 排序的key值
     * @return
     */
    public static List<Map> comparator(List<Map> listMap,String com){
        //List<Map> 对map排序
        Collections.sort(listMap, new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                Integer date1 = (Integer) o1.get(com);
                Integer date2 = (Integer) o2.get(com);
                return date2.compareTo(date1);
            }
        });
        return listMap;
    }

    public static Integer randomNum(){
        Random random = new Random();
        //随机生成0-100  不包括100
        Integer i = random.nextInt(100);
        return i;
    }

}
