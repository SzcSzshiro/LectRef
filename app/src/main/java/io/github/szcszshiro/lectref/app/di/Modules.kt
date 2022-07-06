package io.github.szcszshiro.lectref.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.szcszshiro.lectref.app.db.LectRefDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LectRefDBModule{
    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, LectRefDB::class.java, "lifeline_todo_db").build()

    @Provides
    fun provideLectureDao(db: LectRefDB) = db.getLectureDao()

    @Provides
    fun provideReferenceDao(db: LectRefDB) = db.getReferenceDao()

    @Provides
    fun provideTaskDao(db: LectRefDB) = db.getTaskDao()
}