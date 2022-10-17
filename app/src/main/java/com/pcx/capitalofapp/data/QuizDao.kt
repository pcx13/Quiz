package com.pcx.capitalofapp.data

class QuizDao {

    fun getRandom10Flag(dbh: DatabaseHelper): ArrayList<Quiz> {
        val quizList = ArrayList<Quiz>()
        val db = dbh.writableDatabase
        val c = db.rawQuery("SELECT * FROM countries ORDER BY RANDOM() LIMIT 10", null)

        while (c.moveToNext()) {
            val quizzes = Quiz(
                c.getInt(c.getColumnIndexOrThrow("id")),
                c.getString(c.getColumnIndexOrThrow("name")),
                c.getString(c.getColumnIndexOrThrow("flag")),
                c.getString(c.getColumnIndexOrThrow("capital"))
            )
            quizList.add(quizzes)
        }
        c.close()
        return quizList
    }

    fun getRandom3WrongAnswer(dbh: DatabaseHelper, id: Int): ArrayList<Quiz> {
        val quizList = ArrayList<Quiz>()
        val db = dbh.writableDatabase
        val c =
            db.rawQuery("SELECT * FROM countries WHERE id != $id ORDER BY RANDOM() LIMIT 3", null)

        while (c.moveToNext()) {
            val quizzes = Quiz(
                c.getInt(c.getColumnIndexOrThrow("id")),
                c.getString(c.getColumnIndexOrThrow("name")),
                c.getString(c.getColumnIndexOrThrow("flag")),
                c.getString(c.getColumnIndexOrThrow("capital"))
            )
            quizList.add(quizzes)
        }
        c.close()
        return quizList
    }
}