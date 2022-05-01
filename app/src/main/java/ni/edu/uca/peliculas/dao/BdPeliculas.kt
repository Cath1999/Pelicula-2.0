package ni.edu.uca.peliculas.dao


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ni.edu.uca.peliculas.entidades.Idioma
import ni.edu.uca.peliculas.entidades.Nacionalidad

interface MainDataBaseProvider{
    fun  idiomaDao (): IdiomaDao
    fun nacionalidadDao(): NacionalidadDao
}
@Database(
    entities = [Idioma::class, Nacionalidad::class],version =2)
abstract class MainDataBase: RoomDatabase(), MainDataBaseProvider {
    abstract override fun idiomaDao(): IdiomaDao
    abstract override fun nacionalidadDao():NacionalidadDao


    companion object {
        @Volatile
        private var INSTANCE: MainDataBase? = null
        fun getDataBase(context: Context): MainDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDataBase::class.java,
                        "main_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}