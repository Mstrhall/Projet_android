package com.example.project_2gsb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    /**
     * creation des variables qui vont etre utiliser dans cette page  */
    CalendarView calendarView;// variable qui va recevoir le calendrier
    String curDAte;// variable qui recoit la variable courante
    SQLiteDataBaseHelper db;// variable de la base de données
    String[] rdv;// tableau pour le spinner
    int nb1;// index pour le spinner
   EditText edit;// edit texte
    Spinner spinner_planing;// spinner qui vas afficher les element
    EditText infoS;// edit texte qui afifchera le resultat de la requete

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        /** liaison avec le XML et appel de la BD et vidage de la variable curDate */
        curDAte="";
        db=new SQLiteDataBaseHelper(this);
        calendarView=findViewById(R.id.calendarView2);
        edit = (EditText) findViewById(R.id.editTextInfo);
        infoS =findViewById(R.id.editTextInfo);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            /**appele de la focntion qui recupere et eplemente la date courante */
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                curDAte=String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1);

            }

    });/**appel de la fonction de misea jour */
        maj();

      /*   spinner_planing= findViewById(R.id.spinner_planing);

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, rdv);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_planing.setAdapter(spinnerArrayAdapter);
        spinner_planing.setOnItemSelectedListener(new OnItemSelectedListener() {
                                                      @Override
                                                      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                          nb1=i;
                                                      }

                                                      @Override
                                                      public void onNothingSelected(AdapterView<?> adapterView) {

                                                      }
                                                  }


        );
*/




    }
    /**fonction maj appeler precedement */
    public  void maj()
    {


/** creation d'un curseur qui recupere l'emploie du temp de la date courante */
        Cursor Data= db.getEDT(curDAte);
        int i=0;
        int nb =Data.getCount();
        infoS.setText("nb "+nb);// insertion dans une text box
        rdv = new String[nb];// implementation du tableau rdv
        while (Data.moveToNext()){
            // boucle qui va jusqua la derniere partie du cursor
            rdv[i]=Data.getString(2)+ " "+ Data.getString(3);// rdv recoit les information de la requete
            i++;
            
        }

    }
    /**fonction qui implemente la spinner */
        public void recupHeure(View view){

/**appel de la fonction maj */
maj();
/** liason avec le xml */
            spinner_planing= findViewById(R.id.spinner_planing);
/**creation du spinner adapter pour que le spinner recoive le tableau */
            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, rdv);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_planing.setAdapter(spinnerArrayAdapter);// recoit les valeur de la fonction
            spinner_planing.setOnItemSelectedListener(new OnItemSelectedListener() {
                                                          @Override
                                                          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                              nb1=i;
                                                          }

                                                          @Override
                                                          public void onNothingSelected(AdapterView<?> adapterView) {

                                                          }
                                                      }


            );


            infoS.setText(rdv[0]);
    }
}