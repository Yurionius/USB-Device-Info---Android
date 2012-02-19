/*******************************************************************************
 * Copyright 2012 Alexandros Schillings
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package aws.apps.usbDeviceEnumerator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import aws.apps.usbDeviceEnumerator.MyUsb.MyUsbDevice;

public class Act_UsbInfo extends Activity{
	public static final String EXTRA_TYPE =  "type";
	public static final String EXTRA_DATA_ANDROID =  "data_android";
	public static final String EXTRA_DATA_LINUX =  "data_linux";
	
	/** Called when the activity is first created. */
	
	private int mType;
	private String mAndroidKey;
	private MyUsbDevice mLinuxDevice;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_usb_info);
		
		Bundle b = getIntent().getExtras();
		mType = b.getInt(EXTRA_TYPE);
		mAndroidKey = b.getString(EXTRA_DATA_ANDROID);
		mLinuxDevice = b.getParcelable(EXTRA_DATA_LINUX);
		
		if (mType == Frag_AbstractUsbDeviceInfo.TYPE_ANDROID_INFO){
			Fragment f = new Frag_UsbDeviceInfoAndroid(mAndroidKey);

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.fragment_container, f);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

			ft.commit();
		} 
		else if(mType == Frag_AbstractUsbDeviceInfo.TYPE_LINUX_INFO){
			Fragment f = new Frag_UsbDeviceInfoLinux(mLinuxDevice);

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.fragment_container, f);
			ft.setTransition(FragmentTransaction.TRANSIT_NONE);

			ft.commit();
		} else {
			finish();
		}
	}
}
