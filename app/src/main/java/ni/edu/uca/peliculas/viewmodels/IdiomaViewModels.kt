package ni.edu.uca.peliculas.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.peliculas.dao.MainDataBase
import ni.edu.uca.peliculas.entidades.Idioma
import ni.edu.uca.peliculas.repository.IdiomaRepository

class IdiomaViewModels (application: Application): AndroidViewModel(application) {
    val lista : LiveData<List<Idioma>>
    private val repository: IdiomaRepository
    init {
        val idiomaDao =
            MainDataBase.getDataBase(application).idiomaDao()
        repository = IdiomaRepository(idiomaDao)
        lista = repository.listado
    }
    fun agregarIdioma(idioma: Idioma){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIdioma(idioma)
        }
    }
    fun actualizarIdioma(idioma: Idioma){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateIdioma(idioma)
        }
    }
    fun eliminarIdioma(idioma: Idioma){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIdioma(idioma)
        }
    }
    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}