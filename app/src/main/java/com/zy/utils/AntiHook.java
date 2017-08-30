package com.zy.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dalvik.system.DexFile;
 
/** 
 * 检测native hook框架：Cydia Substrate、Xposed 
 *  
 * @author admin 
 * 
 */  

public class AntiHook {  
	 
    private static boolean findHookAppName(Context context) {  
    	PackageManager packageManager = context.getPackageManager();  
        List<ApplicationInfo> applicationInfoList = packageManager  
                .getInstalledApplications(PackageManager.GET_META_DATA);   
  
        for (ApplicationInfo applicationInfo : applicationInfoList) {  
            if (applicationInfo.packageName.equals("de.robv.android.xposed.installer")) {  
                Log.wtf("HookDetection", "Xposed found on the system.");  
                return true;  
            }  
            if (applicationInfo.packageName.equals("com.saurik.substrate")) {  
                Log.wtf("HookDetection", "Substrate found on the system.");  
                return true;  
            }  
        }  
        return false;  
    }  
  
    private static boolean findHookAppFile() {  
        try {  
            Set<String> libraries = new HashSet<String>();  
            String mapsFilename = "/proc/" + android.os.Process.myPid() + "/maps";  
            BufferedReader reader = new BufferedReader(new FileReader(mapsFilename));  
            String line;  
            while ((line = reader.readLine()) != null) {  
                if (line.endsWith(".so") || line.endsWith(".jar")) {  
                    int n = line.lastIndexOf(" ");  
                    libraries.add(line.substring(n + 1));  
                }  
            }  
            reader.close();  
            for (String library : libraries) {  
                if (library.contains("com.saurik.substrate")) {  
                    Log.wtf("HookDetection", "Substrate shared object found: " + library);  
                    return true;  
                }  
                if (library.contains("XposedBridge.jar")) {  
                    Log.wtf("HookDetection", "Xposed JAR found: " + library);  
                    return true;  
                }  
            }  
        } catch (Exception e) {  
            Log.wtf("HookDetection", e.toString());  
        }  
        return false;  
    }  
  
    // 1. 如果存在Xposed框架hook  
    // （1）在dalvik.system.NativeStart.main方法后出现de.robv.android.xposed.XposedBridge.main调用  
    // （2）如果Xposed hook了调用栈里的一个方法，  
    // 还会有de.robv.android.xposed.XposedBridge.handleHookedMethod  
    // 和de.robv.android.xposed.XposedBridge.invokeOriginalMethodNative调用  
  
    // 2. 如果存在Substrate框架hook  
    // （1）dalvik.system.NativeStart.main调用后会出现2次com.android.internal.os.ZygoteInit.main，而不是一次。  
    // （2）如果Substrate hook了调用栈里的一个方法，  
    // 还会出现com.saurik.substrate.MS$2.invoked，com.saurik.substrate.MS$MethodPointer.invoke还有跟Substrate扩展相关的方法  
  
    private static boolean findHookStack() {  
        try {  
            throw new Exception("findhook");  
        } catch (Exception e) {  
  
            // 读取栈信息  
            // for(StackTraceElement stackTraceElement : e.getStackTrace()) {  
            // Log.wtf("HookDetection", stackTraceElement.getClassName() + "->"+  
            // stackTraceElement.getMethodName());  
            // }  
  
            int zygoteInitCallCount = 0;  
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {  
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit")) {  
                    zygoteInitCallCount++;  
                    if (zygoteInitCallCount == 2) {  
                        Log.wtf("HookDetection", "Substrate is active on the device.");  
                        return true;  
                    }  
                }  
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2")  
                        && stackTraceElement.getMethodName().equals("invoked")) {  
                    Log.wtf("HookDetection", "A method on the stack trace has been hooked using Substrate.");  
                    return true;  
                }  
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge")  
                        && stackTraceElement.getMethodName().equals("main")) {  
                    Log.wtf("HookDetection", "Xposed is active on the device.");  
                    return true;  
                }  
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge")  
                        && stackTraceElement.getMethodName().equals("handleHookedMethod")) {  
                    Log.wtf("HookDetection", "A method on the stack trace has been hooked using Xposed.");  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
    
    private static boolean findHookNative(Context context) {
    	PackageManager packageManager = context.getPackageManager();  
        List<ApplicationInfo> applicationInfoList = packageManager  
                .getInstalledApplications(PackageManager.GET_META_DATA); 
        
    	for (ApplicationInfo applicationInfo : applicationInfoList) {
    	    if (applicationInfo.processName.equals("com.heabk.mbk")) {      
    	        Set<String> classes = new HashSet<String>();
    	        DexFile dex;
    	        try {
    	            dex = new DexFile(applicationInfo.sourceDir);
    	            Enumeration<String> entries = dex.entries();
    	            while(entries.hasMoreElements()) {
    	                String entry = entries.nextElement();
    	                classes.add(entry);
    	            }
    	            dex.close();
    	        } 
    	        catch (IOException e) {
    	            Log.e("HookDetection", e.toString());
    	        }
    	        for(String className : classes) {
    	            if(className.startsWith("com.heabk.mbk")) {
//    	            	Log.wtf("Constant", className);
    	                try {
    	                    Class clazz = Class.forName(className);
    	                    for(Method method : clazz.getDeclaredMethods()) {
    	                    	String Str1="md5StrFromJNI";
    	                    	String Str2="sha1StrFromJNI";
    	                        if(Modifier.isNative(method.getModifiers())&&!method.getName().equals(Str1)&&!method.getName().equals(Str2)){
    	                            Log.wtf("Constant", "Native function found (could be hooked by Substrate or Xposed): " + clazz.getCanonicalName() + "->" + method.getName());
    	                            return true;
    	                        }
    	                    }
    	                }
    	                catch(ClassNotFoundException e) {
    	                    Log.wtf("Constant", e.toString());
    	                }
    	            }
    	        }
    	    }
    	}
		return false;  
    	
    }
  
    public static boolean isHook(Context context) {  
        if (findHookAppName(context) || findHookAppFile() || findHookStack()||findHookNative(context)) {  
            return true;  
        }  
        return false;  
    }  
  
}  