package ni.edu.uca.peliculas.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.peliculas.dao.MainDataBase
import ni.edu.uca.peliculas.entidades.Nacionalidad

import ni.edu.uca.peliculas.repository.IdiomaRepository
import ni.edu.uca.peliculas.repository.NacionalidadRepository

class NacionalidadViewModels (application: Application): AndroidViewModel(application) {
    val lista : LiveData<List<Nacionalidad>>
    private val repository: NacionalidadRepository
    init {
        val nacionalidadDao =
            MainDataBase.getDataBase(application).nacionalidadDao()
        repository = NacionalidadRepository(nacionalidadDao)
        lista = repository.listado
    }
    fun agregarNacionalidad(nacionalidad : Nacionalidad){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNacionalidad(nacionalidad)
        }
    }
    fun actualizarNacionalidad(nacionalidad : Nacionalidad){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNacionalidad(nacionalidad)
        }
    }
    fun eliminarNacionalidad(nacionalidad: Nacionalidad){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNacionalidad(nacionalidad)
        }
    }
    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}