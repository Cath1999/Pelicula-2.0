package ni.edu.uca.peliculas.repository
import androidx.lifecycle.LiveData
import ni.edu.uca.peliculas.dao.NacionalidadDao
import ni.edu.uca.peliculas.entidades.Nacionalidad

class NacionalidadRepository (private val dao: NacionalidadDao) {
    val listado : LiveData<List<Nacionalidad>> =
        dao.getAllRealData()
    suspend fun addNacionalidad(nacionalidad: Nacionalidad){
        dao.insert(nacionalidad)
    }
    suspend fun updateNacionalidad(nacionalidad: Nacionalidad){
        dao.update(nacionalidad)
    }
    suspend fun deleteNacionalidad(nacionalidad: Nacionalidad){
        dao.delete(nacionalidad)
    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }
}
