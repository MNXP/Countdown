package com.xiangpan.countdown.Bean;

/**
 * xiangpan
 * 2018/3/21
 * 17600660418
 * x1992418@163.com
 */

public class TimeDownBean {
    private long useTime;
    private boolean timeFlag;
    private String content;
    public TimeDownBean(long useTime,String content){
        this.useTime = useTime;
        this.content = content;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public boolean isTimeFlag() {
        return timeFlag;
    }

    public void setTimeFlag(boolean timeFlag) {
        this.timeFlag = timeFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
