package au.edu.utas.mekvall.assignment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.InputStream;

public class NewRaffle extends AppCompatActivity {
    public static String NAME_KEY = "RAFFLENAME";
    public static String DESC_KEY = "DESC";
    public static String PERSON_KEY = "PERSON";
    public static String LOCATION_KEY = "LOCATION";
    public static String START_KEY = "START";
    public static String END_KEY = "END";
    public static String MAX_KEY = "MAX";
    public static String COMMANDS_KEY = "COMMANDS";
    public static String RAFFLEURI_KEY = "RAFFLEURI";
    private EditText inputRaffleName;
    private EditText inputDesc;
    private EditText inputPerson;
    private EditText inputLocation;
    private EditText inputStart;
    private EditText inputEnd;
    private EditText inputMaximumTickets;
    private Boolean imgSet;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    private ImageView imageSelected;
    private String RaffleImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_raffle);
        setTitle("New Raffle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgSet = false;

        imageSelected = findViewById(R.id.SelectedImage);

        inputRaffleName = findViewById(R.id.inputRaffleName);
        inputDesc = findViewById(R.id.inputDesc);
        inputPerson = findViewById(R.id.inputPerson);
        inputLocation = findViewById(R.id.inputLocation);
        inputStart = findViewById(R.id.inputStart);
        inputEnd = findViewById(R.id.inputEnd);
        inputMaximumTickets = findViewById(R.id.inputMaximumTickets);

        Button btnImage = findViewById(R.id.btnImage);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(NewRaffle.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
                } else {
                    SelectImage();
                }
            }
        });

        Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputRaffleName.length() > 0 && inputDesc.length() > 0) {
                    Intent i = new Intent(NewRaffle.this, CreateRaffleConfirm.class);

                    String saleCommands = "";
                    String name = inputRaffleName.getText().toString();
                    String desc = inputDesc.getText().toString();
                    i.putExtra(NAME_KEY, name);
                    i.putExtra(DESC_KEY, desc);


                    if (inputPerson.length() > 0) {
                        String person = inputPerson.getText().toString();
                        i.putExtra(PERSON_KEY, person);
                        saleCommands += "PERSON,";
                    }
                    if (inputLocation.length() > 0) {
                        String location = inputLocation.getText().toString();
                        i.putExtra(LOCATION_KEY, location);
                        saleCommands += "LOCATION,";
                    }
                    if (inputStart.length() > 0) {
                        long start = Long.parseLong(inputStart.getText().toString());
                        i.putExtra(START_KEY, start);
                        saleCommands += "START,";
                    }
                    if (inputEnd.length() > 0) {
                        long end = Long.parseLong(inputEnd.getText().toString());
                        i.putExtra(END_KEY, end);
                        saleCommands += "END,";
                    }
                    if (inputMaximumTickets.length() > 0) {
                        int max = Integer.parseInt(inputMaximumTickets.getText().toString());
                        i.putExtra(MAX_KEY, max);
                        saleCommands += "MAX,";
                    } else {
                        int max = 1000;
                        i.putExtra(MAX_KEY, max);
                        saleCommands += "MAX,";
                    }
                    if (imgSet) {
                        String raffleImage = RaffleImageUri;
                        i.putExtra(RAFFLEURI_KEY, raffleImage);
                        saleCommands += "RAFFLEURI,";
                    }

                    i.putExtra(COMMANDS_KEY, saleCommands);
                    startActivity(i);
                    imgSet = false;

                } else {
                    //popup error message to fill in name and desc
                }
            }
        });
    }

    // Reference: Chirag Kachhadiya https://www.youtube.com/watch?v=qs-p6SiKDt8
    private void SelectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0 ) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();

                if (selectedImageUri != null) {
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageSelected.setImageBitmap(bitmap);
                        RaffleImageUri = selectedImageUri.toString();
                        imgSet = true;

                        File selectedImageFile = new File(getPathFromUri(selectedImageUri));

                    } catch (Exception e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private String getPathFromUri(Uri contentUri) {
        String filePath;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            filePath = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }

}
