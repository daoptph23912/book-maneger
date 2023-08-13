package com.daoptph23912.duanmau_daoptph23912_thi.SharePre;

import android.content.Context;
import android.content.SharedPreferences;

import com.daoptph23912.duanmau_daoptph23912_thi.model.ThuThu;


public class Share {
    private final SharedPreferences share;
    private final SharedPreferences.Editor editor;
    private final String KEY_MATT ="TK",KEY_MK = "MK",KEY_MATT2 = "TK2",KEY_NAME ="NAME";
    private static final String NAME_SHARE = "Phạm Thành Đạo";

    public Share(Context context){
        share = context.getSharedPreferences(NAME_SHARE,Context.MODE_PRIVATE);
        editor = share.edit();
    }
    public void putAccount(ThuThu obj, boolean check){
        String maTT = obj.getMaTT();
        editor.putString(KEY_MATT2,maTT);
        editor.putString(KEY_NAME,obj.getTen());
        if(check){
            editor.putString(KEY_MATT,maTT);
            editor.putString(KEY_MK,obj.getMatKhau());
        }else if(share.getString(KEY_MATT,null)!= null && maTT.equals(share.getString(KEY_MATT,null)))
            editor.remove(KEY_MATT);
        editor.apply();
    }

    public ThuThu getAccount(){
        String maTT = share.getString(KEY_MATT,null);
        if(maTT != null)
            return new ThuThu(maTT,share.getString(KEY_NAME,null),share.getString(KEY_MK,null));
        return null;
    }

    public String getMaTT(){
        return share.getString(KEY_MATT2,null);
    }
    public String getName(){return share.getString(KEY_NAME,"Phạm Thành Đạo");}
}
