package com.zy.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.ImageView;

/**
 * 图片处理工具类
 * @author 田裕杰
 * 
 */
public class ImageUtil {

	private static ImageUtil imageUtil;

	public static ImageUtil getInstance() {
		if (imageUtil == null) {
			imageUtil = new ImageUtil();
			return imageUtil;
		}
		return imageUtil;
	}

	/**
	 *  通过画笔从新绘制图片
	 * @param d
	 *            图片对象
	 * @param p
	 *            画笔
	 * @return Drawable
	 * */
	private Drawable createDrawable(Drawable d, Paint p) {
		BitmapDrawable bd = (BitmapDrawable) d;
		Bitmap b = bd.getBitmap();
		Bitmap bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(),
				bd.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(b, 0, 0, p); // 关键代码，使用新的Paint画原图，
		return new BitmapDrawable(bitmap);
	}

	/**
	 *  设置Selector。 本次只增加点击变暗的效果，注释的代码为更多的效果
	 * @param drawable
	 *            图片对象
	 * @return StateListDrawable
	 * */
	private StateListDrawable createSLD(Drawable drawable) {
		StateListDrawable bg = new StateListDrawable();
		Paint p = new Paint();
		p.setColor(0x40222222); // Paint ARGB色值，A = 0x40 不透明。RGB222222 暗色

		Drawable unpress = drawable;
		Drawable pressed = createDrawable(drawable, p);
		// p = new Paint();
		// p.setColor(0x8000FF00);
		// Drawable focused = createDrawable(drawable, p);
		// p = new Paint();
		// p.setColor(0x800000FF);
		// Drawable unable = createDrawable(drawable, p);
		// View.PRESSED_ENABLED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_pressed,
				android.R.attr.state_enabled }, pressed);
		// View.ENABLED_FOCUSED_STATE_SET
		// bg.addState(new int[] { android.R.attr.state_enabled,
		// android.R.attr.state_focused }, focused);
		// View.ENABLED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_enabled }, unpress);
		// View.FOCUSED_STATE_SET
		// bg.addState(new int[] { android.R.attr.state_focused }, focused);
		// // View.WINDOW_FOCUSED_STATE_SET
		// bg.addState(new int[] { android.R.attr.state_window_focused },
		// unable);
		// View.EMPTY_STATE_SET
		bg.addState(new int[] {}, unpress);
		return bg;
	}

	/**
	 *  获取带点击效果的图片对象
	 * @param drawable
	 *            图片对象
	 * @return StateListDrawable
	 * */
	public StateListDrawable getStateListDrawable(Drawable drawable) {
		return createSLD(drawable);
	}
	/**
	 *  从新绘制Bitmap尺寸
	 * @param bitmap
	 *            图片对象
	 * @param w
	 *            图片修改后宽度
	 * @param h
	 *            图片修改后高度
	 * @return Bitmap
	 * */
	
	public Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		Matrix matrix = new Matrix();
		if (w==0) {
			float scaleHeight = (float) h / height;
			matrix.postScale(scaleHeight, scaleHeight);
		}else if (h==0) {
			float scaleWidth = (float) w / width;
			matrix.postScale(scaleWidth, scaleWidth);
		}else {
			float scaleWidth = (float) w / width;
			float scaleHeight = (float) h / height;
			matrix.postScale(scaleWidth, scaleHeight);
		}
		Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
				true);
		return bmp;
	}

	

	/**
	 *  从新绘制drawable尺寸
	 * @param drawable
	 *            图片对象
	 * @param w
	 *            图片修改后宽度
	 * @param h
	 *            图片修改后高度
	 * @return Drawable
	 * */
	@SuppressWarnings("deprecation")
	public Drawable zoomDrawable(Drawable drawable, int w, int h) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap oldMp = FormatTools.getInstance().Drawable2Bitmap(drawable);
		Matrix matrix = new Matrix();
		if (w==0) {
			float scaleHeight = (float) h / height;
			matrix.postScale(scaleHeight, scaleHeight);
		}else if (h==0) {
			float scaleWidth = (float) w / width;
			matrix.postScale(scaleWidth, scaleWidth);
		}else {
			float scaleWidth = (float) w / width;
			float scaleHeight = (float) h / height;
			matrix.postScale(scaleWidth, scaleHeight);
		}
		Bitmap newbmp = Bitmap.createBitmap(oldMp, 0, 0, width, height, matrix,
				true);
		return new BitmapDrawable(newbmp);
	}
	/**
	 *  bitmap转drawable 转换时drawable像素会缩小一半 所以用下面的方法
	 * @param bitmap
	 *            图片对象（bitmap）
	 * @param activity
	 *            activity对象
	 * @return Drawable
	 * */
	public Drawable getDrawable(Bitmap bitmap, Activity activity) {
		ImageView aImageView = new ImageView(activity);
		aImageView.setImageBitmap(bitmap);
		return aImageView.getDrawable();
	}

	/**
	 *  代码动态设置RadioButton的背景图片 用来做换肤
	 * @param normal
	 *            原始图片对象（Drawable）
	 * @return StateListDrawable
	 * */
	public StateListDrawable getRadioButtonBg(Drawable normal) {
		Paint p = new Paint();
		p.setColor(0x40222222); // Paint ARGB色值，A = 0x40 不透明。RGB222222 暗色
		Drawable pressed = createDrawable(normal, p);
		return newSelector(normal, pressed);
	}

	/**
	 *  代码动态设置RadioButton的背景图片 用来做换肤
	 * @param normal
	 *            正常状态下图片显示
	 * @param pressed
	 *            选中状态图片显示
	 * @return StateListDrawable
	 * */
	public StateListDrawable getRadioButtonBg(Drawable normal, Drawable pressed) {
		return newSelector(normal, pressed);
	}

	/**
	 *  设置Selector
	 * @param normal
	 *            初始状态图片
	 * @param pressed
	 *            状态改变时图片
	 * @return StateListDrawable
	 * */
	private StateListDrawable newSelector(Drawable normal, Drawable pressed) {
		StateListDrawable bg = new StateListDrawable();
		// View.state_pressed
		bg.addState(new int[] { android.R.attr.state_pressed }, pressed);
		// View.FOCUSED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_focused }, pressed);
		// View.state_checked
		bg.addState(new int[] { android.R.attr.state_checked }, pressed);
		// View.EMPTY_STATE_SET
		bg.addState(new int[] {}, normal);
		return bg;
	}

	/**
	 *  获取view截图
	 * @param view
	 *            需要截图的view
	 * @return Drawable
	 * */
	public Drawable getViewScreenShot(View view) {
		Bitmap obmp ;
		BitmapDrawable transitionsDrawable = null;
		view.setDrawingCacheEnabled(true);// 设置图片缓冲区
		if (view.getDrawingCache() != null) {
			obmp = Bitmap.createBitmap(view.getDrawingCache());
			view.setDrawingCacheEnabled(false);// 清空图片缓冲区
			transitionsDrawable = new BitmapDrawable(obmp);
			obmp.recycle();
		}
		return transitionsDrawable;
	}
	/**
	 * 获取透明图片
	 * */
	public Drawable getScaleDrawable(Drawable drawable,Activity activity){
		Paint p = new Paint();
		p.setColor(0x40222222); // Paint ARGB色值，A = 0x40 不透明。RGB222222 暗色
		BitmapDrawable bd = (BitmapDrawable) drawable;
		Bitmap b = bd.getBitmap();
		Bitmap bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(),
				bd.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(b, 0, 0, p); // 关键代码，使用新的Paint画原图，
		return getDrawable(bitmap, activity);
	}
	/**
     * 图片上画字
     * */
    public Drawable drawTextAtDrawable(Drawable drawable,String text,Activity activity){
    	LogUtil.d(activity, "在图片上写字:"+text);
    	Bitmap bitmap = FormatTools.getInstance().Drawable2Bitmap(drawable);
         int x = bitmap.getWidth();
         int y = bitmap.getHeight();
         // 创建一个和原图同样大小的位图
         Bitmap newbit = Bitmap.createBitmap(x, y, Config.ARGB_8888);
         Canvas canvas = new Canvas(newbit);
         Paint paint = new Paint();
         // 在原始位置0，0插入原图
         canvas.drawBitmap(bitmap, 0, 0, paint);
         paint.setColor(Color.RED);
         paint.setTextSize(50);
         // 在原图指定位置写上字
         canvas.drawText(text, x/3 , y/5*3, paint);
         canvas.save(Canvas.ALL_SAVE_FLAG);
         // 存储
         canvas.restore();
         return getDrawable(newbit, activity);
    }
    
}
