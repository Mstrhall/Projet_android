package com.example.project_2gsb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    /**definition des variables qui vont etre utiliser dans cet ecran */
    CalendarView calendarView;// variable qui recupere le calendrier
    String curDAte;// variable qui concatene la date et l'heure
    SQLiteDataBaseHelper db;// variable de la base de données
    TextView texte;// variable qui resoit tout ce qui et rendu par la fonction bd
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        /**liason avec le xml et appel de bd  */
         calendarView=findViewById(R.id.calendarView);
         texte = findViewById(R.id.textView10);
         db=new SQLiteDataBaseHelper(this);
         calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             /**appele de la methode qui permet de recupere la date courante */
             public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                curDAte=String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1);

             }

         });

    }
    /** methode d'insertion dans la bd */
    public void insertBD(View view){
        /**recuperation des texte saisie */
        EditText heurS=(EditText)findViewById(R.id.label_rdv_heure);
        EditText nomS=(EditText) findViewById(R.id.label_nom_rdv);
        /**appel de la methode d'insertion avec les parametre pour insert un rdv */
        db.insertRDV(curDAte,heurS.getText().toString(),nomS.getText().toString());
        texte.setText(curDAte+" "+ heurS.getText().toString()+" "+nomS.getText().toString());
    }
}