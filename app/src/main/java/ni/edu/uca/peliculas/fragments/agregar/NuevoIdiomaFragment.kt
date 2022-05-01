package ni.edu.uca.peliculas.fragments.agregar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ni.edu.uca.peliculas.R
import ni.edu.uca.peliculas.databinding.FragmentNuevoIdiomaBinding
import ni.edu.uca.peliculas.entidades.Idioma
import ni.edu.uca.peliculas.viewmodels.IdiomaViewModels

class NuevoIdiomaFragment : Fragment() {
    lateinit var fBinding: FragmentNuevoIdiomaBinding
    private lateinit var viewModel: IdiomaViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fBinding =
            FragmentNuevoIdiomaBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(IdiomaViewModels::class.java)
        fBinding.BtnGuardarIdioma.setOnClickListener {
            guardarRegistro()
        }
        return fBinding.root
    }
    private fun guardarRegistro() {
        //val baseDatos = MainBaseDatos.getDataBase(this)
        val nomb = fBinding.txtNombreIdioma.text.toString()

        if (nomb.isNotEmpty())
        {
            //Crear objeto
            val idi = Idioma(0,true ,nomb)

            //Agregar nuevo idioma
            viewModel.agregarIdioma(idi)
            Toast.makeText(requireContext(), "Registro guardado",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.devolver_nuevoid)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }


    }
}

