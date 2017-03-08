package com.luoshihai.xxdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


public abstract class DialogUtil {
    private Dialog mDialog;
    private Window mDialogWindow;
    private DialogViewHolder dilaogVh;
    private View mRootView;

    public DialogUtil(Context context, int layoutId) {
        dilaogVh = DialogViewHolder.get(context, layoutId);
        mRootView = dilaogVh.getConvertView();
        mDialog = new Dialog(context, R.style.dialog);

        mDialog.setContentView(mRootView);
        mDialogWindow = mDialog.getWindow();
        convert(dilaogVh);
    }
    /**B
     * 把弹出框view holder传出去
     */
    public abstract void convert(DialogViewHolder viewHolder);

    public static AlertDialog.Builder creatNormalDialogBuilder(Context context, String title, String message) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message);
    }

    /**
     * 显示dialog
     */
    public DialogUtil showDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
        return this;
    }

    /**
     * 弹出时背景亮度 值为0.0~1.0
     * 1.0表示全黑  0.0表示全白
     */
    public DialogUtil backgroundLight(float light) {
        if (light < 0.0 || light > 1.0)
            return this;
        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        lp.dimAmount = light;
        mDialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 从底部一直弹到中间
     */
    public DialogUtil fromBottomToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.window_bottom_in_bottom_out);
        return this;
    }

    /**
     * 从底部弹出
     */
    public DialogUtil fromBottom() {
        fromBottomToMiddle();
        mDialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        return this;
    }

    /**
     * 从左边一直弹到中间退出也是到左边
     */
    public DialogUtil fromLeftToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.window_left_in_left_out);
        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return this;
    }

    /**
     * 从右边一直弹到中间退出也是到右边
     */
    public DialogUtil fromRightToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.window_left_in_right_out);
        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return this;
    }

    /**
     * 显示一个Dialog可以传递一个style
     */
    public DialogUtil showDialog(int style) {
        mDialogWindow.setWindowAnimations(style);
        mDialog.show();
        return this;
    }

    /**
     * 显示一个Dialog可以传递一个是否显示动画
     */
    public DialogUtil showDialog(boolean isAnimation) {
        mDialogWindow.setWindowAnimations(R.style.dialog_scale_animstyle);
        mDialog.show();
        return this;
    }

    /**
     * 全屏显示
     */
    public DialogUtil fullScreen() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }


    public DialogUtil setOnKeyListener(DialogInterface.OnKeyListener onKeyListener){
        mDialog.setOnKeyListener(onKeyListener);
        return this;
    }
    /**
     * 全屏宽度
     */
    public DialogUtil fullWidth() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * 全屏高度
     */
    public DialogUtil fullHeight() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * 自己设置高度和宽度
     */
    public DialogUtil setWidthAndHeight(int width, int height) {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = width;
        wl.height = height;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * cancel dialog
     */
    public void cancelDialog() {
        dismiss();
    }

    /**
     * cancel dialog
     */
    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
    /**
     * 设置监听
     */
    public DialogUtil setDialogDismissListener(OnDismissListener listener) {
        mDialog.setOnDismissListener(listener);
        return this;
    }

    /**
     * 设置监听
     */
    public DialogUtil setOnCancelListener(OnCancelListener listener) {
        mDialog.setOnCancelListener(listener);
        return this;
    }

    /**
     * 设置是否能取消
     */
    public DialogUtil setCancelAble(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }



    /**
     * 设置触摸其他地方是否能取消
     */
    public DialogUtil setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

}
