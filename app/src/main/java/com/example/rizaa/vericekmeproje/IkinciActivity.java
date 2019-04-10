package com.example.rizaa.vericekmeproje;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class IkinciActivity extends AppCompatActivity {
    Button geri;

    private ListView lv;
    public ArrayList liste=new ArrayList();
    private ArrayAdapter<String> adapter;

    private ProgressDialog progressDialog;
    private static  String URL="http://www.ozturkriza.com";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ikinci_activity);

        lv=(ListView)findViewById(R.id.list);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,liste);

           new VeriGetir().execute();



        geri=(Button)findViewById(R.id.btngeri);
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        }
    private class VeriGetir extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog= new ProgressDialog(IkinciActivity.this);
            progressDialog.setTitle("YeniÜrünler");
            progressDialog.setMessage("lütfen bekleyiniz");
            progressDialog.setIndeterminate(false);
            progressDialog.show();


        }


        @Override
        protected Void doInBackground(Void... voids) {try {
            Document gelen= Jsoup.connect(URL).timeout(30*1000).get();
            Elements veri=gelen.select("div [id=\"12\"]");
            for (int i=0;i<veri.size();i++     ){
                liste.add(veri.get(i).text());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            lv.setAdapter(adapter);
            progressDialog.dismiss();
        }

    }

}
