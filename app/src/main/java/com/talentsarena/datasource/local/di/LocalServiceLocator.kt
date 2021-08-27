package com.talentsarena.datasource.local.di

import android.content.Context
import com.talentsarena.datasource.local.DatabaseManager
import com.talentsarena.datasource.local.dao.MovieDao

/**
 * Service locator to generate [MovieDao] object.
 */
object LocalServiceLocator {

    fun getMovieDao(context: Context): MovieDao = DatabaseManager.getInstance(context).movieDao()
}