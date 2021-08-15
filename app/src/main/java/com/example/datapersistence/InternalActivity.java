package com.example.datapersistence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity {
    public static final String FILENAME = "namafile.txt";
    TextView textBaca;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        textBaca = findViewById(R.id.text_baca);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InternalActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void buatFile(View view) {
        String isiFile = "Coba isi data file text";
        File file = new File(getFilesDir(), FILENAME);

        Toast.makeText(this, "Make Text", Toast.LENGTH_SHORT).show();

       FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ubahFile(View view) {
        String ubah = "Update isi data file text";
        File file = new File(getFilesDir(), FILENAME);
        Toast.makeText(this, "Update Text", Toast.LENGTH_SHORT).show();
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void bacaFile(View view) {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        Toast.makeText(this, "Read Text", Toast.LENGTH_SHORT).show();
        if(file.exists()){
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();

                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            }catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }
            textBaca.setText(text.toString());
        }
    }

    public void hapusFile(View view) {
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show();
            file.delete();
        }
    }
}