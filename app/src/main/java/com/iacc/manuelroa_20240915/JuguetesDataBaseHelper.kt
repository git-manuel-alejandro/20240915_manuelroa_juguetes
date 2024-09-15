package com.iacc.manuelroa_20240915

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class JuguetesDataBaseHelper(context: Context) : SQLiteOpenHelper(
    context , DATA_BASE , null , DATA_VERSION
) {
    companion object {
        private const val DATA_BASE = "juguetes.db"
        private const val DATA_VERSION = 1
        private const val TABLE_NAME = "juguetes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_PRECIO = "precio"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY ,$COLUMN_TITULO TEXT, $COLUMN_PRECIO INTEGER)"
        db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }


    fun insertJuguete(juguete: Juguete){
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_TITULO , juguete.titulo)
            put(COLUMN_PRECIO , juguete.precio)

        }

        db.insert(TABLE_NAME , null , values)
        db.close()
    }

    fun getAllJuguetes(): List<Juguete>{
        val listaJuguetes = mutableListOf<Juguete>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query , null)

        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO))
            val precio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRECIO))

            val juguete = Juguete(id, titulo , precio )
            listaJuguetes.add(juguete)
        }

        cursor.close()
        db.close()

        return listaJuguetes

    }
}