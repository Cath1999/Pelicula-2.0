package ni.edu.uca.peliculas.repository

import androidx.lifecycle.LiveData
import ni.edu.uca.peliculas.dao.IdiomaDao
import ni.edu.uca.peliculas.entidades.Idioma

class IdiomaRepository (private val dao: IdiomaDao) {
    val listado : LiveData<List<Idioma>> =
        dao.getAllRealData()
    suspend fun addIdioma(idioma: Idioma){
        dao.insert(idioma)
    }
    suspend fun updateIdioma(idioma: Idioma){
        dao.update(idioma)
    }
    suspend fun deleteIdioma(idioma: Idioma){
        dao.delete(idioma)
    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }
}
