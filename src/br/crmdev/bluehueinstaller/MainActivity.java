package br.crmdev.bluehueinstaller;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.io.*;
import android.opengl.*;

public class MainActivity extends Activity {

	Button bt;
	
	static final int REQUEST_INSTALL = 1;
    static final int REQUEST_UNINSTALL = 2;

    protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		this.setContentView(R.layout.main);
	
		Button button =  (Button)findViewById(R.id.apk);
        button.setOnClickListener(mMySourceListener);
		
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
		
		bt = (Button)findViewById(R.id.info);
		bt.setOnClickListener(bton);
	}
	
	OnClickListener bton = new OnClickListener()
	{

		@Override
		public void onClick(View p1)
		{
			Intent intent = new Intent(MainActivity.this, SubActivity.class);
			startActivity(intent);
		}

		
	};
	
	//Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	@Override
	public boolean onSair(MenuItem item)
	{
		AlertDialog.Builder sair = new AlertDialog.Builder(MainActivity.this);
		sair.setIcon(R.drawable.ic_launcher);
		sair.setTitle(R.string.sair);
		sair.setMessage(R.string.sair_);
		sair.setPositiveButton(R.string.s, new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					finish();
				}
			});
		sair.setNeutralButton(R.string.n, null);
		sair.show();
		return true;
	}


	{
	};
	
	//Resultado da Instalação
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_INSTALL) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, R.string.v, Toast.LENGTH_LONG).show();
				
				Intent inten = new Intent(MainActivity.this, SubActivity.class);
				startActivity(inten);
				
				bt.setVisibility(View.VISIBLE);
            }
			
			else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, R.string.v_, Toast.LENGTH_LONG).show();
            }
			
			else {
                Toast.makeText(this, R.string.v__, Toast.LENGTH_LONG).show();
           }
		   
        } 
     }

	 
	 
	//Botão Instalar
	private OnClickListener mMySourceListener = new OnClickListener() {
        public void onClick(View v) {
			
			AlertDialog.Builder ins = new AlertDialog.Builder(MainActivity.this);
			ins.setIcon(R.drawable.ic_launcher);
			ins.setTitle(R.string.i);
			ins.setMessage(R.string.i_);
			ins.setPositiveButton(R.string.s, new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						AlertDialog.Builder ins1 = new AlertDialog.Builder(MainActivity.this);
						ins1.setIcon(R.drawable.ic_launcher);
						ins1.setTitle(R.string.i);
						ins1.setMessage(R.string.i__);
						ins1.setPositiveButton(R.string.s, new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface p1, int p2)
								{
									AlertDialog.Builder ins2 = new AlertDialog.Builder(MainActivity.this);
									ins2.setIcon(R.drawable.ic_launcher);
									ins2.setTitle(R.string.i);
									ins2.setMessage(R.string.i___);
									ins2.setPositiveButton(R.string.s, new DialogInterface.OnClickListener(){

											@Override
											public void onClick(DialogInterface p1, int p2)
											{
												AlertDialog.Builder ins2 = new AlertDialog.Builder(MainActivity.this);
												ins2.setIcon(R.drawable.ic_launcher);
												ins2.setTitle(R.string.i);
												ins2.setMessage(R.string.i____);
												ins2.setPositiveButton(R.string.ns, new DialogInterface.OnClickListener(){

														@Override
														public void onClick(DialogInterface p1, int p2)
														{
									 					    Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
															intent.setData(Uri.fromFile(prepareApk("bluehue.apk")));
															intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
															intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
															intent.putExtra(Intent.EXTRA_INSTALLER_PACKAGE_NAME, getApplicationInfo().packageName);
															startActivityForResult(intent, REQUEST_INSTALL);
														}
													});
												ins2.show();
											}
										});
									ins2.setNeutralButton(R.string.n, null);
									ins2.show();
								}
							});
						ins1.setNeutralButton(R.string.n, null);
						ins1.show();
					}
				});
			ins.setNeutralButton(R.string.n, null);
			ins.show();
        }
    };


	private File prepareApk(String assetName) {
        // Copy the given asset out into a file so that it can be installed.
        // Returns the path to the file.
        byte[] buffer = new byte[8192];
        InputStream is = null;
        FileOutputStream fout = null;
        try {
            is = getAssets().open(assetName);
            fout = openFileOutput("tmp.apk", Context.MODE_WORLD_READABLE);
            int n;
            while ((n=is.read(buffer)) >= 0) {
                fout.write(buffer, 0, n);
            }
        } catch (IOException e) {
            Log.i("InstallApk", "Failed transferring", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
            }
        }

        return getFileStreamPath("tmp.apk");
    }
	
}
