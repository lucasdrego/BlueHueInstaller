package br.crmdev.bluehueinstaller;

import android.app.*;
import android.content.*;
import android.graphics.drawable.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.method.*;
import android.view.*;
import android.widget.*;

public class SubActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sub);
		
		ActionBar ab = this.getActionBar();
		ab.setBackgroundDrawable(ColorDrawable.createFromPath("#FFFFFF"));
		ab.setDisplayShowHomeEnabled(true);
		ab.setHomeButtonEnabled(true);
		ab.setDisplayUseLogoEnabled(true);
		ab.setIcon(R.drawable.ic_launcher);
		
		//Navigation Bar Color
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) 
		{
			Window window = this.getWindow();

			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

			window.setStatusBarColor(this.getResources().getColor(R.drawable.indigodark));

			window.setNavigationBarColor(this.getResources().getColor(R.drawable.indigodark));
		}

		else
		{
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.voltar, menu);
		return true;
	}
	
	public boolean onVoltar(MenuItem item)
	{
		finish();
		return true;
	}
	
	public void onChangelog(View view)
	{
		
		AlertDialog.Builder ch = new AlertDialog.Builder(SubActivity.this);
		ch.setIcon(R.drawable.ic_launcher);
		ch.setTitle("Changelog");
		ch.setMessage(R.string.chang);
		ch.setPositiveButton("OK", null);
		ch.show();
	}
	
	public void onG(View view)
	{
		Uri uri = Uri.parse("https://plus.google.com/+LucasDiego29");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	
	public void onDev(View view)
	{
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.dev, null);
		
		AlertDialog.Builder dev = new AlertDialog.Builder(this);
		dev.setIcon(R.drawable.ic_launcher);
		dev.setTitle("DEV");
		dev.setView(layout);
		dev.setPositiveButton("OK", null);
		dev.show();
	}
	
}
