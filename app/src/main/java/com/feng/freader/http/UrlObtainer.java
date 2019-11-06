package com.feng.freader.http;

/**
 * 获取相应的 url
 *
 * @author Feng Zhaohao
 * Created on 2019/11/6
 */
public class UrlObtainer {

    /**
     * 获取排行榜小说
     *
     * @param id 排行榜的 id
     * @return
     */
    public static String getRankNovels(String id) {
        return "http://api.zhuishushenqi.com/ranking/" + id;
    }
}