package com.zhulf.myandroidpractise;


import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    public static List<DataBean> dataEngender() {
        List<DataBean> traceList = new ArrayList<>();

        traceList.add(new DataBean("2016-05-25 17:48:00", "[沈阳市] [沈阳和平五部]的派件已签收 感谢使用中通快递,期待再次为您服务!"));
        traceList.add(new DataBean("2016-05-25 13:01:04", "[沈阳市] 快件到达 [沈阳和平五部]"));
        traceList.add(new DataBean("2016-05-25 12:19:47", "[沈阳市] 快件离开 [沈阳中转]已发往[沈阳和平五部]"));
        traceList.add(new DataBean("2016-05-25 11:12:44", "[沈阳市] 快件到达 [沈阳中转]"));
        traceList.add(new DataBean("2016-05-24 03:12:12", "[嘉兴市] 快件离开 [杭州中转部]已发往[沈阳中转]"));
        traceList.add(new DataBean("2016-05-23 21:06:46", "[杭州市] 快件到达 [杭州汽运部]"));
        traceList.add(new DataBean("2016-05-23 18:59:41", "[杭州市] 快件离开 [杭州乔司区]已发往[沈阳]"));
        traceList.add(new DataBean("2016-05-23 18:35:32", "[杭州市] [杭州乔司区]的市场部已收件 电话:18358xxxxxx"));

        return traceList;
    }
}
