package ni.edu.uca.peliculas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.peliculas.databinding.ListaIdiomaBinding
import ni.edu.uca.peliculas.entidades.Idioma
import ni.edu.uca.peliculas.fragments.lista.ListaIdiomaFragmentDirections

class IdiomaAdapter: RecyclerView.Adapter<IdiomaAdapter.IdiomaHolder>(){
    var listaIdm:List<Idioma> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdiomaHolder {
        val binding = ListaIdiomaBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return IdiomaHolder(binding)
    }

    override fun onBindViewHolder(holder: IdiomaAdapter.IdiomaHolder, position: Int) : Unit =
        holder.bind(listaIdm[position])

    override fun getItemCount(): Int =listaIdm.size

    fun setData(idm: List<Idioma>) {
        this.listaIdm = idm
        notifyDataSetChanged()
    }


    inner class IdiomaHolder(val binding: ListaIdiomaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(idm: Idioma){
            with(binding){
                idIdioma.text = idm.id_Idioma.toString()
                nombreIdioma.text = idm.nombre

                IdiomaFila.setOnClickListener{
                    val action= ListaIdiomaFragmentDirections.actualizarIdioma(idm)
                    it.findNavController().navigate(action)
                }
            }

        }
    }
}