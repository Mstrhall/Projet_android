package com.example.project_2gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SQLiteDataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/**
* creation d'une variable de la base de données correpondant a ma classe
 * liasion avec le premier bouton dans le XML
* */
        db=new SQLiteDataBaseHelper(this);

    }
    /**
     * creation de la premier methode qui vise a appeler l'ecran 2 pour enregistrer un pro
     * liasion avec le second bouton dans le XML */
    public void GOenregistrer(View view){
        Intent  intentAfficher = new Intent(this, MainActivity2.class);
        startActivity(intentAfficher);

    }  /**
     creation de la seconde methode qui vise a appeler l'ecran 3 pour enregistrer un RDV
     liaison avec le troisieme bouton dans le XML  */
    public void GOprendreRDV(View view){
        Intent  intentAfficher = new Intent(this, MainActivity3.class);
        startActivity(intentAfficher);

    }
/**
 creation de la troisieme methode quqi vise a appeler l'ecran 4 pour voir les rdv
 liaison avec le quatrieme bouton dans le XML */
    public void Goprofes(View view)
    {
        Intent intentAfficher = new Intent(this, MainActivity5.class);
        startActivity(intentAfficher);
    }
    /**
     * creation de la quatrieme methode qui vise a appeler l'ecran 5 pour voir les profesionel sur une zone
     * liaison avec le 5 ieme bouton dans le XML */
    public void GOemploidutemp(View view)
    {
        Intent  intentAfficher = new Intent(this, MainActivity4.class);
        startActivity(intentAfficher);
    }

}