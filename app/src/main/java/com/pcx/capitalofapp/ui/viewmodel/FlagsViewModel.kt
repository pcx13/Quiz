package com.pcx.capitalofapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.pcx.capitalofapp.data.DatabaseHelper
import com.pcx.capitalofapp.data.Quiz

class FlagsViewModel:ViewModel() {

    private lateinit var suallar: ArrayList<Quiz>
    private lateinit var sehvVariant: ArrayList<Quiz>
    private lateinit var dogruSual: Quiz
    private lateinit var butunVariant: HashSet<Quiz>
    private lateinit var dbs: DatabaseHelper

    private var sualSaygac = 0
    private var dogruSaygac = 0
    private var sehvSaygac = 0


    private fun sualYukle(){
        dogruSual=suallar[sualSaygac]


    }
}