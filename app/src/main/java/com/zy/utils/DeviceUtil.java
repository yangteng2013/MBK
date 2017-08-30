package com.zy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;

import com.zy.utils.crypto.MD5Util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  提供一些方法 用于获取移动设备信息
 * @author 田裕杰
 * */
public class DeviceUtil {
	/**
	 *  获取手机型号
	 * 
	 * @return String
	 */
	public static String getModel() {
		return android.os.Build.MODEL;
	}

	/**
	 *  获取手机MAC地址
	 * @param context
	 *            上下文
	 * @return String
	 */
	public static String getMac(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

		WifiInfo info = wifi.getConnectionInfo();

		return info.getMacAddress();
	}

	/**
	 *  获取手机设别android SDK版本号
	 * 
	 * @return int
	 * */
	public static int getSDKVersion() {
		int version = 0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return version;
	}

	/**
	 *  硬件设备类型
	 * 
	 * @return String
	 * */
	public static String getDeviceType() {
		return "android";
	}

	/**
	 *  获取手机设备IMEI
	 * @param context
	 *            上下文
	 * @return String
	 * */
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	/**
	 * 拼接唯一标识
	 */
	
	public static String getOnlyId(Context context){
		
//		String id= getIMEI(context)+getMac(context);
		String id= getIMEI(context);
		id = MD5Util.getMD5String(id);
		return id;
		
	}
	
	
	/**
	 *  获取手机屏幕尺寸
	 * @param context
	 *            上下文
	 * @return String(480*800)
	 */
	public static String getDisplayMetrics(Context context) {
		// String str = "";
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;// 屏幕宽（像素，如：480px）
		int screenHeight = dm.heightPixels;// 屏幕高（像素，如：800px）
		return String.valueOf(screenWidth) + "*" + String.valueOf(screenHeight);
	}

	/**
	 *  获取手机屏幕尺寸 高度
	 * @param context
	 *            上下文
	 * @return int
	 */
	public static int getMetricsHeight(Context context) {
		// String str = "";
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenHeight = dm.heightPixels;// 屏幕高（像素，如：800px）
		return screenHeight;
	}

	/**
	 *  获取手机屏幕尺寸 宽度
	 * @param context
	 *            上下文
	 * @return int
	 */
	public static int getMetricsWidth(Context context) {
		// String str = "";
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;// 屏幕高（像素，如：800px）
		return screenWidth;
	}

