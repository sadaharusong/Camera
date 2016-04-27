package com.example.cermra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity 
{
	private static int REQ_1 = 1;
	private static int REQ_2 = 2;
	private ImageView mImageView;
	private String mFilePath;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.iv);
		mFilePath = Environment.getExternalStorageDirectory().getPath();
		mFilePath = mFilePath + "/" + "temp.png";
		
	}

	public void startCamera1(View view)
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, REQ_1);
	}
	
	public void startCamera2(View view)
	{
		Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri photoUri = Uri.fromFile(new File(mFilePath));
		intent2.putExtra(MediaStore.EXTRA_OUTPUT, photoUri); // ¸ü¸ÄÂ·¾¶
		startActivityForResult(intent2, REQ_2);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_OK)
		{
			if (requestCode == REQ_1)
			{
				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bundle.get("data");
				mImageView.setImageBitmap(bitmap);
			} else if (requestCode == REQ_2)
			{
				FileInputStream fis = null;
				try
				{
					fis = new FileInputStream(mFilePath);
					Bitmap bitmap = BitmapFactory.decodeStream(fis);
					mImageView.setImageBitmap(bitmap);
				} catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally
				{
					try
					{
						fis.close();
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	
}
