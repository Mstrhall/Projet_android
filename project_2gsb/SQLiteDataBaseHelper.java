package com.example.project_2gsb;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
@celianlaumond

*/
/**creation de champs des table */
public class SQLiteDataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="GSB2.db";
    public static final String TABLE_NAME="Profesionels";
    public static final String COL_1="ID";
    public static final String COL_2="NOM";
    public static final String COL_3="PRENOM";
    public static final String COL_4="LETYPE";
    public static final String COL_5="ADRESSE";
    public static final String COL_6="MAIL";
    public static final String COL_7="TEL";
    public static final String TABLE_NAME2="Rdvs";
    public static final String COL_21="ID";
    public static final String COl_22="DATERDV";
    public static final String COL_23="HEURE";
    public static final String COL_24="NOM";

    public SQLiteDataBaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);

    }
    /**creation des tables  grace au champs precedens */
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE table "+TABLE_NAME+"(NOM TEXT PRIMARY KEY , PRENOM TEXT, LETYPE TEXT, ADRESSE TEXT, MAIL TEXT, TEL TEXT)");
        db.execSQL("CREATE table "+TABLE_NAME2+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATERDV TEXT, HEURE TEXT,NOM TEXT, " +
                "FOREIGN KEY(NOM) REFERENCES "+TABLE_NAME+"(NOM) )");
    }
    /** on ajoute a la bd en verifiant si elle existe */
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);
    }
    /** insertion dans la bd a partir des champ saisie en parametre
     *
     * @param nom , prenom , type , adresse , mail , tel :
     *
     * @return
     */
    public void insertData(String nom, String prenom,String type,String adresse,String mail ,String tel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2, nom);
        cv.put(COL_3, prenom);
        cv.put(COL_4, type);
        cv.put(COL_5, adresse);
        cv.put(COL_6, mail);
        cv.put(COL_7, tel);
        db.insert(TABLE_NAME, null, cv);
        db.close();


    }
    /**
     * parametre: datecourante, heure , nomProf
     * insertion dans la bd a partir des champ saisie en parametre */
    public void insertRDV(String Datecourante,String heure,String nomProf ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COl_22,Datecourante);
        cv.put(COL_23, heure);
        cv.put(COL_24,nomProf);
        db.insert(TABLE_NAME2,null,cv);
        db.close();
    }
    /**recuperation de toute la base de données
     * rentourne le resutlat de la requete dans uen variable result  */
    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return result;
    }

    /**recuperation de l'emploie du temps
     *
     * @param jour
     * @return retourne le resultat de la requete
     */
    public Cursor getEDT(String jour){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM "+TABLE_NAME2+" WHERE DATERDV='"+jour+"'",null);
        return result;
    }

    /**retourne le nombre de rdv
     *
     * @param jour
     * @return r 
     */
    public int countRDV(String jour){
        SQLiteDatabase db=this.getReadableDatabase();
         Cursor result=db.rawQuery("SELECT COUNT(ID) FROM "+TABLE_NAME2+" WHERE Datecourante="+jour,null);
         result.moveToFirst();
         int r=result.getInt(0);
        return r;
    }

    /**recuperation de la ville
     *
     * @param Vile
     * @param CP
     * @returnle resultat de la requete
     */
    public Cursor getVille(String Vile,String CP){
        Cursor result;
SQLiteDatabase db=this.getReadableDatabase();
if (Vile==""){
 result=db.rawQuery("SELECT NOM FROM "+TABLE_NAME+" WHERE ADRESSE LIKE '%"+CP+"%'",null);}
else {
    if(CP==""){
        result=db.rawQuery("SELECT NOM FROM "+TABLE_NAME+" WHERE ADRESSE LIKE '%"+Vile+"%'",null);
    }
    else{
        result=db.rawQuery("SELECT NOM FROM "+TABLE_NAME+" WHERE ADRESSE LIKE '%"+Vile+"%' OR ADRESSE LIKE '%"+CP+"%'",null);
    }
    }
   return result;


}}
