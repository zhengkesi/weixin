package com.weixin.publics.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xiaodong
 * @Date: 2021/2/22 15:05
 */
public class StringUtil {
    public static void main(String[] args) {
        String classs = "7802\n" +
                "7830\n" +
                "7833\n" +
                "13256\n" +
                "17705\n" +
                "17708\n" +
                "17718\n" +
                "17719\n" +
                "22733\n" +
                "24897\n" +
                "25006\n" +
                "25088\n" +
                "26277\n" +
                "26294\n" +
                "28634\n" +
                "28681\n" +
                "29750\n" +
                "30502\n" +
                "30512\n" +
                "30522\n" +
                "30586\n" +
                "31933\n" +
                "33741\n" +
                "35674\n" +
                "35681\n" +
                "35682\n" +
                "36017\n" +
                "36029\n" +
                "36039\n" +
                "36040\n" +
                "36556\n" +
                "36628\n" +
                "36683\n" +
                "36686\n" +
                "36727\n" +
                "36737\n" +
                "37441\n" +
                "37444\n" +
                "37446\n" +
                "37465\n" +
                "37475\n" +
                "37477\n" +
                "37478\n" +
                "37491\n" +
                "37493\n" +
                "37494\n" +
                "38023\n" +
                "38419\n" +
                "38420\n" +
                "38425\n" +
                "38426\n" +
                "38427\n" +
                "38429\n" +
                "38430\n" +
                "38435\n" +
                "38436\n" +
                "38437\n" +
                "38439\n" +
                "38441\n" +
                "38443\n" +
                "38449\n" +
                "38451\n" +
                "38452\n" +
                "38453\n" +
                "38454\n" +
                "38455\n" +
                "38456\n" +
                "38459\n" +
                "38460\n" +
                "38461\n" +
                "38462\n" +
                "38464\n" +
                "38797\n" +
                "38808\n" +
                "38933\n" +
                "39025\n" +
                "39217\n" +
                "39219\n" +
                "39221\n" +
                "39228\n" +
                "39230\n" +
                "39234\n" +
                "39235\n" +
                "39236\n" +
                "39239\n" +
                "39240\n" +
                "39243\n" +
                "39245\n" +
                "39250\n" +
                "39254\n" +
                "39255\n" +
                "39310\n" +
                "39321\n" +
                "39336\n" +
                "39386\n" +
                "39447\n" +
                "39536\n" +
                "39537\n" +
                "39538\n" +
                "39543\n" +
                "39544\n" +
                "39547\n" +
                "39548\n" +
                "39549\n" +
                "39550\n" +
                "39552\n" +
                "39553\n" +
                "39554\n" +
                "39555\n" +
                "39558\n" +
                "39559\n" +
                "39560\n" +
                "39562\n" +
                "39563\n" +
                "39564\n" +
                "39565\n" +
                "39602\n" +
                "39604\n" +
                "39613\n" +
                "39617\n" +
                "39628\n" +
                "40030\n" +
                "40168\n" +
                "40190\n" +
                "40193\n" +
                "40195\n" +
                "40199\n" +
                "40200\n" +
                "40655\n" +
                "40849\n" +
                "40865\n" +
                "40894\n" +
                "40899\n" +
                "40975\n" +
                "40977\n" +
                "40980\n" +
                "41008\n" +
                "41015\n" +
                "41016\n" +
                "41021\n" +
                "41022\n" +
                "41023\n" +
                "41024\n" +
                "41025\n" +
                "41026\n" +
                "41027\n" +
                "41028\n" +
                "41029\n" +
                "41061\n" +
                "41066\n" +
                "41067\n" +
                "41088\n" +
                "41091\n" +
                "41319\n" +
                "41661\n" +
                "41891\n" +
                "41892\n" +
                "41895\n" +
                "41897\n" +
                "41906\n" +
                "41912\n" +
                "42145\n" +
                "43629\n" +
                "43633\n" +
                "43637\n" +
                "44251\n" +
                "44662\n" +
                "44722\n" +
                "44724\n" +
                "45555\n" +
                "45563\n" +
                "47822\n" +
                "47844\n" +
                "47862\n" +
                "47866\n" +
                "47871\n" +
                "47877\n" +
                "47882\n" +
                "47887\n" +
                "47891\n" +
                "48330\n" +
                "50375\n" +
                "52913\n" +
                "56374\n" +
                "57866\n" +
                "57872\n" +
                "59408\n" +
                "60313\n" +
                "60328\n" +
                "60329\n" +
                "60330\n" +
                "61651\n" +
                "61663\n" +
                "63609\n" +
                "63610\n" +
                "64796\n" +
                "64849\n" +
                "64856\n" +
                "64878\n" +
                "65020\n" +
                "65116\n" +
                "66717\n" +
                "68026\n" +
                "68029\n" +
                "68030\n" +
                "69423\n" +
                "69425\n" +
                "69426\n" +
                "69429\n" +
                "69434\n" +
                "69440\n" +
                "69447\n" +
                "70984\n" +
                "71861\n" +
                "71866\n" +
                "71867\n" +
                "71872\n" +
                "71881\n" +
                "71882\n" +
                "71890\n" +
                "71891\n" +
                "71892\n" +
                "71893\n" +
                "71894\n" +
                "71895\n" +
                "71900\n" +
                "71901\n" +
                "71903\n" +
                "71906\n" +
                "71907\n" +
                "71908\n" +
                "71913\n" +
                "72005\n" +
                "72049\n" +
                "72051\n" +
                "72339\n" +
                "73743\n" +
                "76136\n" +
                "76257\n" +
                "76260\n" +
                "76266\n" +
                "76275\n" +
                "76285\n" +
                "76290";
        String replace = classs.replace("\n", ",");
        String[] split = replace.split(",");
        List<String> list = Arrays.asList(split);

        System.out.println(list);
    }
}
