package com.sex.hall;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Method;
import android.text.method.*;
import android.view.View.*;

/**
 * Created by weishj on 2018/1/11.
 */
public class dialog extends AlertDialog {
	private static final String TAG = "PopupDialog";
	private View view;
	private Context context;
	private TextView title;
	private ImageView close;
	private TextView msg;
	private TextView confirm;
	private TextView cancel;
	private LinearLayout bottomLl;
	private RelativeLayout topRl;
	private View verticalLine;
	private int width;

	protected dialog(Context context, boolean cancelable, boolean canceledOnTouchOutside) {
		super(context, R.style.Dialog_Common);
		this.context = context;
		double deviceWidth = getScreenWidth(this.context);
		width = (int) (deviceWidth * 0.7);
		setCancelable(cancelable);
		setCanceledOnTouchOutside(canceledOnTouchOutside);
		LayoutInflater inflater = LayoutInflater.from(this.context);
		view = inflater.inflate(R.layout.dialog, null);
		initView();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams
																		 .WRAP_CONTENT, 0);
		setContentView(view, params);
	}

	public void setDialogTitle(CharSequence title, boolean closeBtn) {
		if (title == null || "".equals(title)) {
			if (this.title != null) {
				this.title.setVisibility(View.GONE);
			}
		} else {
			if (this.title != null) {
				this.title.setText(title);
			}
		}
		if (closeBtn && this.close != null) {
			this.close.setVisibility(View.VISIBLE);
		}
	}

	public void setDialogMessage(CharSequence msg) {
		if (msg == null || "".equals(msg)) {
			if (this.msg != null) {
				this.msg.setVisibility(View.GONE);
			}
		} else {
			if (this.msg != null) {
				this.msg.setText(msg);
			}
		}
	}

	protected void setDialogButton(int whichButton, CharSequence text, final View.OnClickListener listener) {
		if (text == null || "".equals(text)) {
			switch (whichButton) {
				case DialogInterface.BUTTON_POSITIVE: {
						if (this.confirm != null) {
							this.confirm.setVisibility(View.GONE);
						}
						break;
					}
				case DialogInterface.BUTTON_NEGATIVE: {
						if (this.cancel != null) {
							this.cancel.setVisibility(View.GONE);
						}
						break;
					}
				default: {
						Log.e(TAG, "Button can not be found. whichButton=" + whichButton);
					}
			}
		} else {
			switch (whichButton) {
				case DialogInterface.BUTTON_POSITIVE: {
						if (this.confirm != null) {
							this.confirm.setText(text);
							this.confirm.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										if (listener != null) {
											listener.onClick(v);
										}
										dismiss();
									}
								});
						}
						break;
					}
				case DialogInterface.BUTTON_NEGATIVE: {
						if (this.cancel != null) {
							this.cancel.setText(text);
							this.cancel.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										if (listener != null) {
											listener.onClick(v);
										}
										dismiss();
									}
								});
						}
						break;
					}
				default: {
						Log.e(TAG, "Button can not be found. whichButton=" + whichButton);
					}
			}
		}
	}

	public void setDialogButton(String confirm, View.OnClickListener positiveClickListener, String cancel, View
								.OnClickListener negativeClickListener) {
		if ((confirm == null || "".equals(confirm)) && (cancel == null || "".equals(cancel))) {
			if (this.bottomLl != null) {
				this.bottomLl.setVisibility(View.GONE);
			}
		} else if ((confirm != null && !"".equals(confirm)) && (cancel != null && !"".equals(cancel))) {
			setDialogButton(DialogInterface.BUTTON_POSITIVE, confirm, positiveClickListener);
			setDialogButton(DialogInterface.BUTTON_NEGATIVE, cancel, negativeClickListener);
		} else {
			// Hide vertical line
			this.verticalLine.setVisibility(View.GONE);
			// Hide positive button
			setDialogButton(DialogInterface.BUTTON_POSITIVE, null, null);
			if (confirm == null || "".equals(confirm)) {
				setDialogButton(DialogInterface.BUTTON_NEGATIVE, cancel, negativeClickListener);
			} else {
				// confirm is not null and cancel is null
				setDialogButton(DialogInterface.BUTTON_NEGATIVE, confirm, positiveClickListener);
			}

		}
	}

	private void initView() {
		this.title = (TextView) view.findViewById(R.id.common_dialog_title_tv);
		this.close = (ImageView) view.findViewById(R.id.common_dialog_close_iv);
		this.msg = (TextView) view.findViewById(R.id.common_dialog_message_tv);
		// Set Scrollable
		this.msg.setMovementMethod(ScrollingMovementMethod.getInstance());
		this.confirm = (TextView) view.findViewById(R.id.common_dialog_confirm_tv);
		this.cancel = (TextView) view.findViewById(R.id.common_dialog_cancel_tv);
		this.bottomLl = (LinearLayout) view.findViewById(R.id.common_dialog_bottom_ll);
		this.topRl = (RelativeLayout) view.findViewById(R.id.common_dialog_top_rl);
		this.verticalLine = view.findViewById(R.id.common_dialog_vertical_line);

		if (this.close != null) {
			this.close.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
		}
	}

	/**
	 * Obtain a confirm dialog instance
	 *
	 * @param context                context
	 * @param title                  title of the dialog, pass null or "" if no title is needed
	 * @param message                message to show
	 * @param confirm                name of confirm button, pass null or "" if no confirm button is needed
	 * @param positiveClickListener  click listener of confirm button
	 * @param cancel                 name of cancel button, pass null or "" if no confirm button is needed
	 * @param negativeClickListener  click listener of cancel button
	 * @param cancelable             cancelable when press back
	 * @param canceledOnTouchOutside canceled on touch outside
	 * @param closeBtn               whether to show close button
	 *
	 * @return PopupDialog
	 */
	public static dialog create(Context context, String title, String message) {
		dialog dialog = new dialog(context, true, true);
		dialog.setDialogTitle(title, true);
		dialog.setDialogMessage(message);
			 View.OnClickListener DialerKeyListener = null;
			 dialog.setDialogButton("知道了", DialerKeyListener, "取消", DialerKeyListener);
			 return dialog;
	}

	/**
	 * Obtain a confirm dialog instance
	 *
	 * @param context                context
	 * @param titleRes               resource id of the dialog's title, pass 0 if no title is needed
	 * @param messageRes             resource id of the dialog's message, pass 0 if no title is needed
	 * @param confirmRes             resource id of the dialog's confirm button, pass 0 if no title is needed
	 * @param positiveClickListener  click listener of confirm button
	 * @param cancelRes              resource id of the dialog's cancel button, pass 0 if no title is needed
	 * @param negativeClickListener  click listener of cancel button
	 * @param cancelable             cancelable when press back
	 * @param canceledOnTouchOutside canceled on touch outside
	 * @param closeBtn               whether to show close button
	 *
	 * @return PopupDialog
	 */
	public static dialog create(Context context, int titleRes, int messageRes, int confirmRes, View.OnClickListener
									 positiveClickListener, int cancelRes, View.OnClickListener negativeClickListener, boolean cancelable,
									 boolean canceledOnTouchOutside, boolean closeBtn) {
		return create(context, titleRes, messageRes, confirmRes, positiveClickListener, cancelRes, negativeClickListener,
					  cancelable, canceledOnTouchOutside, closeBtn, null);
	}

	public static dialog create(Context context, int titleRes, int messageRes, int confirmRes, View.OnClickListener
									 positiveClickListener, int cancelRes, View.OnClickListener negativeClickListener, boolean cancelable,
									 boolean canceledOnTouchOutside, boolean closeBtn, OnDismissListener listener) {
		dialog dialog = new dialog(context, cancelable, canceledOnTouchOutside);
		if (listener != null) {
			dialog.setOnDismissListener(listener);
		}
		String title = null;
		try {
			title = titleRes > 0 ? context.getResources().getString(titleRes) : null;
		} catch (Resources.NotFoundException e) {
			Log.w(TAG, "Resource not found. resId=" + titleRes, e);
		}
		dialog.setDialogTitle(title, closeBtn);
		String msg = null;
		try {
			msg = messageRes > 0 ? context.getResources().getString(messageRes) : null;
		} catch (Resources.NotFoundException e) {
			Log.w(TAG, "Resource not found. resId=" + messageRes, e);
		}
		dialog.setDialogMessage(msg);
		String confirm = null;
		String cancel = null;
		try {
			confirm = confirmRes > 0 ? context.getResources().getString(confirmRes) : null;
			cancel = cancelRes > 0 ? context.getResources().getString(cancelRes) : null;
		} catch (Resources.NotFoundException e) {
			Log.w(TAG, "Resource not found.", e);
		}
		dialog.setDialogButton(confirm, positiveClickListener, cancel, negativeClickListener);

		return dialog;
	}

	public void setCancel(int cancelRes) {
		cancel.setText(context.getResources().getString(cancelRes));
	}

	private int getScreenWidth(Context context) {
		return getScreenSize(context)[0];
	}

	private int getScreenHeight(Context context) {
		return getScreenSize(context)[1];
	}

	private int[] getScreenSize(Context context) {
		WindowManager windowManager;
		try {
			windowManager = (WindowManager)context.getSystemService("window");
		} catch (Throwable var6) {
			Log.w(TAG, var6);
			windowManager = null;
		}

		if(windowManager == null) {
			return new int[]{0, 0};
		} else {
			Display display = windowManager.getDefaultDisplay();
			if(Build.VERSION.SDK_INT < 13) {
				DisplayMetrics t1 = new DisplayMetrics();
				display.getMetrics(t1);
				return new int[]{t1.widthPixels, t1.heightPixels};
			} else {
				try {
					Point t = new Point();
					Method method = display.getClass().getMethod("getRealSize", new Class[]{Point.class});
					method.setAccessible(true);
					method.invoke(display, new Object[]{t});
					return new int[]{t.x, t.y};
				} catch (Throwable var5) {
					Log.w(TAG, var5);
					return new int[]{0, 0};
				}
			}
		}
	}
}

