package ni.edu.uca.peliculas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.peliculas.databinding.ListaNacionalidadBinding
import ni.edu.uca.peliculas.entidades.Nacionalidad
import ni.edu.uca.peliculas.fragments.lista.ListaNacionalidadFragmentDirections

class NacionalidadAdapter: RecyclerView.Adapter<NacionalidadAdapter.NacionalidadHolder>(){
    var listaNac:List<Nacionalidad> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NacionalidadHolder {
        val binding = ListaNacionalidadBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return NacionalidadHolder(binding)
    }

    override fun onBindViewHolder(holder: NacionalidadAdapter.NacionalidadHolder, position: Int) : Unit =
        holder.bind(listaNac[position])

    override fun getItemCount(): Int =listaNac.size

    fun setData(nac: List<Nacionalidad>) {
        this.listaNac = nac
        notifyDataSetChanged()
    }


    inner class NacionalidadHolder(val binding: ListaNacionalidadBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nac: Nacionalidad){
            with(binding){
                idNacionalidad.text = nac.id_Nacionalidad.toString()
                nombreNacionalidad.text = nac.nombre

                NacionalidadFila.setOnClickListener{
                    val action= ListaNacionalidadFragmentDirections.actualizarNacionalidad(nac)
                    it.findNavController().navigate(action)
                }
            }

        }
    }
}