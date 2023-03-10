package com.example.project_2gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity5 extends AppCompatActivity {
    /** creation des variables qui vont etre utiliser sur cet ecran */
    SQLiteDataBaseHelper db;// vairables de la base de données
    Cursor result;// cursor qui va servir a l'insertion dans le spinner
    String[] rdv;// tableau pour le spinner
    Spinner spinner_rdv; // nom du spiner
    EditText  nomville;// edit text pour recupere la saisie
    EditText cp;// edit text pour recupere la saisie
    EditText test;
    int nb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        /**creation des appele avec la bd et liaison avec le XML */
        db=new SQLiteDataBaseHelper(this);
         nomville=(EditText)findViewById(R.id.ville_recherche);
         cp=(EditText)findViewById(R.id.codepostal_recherche);


    }
    /**creation de la fonction pour recupere dans la base de donnée le nom du pro en fonction de ca ville */
    public void cherche(View view ){
String ville=nomville.getText().toString();
String lecp = cp.getText().toString();
result=db.getVille(ville,lecp);
   afficherspinner();

    }
    /** fonction de mise a jour et d'impelementation du spinner */
    public void maj(){
        int i=0;
        int nb =result.getCount();
        rdv=new String[nb];
        while (result.moveToNext()){
            rdv[i]=result.getString(0);
            i++;
        }
    }
    /**
     * fonction d'affichage dans le spinner */
    public void afficherspinner()
    {/**appele de la fonction maj */
        maj();
        /**creation du spinner adapter pour que le spinner recoive le tableau */
        spinner_rdv=findViewById(R.id.spinner_rdv);
        ArrayAdapter spinnerArrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,rdv);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_rdv.setAdapter(spinnerArrayAdapter);
        spinner_rdv.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb1=i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );
    }
}