package ni.edu.uca.peliculas.dao

import ni.edu.uca.peliculas.entidades.Idioma


import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface IdiomaDao {

    //IDIOMA
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Idioma)

    @Query("SELECT * FROM Idioma")
    fun getAllRealData(): LiveData<List<Idioma>>

    @Query("Select * from Idioma")
    suspend fun getAllIdioma(): List<Idioma>

    @Query("SELECT * FROM Idioma where id_Idioma= :id")
    suspend fun getByIdIdioma(id: Int): Idioma

    @Update
    fun update(usuario: Idioma)

    @Delete
    fun delete(usuario: Idioma)


    @Query("Delete from Idioma")
    suspend fun deleteAll()


}