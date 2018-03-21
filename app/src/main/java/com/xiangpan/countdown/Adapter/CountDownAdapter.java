package com.xiangpan.countdown.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiangpan.countdown.Bean.TimeDownBean;
import com.xiangpan.countdown.R;

import java.text.ParseException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * xiangpan
 * 2018/3/21
 * 17600660418
 * x1992418@163.com
 */

public class CountDownAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<TimeDownBean> mTimeDownBeanList;
    public CountDownAdapter(Context context,List<TimeDownBean> mTimeDownBeanList){
        this.mContext = context;
        this.mTimeDownBeanList = mTimeDownBeanList;
        startTime();
    }


    /**
     * 列表倒计时
     */
    private void startTime() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < mTimeDownBeanList.size(); i++) {
                            long useTime = mTimeDownBeanList.get(i).getUseTime();
                            if (useTime > 0) {
                                useTime -= 1000;
                                mTimeDownBeanList.get(i).setUseTime(useTime);
                                if (useTime <= 0 && mTimeDownBeanList.get(i).isTimeFlag()) {
                                    mTimeDownBeanList.get(i).setTimeFlag(false);
                                    CountDownAdapter.this.notifyItemChanged(i);
                                }else {
                                    mTimeDownBeanList.get(i).setTimeFlag(true);
                                    CountDownAdapter.this.notifyItemChanged(i);
                                }

                            }

                        }

                    }
                });


            }
        }, 0, 1000);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.count_down_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            TimeDownBean timeDownBean = mTimeDownBeanList.get(position);
            viewHolder.contentTv.setText(timeDownBean.getContent());
            try {
                setTime(viewHolder, position);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    private void setTime(ViewHolder holder, int position) throws ParseException{
        TimeDownBean timeDownBean = mTimeDownBeanList.get(position);
        if (timeDownBean.getUseTime() > 1000) {
            holder.timeTv.setVisibility(View.VISIBLE);
            long useTime = timeDownBean.getUseTime();
            useTime = useTime / 1000;
            setTimeShow(useTime, holder);

        }else {
            holder.timeTv.setVisibility(View.GONE);
        }

    }

    private void setTimeShow(long useTime, ViewHolder holder) {
        int hour = (int) (useTime / 3600 );
        int min = (int) (useTime / 60 % 60);
        int second = (int) (useTime % 60);
        int day = (int) (useTime / 3600 / 24);
        String mDay, mHour, mMin, mSecond;//天，小时，分钟，秒
        second--;
        if (second < 0) {
            min--;
            second = 59;
            if (min < 0) {
                min = 59;
                hour--;
            }
        }
        if (hour < 10) {
            mHour = "0" + hour;
        } else {
            mHour = "" + hour;
        }
        if (min < 10) {
            mMin = "0" + min;
        } else {
            mMin = "" + min;
        }
        if (second < 10) {
            mSecond = "0" + second;
        } else {
            mSecond = "" + second;
        }
        String strTime = "上架倒计时 " +mHour + ":" + mMin + ":" + mSecond + "";
        holder.timeTv.setText(strTime);

    }


    @Override
    public int getItemCount() {
        return mTimeDownBeanList!=null?mTimeDownBeanList.size():0;
    }




    private class ViewHolder extends RecyclerView.ViewHolder{
        private TextView contentTv;
        private TextView timeTv;
        private ViewHolder(View itemView) {
            super(itemView);
            init(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener!=null){
                        itemClickListener.onItemClick(v,getLayoutPosition());
                    }
                }
            });
        }

        private void init(View itemView) {
            contentTv = (TextView)itemView.findViewById(R.id.content_tv);
            timeTv = (TextView)itemView.findViewById(R.id.time_tv);
        }
    }



    //对外部暴漏点击事件接口
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

    public OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
