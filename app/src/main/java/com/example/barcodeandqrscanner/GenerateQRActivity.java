package com.example.barcodeandqrscanner;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateQRActivity extends AppCompatActivity {
    EditText etQrGenerate;
    ImageView ivQrCode;
    Button bQrGenerate;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

        etQrGenerate = findViewById(R.id.etQrGenerate);
        bQrGenerate = findViewById(R.id.bGenerateQr);
        ivQrCode = findViewById(R.id.ivQrCode);

        bQrGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = etQrGenerate.getText().toString();
                if (text.equals("")){
                    if (TextUtils.isEmpty(text)) etQrGenerate.setError("This can't be blank !");
                }else {
                    MultiFormatWriter writer = new MultiFormatWriter();

                    try {
                        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE,200,200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        ivQrCode.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}
