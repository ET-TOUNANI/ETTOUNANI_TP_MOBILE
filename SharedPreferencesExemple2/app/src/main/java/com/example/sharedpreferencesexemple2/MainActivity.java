package com.example.sharedpreferencesexemple2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBarSound ;
    private SeekBar seekBarBrightness;

    private RadioGroup radioGroupDiffLevel;
    private RadioButton radioButtonEasy;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHard;

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.seekBarBrightness= (SeekBar)this.findViewById(R.id.seekBar_brightness);
        this.seekBarSound= (SeekBar)this.findViewById(R.id.seekBar_sound);

        this.seekBarBrightness.setMax(100);
        this.seekBarSound.setMax(100);

        this.radioGroupDiffLevel= (RadioGroup) this.findViewById(R.id.radioGroup_diffLevel);
        this.radioButtonEasy=(RadioButton) this.findViewById(R.id.radioButton_easy);

        this.radioButtonMedium = (RadioButton)this.findViewById(R.id.radioButton_medium);
        this.radioButtonHard=(RadioButton) this.findViewById(R.id.radioButton_hard);

        this.buttonSave = (Button) this.findViewById(R.id.button_save);

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.doSave(view);
            }
        });

    // Load saved game setting.
        this.loadGameSetting();
}
    private void loadGameSetting()  {
        SharedPreferences sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);

        if(sharedPreferences!= null) {

            int brightness = sharedPreferences.getInt("brightness", 90);
            int sound = sharedPreferences.getInt("sound",95);
            int checkedRadioButtonId = sharedPreferences.getInt("checkedRadioButtonId", R.id.radioButton_medium);

            this.seekBarSound.setProgress(sound);
            this.seekBarBrightness.setProgress(brightness);
            this.radioGroupDiffLevel.check(checkedRadioButtonId);
            //Toast.makeText(this,sound,Toast.LENGTH_LONG).show();

        } else {
            this.radioGroupDiffLevel.check(R.id.radioButton_medium);
            Toast.makeText(this,"Use the default game setting",Toast.LENGTH_LONG).show();
        }

    }
    // Called when user click to Save button.
    public void doSave(View view)  {
        // The created file can only be accessed by the calling application
        // (or all applications sharing the same user ID).
        SharedPreferences sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("brightness", this.seekBarBrightness.getProgress());
        editor.putInt("sound", this.seekBarSound.getProgress());

        // Checked RadioButton ID.
        int checkedRadioButtonId = radioGroupDiffLevel.getCheckedRadioButtonId();

        editor.putInt("checkedRadioButtonId", checkedRadioButtonId);

        // Save.
        editor.apply();

        Toast.makeText(this,"Game Setting saved!",Toast.LENGTH_LONG).show();
    }
}



