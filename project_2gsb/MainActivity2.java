package com.example.project_2gsb;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity2 extends AppCompatActivity {
    SQLiteDataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /**
         * creation de la variable qui correspond a la base de données */
        db=new SQLiteDataBaseHelper(this);

    }/**
     methode qui est lier au xml  */
    public void insererClick(View view) {
        /** recuperation des données saisie dans les text box et affectation dans une variable  */
        EditText nomS=(EditText)findViewById(R.id.label_nom_pro);
        EditText prenomS=(EditText)findViewById(R.id.label_prenom_pro);
        EditText typeS=(EditText)findViewById(R.id.label_type_pro);
        EditText adresseS=(EditText)findViewById(R.id.label_adresse_pro);
        EditText mailS=(EditText)findViewById(R.id.label_mail_pro);
        EditText telS=(EditText)findViewById(R.id.label_telephonne_pro);
        /** recuperation de la variable de la base de données et appel de la methode insert de la clase base de données
         * et insertion des edit text saisie en parametre de la fonction pour l'impelementation de la BD en sql enbarquer
         */
        db.insertData(nomS.getText().toString(),prenomS.getText().toString(),typeS.getText().toString(),adresseS.getText().toString(),mailS.getText().toString(),telS.getText().toString());

    }
}