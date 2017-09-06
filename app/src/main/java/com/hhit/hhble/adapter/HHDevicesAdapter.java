package com.hhit.hhble.adapter;

import android.util.Log;

import com.hhit.hhble.R;
import com.hhit.hhble.base.HHItemClickLitener;
import com.hhit.hhble.bean.HHDeviceBean;
import com.hhit.hhble.base.BaseViewHolderHelper;
import com.hhit.hhble.base.RecyclerViewBaseAdapter;

import rx.functions.Action1;

/**
 * Created by chrisw on 2017/9/5.
 */

public class HHDevicesAdapter extends RecyclerViewBaseAdapter<HHDeviceBean> {
    final static String TAG = HHDevicesAdapter.class.getSimpleName();
    private HHItemClickLitener mHHItemClickLitener;

    public void setHhItemClickLitener(HHItemClickLitener hhItemClickLitener) {
        this.mHHItemClickLitener = hhItemClickLitener;
    }

    private boolean bShowStatus;

    public HHDevicesAdapter(boolean bShowStatus) {
        this.bShowStatus = bShowStatus;
    }

    @Override
    public int layoutResId(int viewType) {
        return R.layout.item_device;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolderHelper holder, final int position) {
        final HHDeviceBean deviceBean = mData.get(position);
        holder.setTextView(R.id.tv_name, deviceBean.getName());
        if(bShowStatus){
            if(deviceBean.getState() == 0){
                holder.setTextView(R.id.tv_status, "未绑定");
            } else if(deviceBean.getState() == 1){
                holder.setTextView(R.id.tv_status, "绑定");
            }
        }else {
            holder.setTextView(R.id.tv_status, "");
        }


        clicks(holder.getItemView(), new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Log.d(TAG, deviceBean.getName());
                if(mHHItemClickLitener != null){
                    mHHItemClickLitener.onItemClick(holder.getItemView(), position);
                }
            }
        });
    }
}
