package com.example.photocropdemo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.photocropdemo.interfaces.ICropListener;
import com.example.photocropdemo.manager.CropManager;


public class MainActivity extends Activity implements OnClickListener,ICropListener {
	private Intent intent;
	private ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (ImageView) findViewById(R.id.img_test);
		intent = new Intent(MainActivity.this,ImageCropActivity.class);
		img.setOnClickListener(this);
		CropManager.getInstance().setCropListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
//		overridePendingTransition(0, 0);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_test:
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public void onSave(Bitmap bitmap) {
		img.setImageBitmap(bitmap);
	}

}