	/**
	 * 判断手机当前网络开关状态 wifi gprs
	 * 
	 * @param context
	 *            上下文
	 * @return boolean
	 * */
	public static boolean IsNetWork(Context context) {
		// 判断手机当前网络开关状态 wifi gprs
		boolean isnetwork = true;
		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			boolean isWifiConnected = cm.getNetworkInfo(
					ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ? true
					: false;
			boolean isGprsConnected = cm.getNetworkInfo(
					ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ? true
					: false;
			if (isWifiConnected || isGprsConnected) {
				isnetwork = true;
			} else {
				isnetwork = false;
			}
		} catch (Exception e) {
			isnetwork = true;
		}
		return isnetwork;
	}

	/**
	 * 获取IP地址
	 * 
	 * @param activity
	 * @return
	 */
	public static String getIPAddress(Context context) {
		String result = "";
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// 判断wifi是否开启
		if (wifiManager.isWifiEnabled()) {
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ip = wifiInfo.getIpAddress();
			result = intToIp(ip);
			// Log.e("wifi", ""+result);
		} else {
			result = getLocalIpAddress();
			// Log.e("gprs", ""+result);
		}
		// System.out.println("检查是否为IP:"+checkIP(result));
		if (!checkIP(result)) {
			result = "192.168.1.1";
		}
		return result;
	}

	/**
	 * 检查是否为IP
	 * 
	 * @param moble
	 * @return
	 */
	public static boolean checkIP(String ip) {
		Pattern p = Pattern
				.compile("(([1]{1}[0-9]{1}[0-9]{1}[.]{1})|([2]{1}[0-5]{1}[0-5]{1}[.]{1})|([1-9]{1}[0-9]{1}[.]{1})|([1-9]{1}[.]{1})|([0]{1}[.]{1})){3}(([1]{1}[0-9]{1}[0-9]{1})|([2]{1}[0-5]{1}[0-5]{1})|([1-9]{1}[0-9]{1})|([1-9]{1})|([0]{1})){1}");
		Matcher m = p.matcher(ip);
		return m.matches();
	}

	/**
	 * 获取GPRS ip地址
	 * 
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			// Log.e("WifiPreference IpAddress", ex.toString());
		}
		return "192.168.0.1";
	}

	/**
	 * 格式化 ip
	 * 
	 * @param i
	 * @return
	 */
	public static String intToIp(int i) {

		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + (i >> 24 & 0xFF);
	}

	/**
	 *  获取指定Activity的截屏
	 * @param activity
	 *            activity对象
	 * @return Bitmap
	 * */
	public static Bitmap getScreenShot(Activity activity) {

		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();
		// 获取状态栏高度
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		// 获取屏幕长和高
		int width = activity.getWindowManager().getDefaultDisplay().getWidth();
		int height = activity.getWindowManager().getDefaultDisplay()
				.getHeight();
		// 去掉标题栏
		// Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
		Bitmap b = Bitmap.createBitmap(b1, 0, 0, width, height);
		view.destroyDrawingCache();
		return b;
	}

	/**
	 *  判断sd卡是否存在
	 * 
	 * @return boolean
	 */
	public static boolean hasSDCard() {
		String SDState = Environment.getExternalStorageState();
		if (SDState.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *  判断手机GPS是否开启
	 * @param context
	 *            上下文
	 * @return boolean
	 */
	public static boolean isOpenGPS(Context context) {
		LocationManager alm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			return true;
		} else {
			return false;
			// Intent myIntent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
			// mContext.startActivity(myIntent);
		}
	}

	/**
	 *  获取应用程序版本号
	 * @param context
	 *            上下文
	 * @return String
	 * */
	public static String getVersionCode(Context context) {
		int versionCode = 0;
		try {
			// 获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return String.valueOf(versionCode);
	}

	/**
	 *  获取应用程序版本名
	 * @param context
	 *            上下文
	 * @return String
	 * */
	public static String getVersionName(Context context) {
		String versionName = "";
		try {
			// 获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionName = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}


	/**
	 *  获取手机屏幕密度
	 * @param activity
	 *            activity对象
	 * @return int
	 * */
	public static int getMetricDpi(Activity activity) {
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
		return densityDpi;
	}

	/**
	 *  获取设备信息
	 * @param context
	 *            上下文
	 * @return Map<String,String>
	 * */
	public static Map<String, String> getDeviceInfo(Context context) {
		Map<String, String> map = new HashMap<String, String>();
		map.clear();
		// IMEI
		map.put("DeviceId", getIMEI(context));
		// 判断sd卡是否存在,1存在，0不存在
		map.put("SDFlag", String.valueOf(hasSDCard()));
		// 获取设备型号
		map.put("Model", getModel());
		// 设备类型,0：手机1：平板2：电视3：其他
		map.put("DeviceType", "0");
		// Mac地址
		map.put("MacAddress", getMac(context));
		// 系统版本号
		map.put("SYSVersion", String.valueOf(getSDKVersion()));
		// 版本号
		map.put("VersionCode", String.valueOf(getVersionCode(context)));
		// 版本名称
		map.put("VersionName", getVersionName(context));
		// 客户端类型
		map.put("ClientType", "Android");
		// 屏幕信息
		map.put("DisplayMetrics", getDisplayMetrics(context));
		// Root标志true or false
		map.put("RootFlag", new Root().isDeviceRooted()+"");
		return map;

	}
	/** 
	 * 多个SD卡时 取外置SD卡<br> 
	 * 参考：http://blog.csdn.net/bbmiku/article/details/7937745<br> 
	 * @return 
	 */  
	public static String getExternalStorageDirectory() {  
	    Map<String, String> map = System.getenv();  
	    String[] values = new String[map.values().size()];  
	    map.values().toArray(values);  
	    String path = values[values.length - 1];    //外置SD卡的路径  
	    if (path.startsWith("/mnt/") && !Environment.getExternalStorageDirectory().getAbsolutePath().equals(path)){  
	        return path;  
	    }else{  
	        return null;  
	    }  
	}  
	/**
	 *  获取应用程序包 MD5值
	 * @param context
	 *            上下文
	 * @return Map（key：SIGNATURE_FLAG，value：apk包签名信息；key：APKMD5_FLAG，value：apk
	 *         MD5值）
	 * */
	public static Map<String, String> getAPKMD5Info(Context context) {
		Map<String, String> map = new HashMap<String, String>();
		String SIGNATURE_FLAG = "";
		String APKMD5_FLAG = "";
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packageInfo;
		try {
			packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), PackageManager.GET_SIGNATURES);
			//创建文件可以获取到文件的大小
			File f = new File(packageInfo.applicationInfo.sourceDir);
			for (Signature signature : packageInfo.signatures) {
				// 取到Package的签名
				SIGNATURE_FLAG = MD5Util
						.getMD5String(signature.toCharsString());
			}
			// 取到APKMD5
			APKMD5_FLAG = MD5Util.getFileMD5String(f);
			map.put("SIGNATURE_FLAG", SIGNATURE_FLAG);
			map.put("APKMD5_FLAG", APKMD5_FLAG);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
