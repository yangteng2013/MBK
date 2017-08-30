//package com.zy.utils;
//
//import com.csii.powerenter.PEEditText;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//
///**
// * �ж�����ǿ��
// *
// */
//public class FormatPassword {
//	static OnPWDListener mOnPWDListener;
//	public static void formatPassword(final PEEditText editText,final TextView textView,final TextView textView1,final TextView textView2,final TextView textView3){
//		editText.addTextChangedListener(new TextWatcher() {
//
//				public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//				}
//
//				public void beforeTextChanged(CharSequence s, int start, int count,
//						int after) {
//
//				}
//
//				public void afterTextChanged(Editable s) {
//					int content_type=editText.getContentType();
////
////					if(content_type==0)Toast.makeText(CsiiActivity.this,"����Ϊ��",Toast.LENGTH_SHORT).show();
////					else if(content_type==1)Toast.makeText(CsiiActivity.this,"������ֻ��������",Toast.LENGTH_SHORT).show();
////					else if(content_type==2)Toast.makeText(CsiiActivity.this,"������ֻ������ĸ",Toast.LENGTH_SHORT).show();
////					else if(content_type==3)Toast.makeText(CsiiActivity.this,"�����а������ֺ���ĸ",Toast.LENGTH_SHORT).show();
////					else if(content_type==4)Toast.makeText(CsiiActivity.this,"������ֻ���������ַ�",Toast.LENGTH_SHORT).show();
////					else if(content_type==5)Toast.makeText(CsiiActivity.this,"�����а��������ַ�������",Toast.LENGTH_SHORT).show();
////					else if(content_type==6)Toast.makeText(CsiiActivity.this,"�����а��������ַ�����ĸ",Toast.LENGTH_SHORT).show();
////					else if(content_type==7)Toast.makeText(CsiiActivity.this,"�����а��������ַ�����ĸ������",Toast.LENGTH_SHORT).show();
//
//					if(content_type==1||content_type==2||content_type==4){
//						textView.setVisibility(View.VISIBLE);
//						textView1.setVisibility(View.INVISIBLE);
//						textView2.setVisibility(View.INVISIBLE);
//						textView3.setText("��");
//						mOnPWDListener.onComplete(false);
//					}else if(content_type==3||content_type==5||content_type==6){
//						textView.setVisibility(View.VISIBLE);
//						textView1.setVisibility(View.VISIBLE);
//						textView2.setVisibility(View.INVISIBLE);
//						textView3.setText("��");
//						mOnPWDListener.onComplete(true);
//					}else if(content_type==7){
//						textView.setVisibility(View.VISIBLE);
//						textView1.setVisibility(View.VISIBLE);
//						textView2.setVisibility(View.VISIBLE);
//						textView3.setText("ǿ");
//						mOnPWDListener.onComplete(true);
//					}else{
//						textView.setVisibility(View.INVISIBLE);
//						textView1.setVisibility(View.INVISIBLE);
//						textView2.setVisibility(View.INVISIBLE);
//						textView3.setText("");
//						mOnPWDListener.onComplete(false);
//					}
//				}
//			});
//	}
//	public static void setonPWDListener(OnPWDListener OnPWDListener){
//		mOnPWDListener=OnPWDListener;
//	}
//	/**
//	 *�Ƿ�����ĸ�������
//	 */
//	public interface OnPWDListener{
//		public void onComplete(boolean is);
//	}
//}
