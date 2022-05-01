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
import ni.edu.uca.peliculas.databinding.FragmentNuevaNacionalidadBinding
import ni.edu.uca.peliculas.entidades.Nacionalidad
import ni.edu.uca.peliculas.viewmodels.NacionalidadViewModels

class NuevaNacionalidadFragment : Fragment() {
    lateinit var fBinding: FragmentNuevaNacionalidadBinding
    private lateinit var viewModel: NacionalidadViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fBinding =
            FragmentNuevaNacionalidadBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(NacionalidadViewModels::class.java)
        fBinding.BtnGuardarNacionalidad.setOnClickListener {
            guardarRegistro()
        }
        return fBinding.root
    }
    private fun guardarRegistro() {
        //val baseDatos = MainBaseDatos.getDataBase(this)
        val nomb = fBinding.txtnombreNacionalidad.text.toString()

        if (nomb.isNotEmpty())
        {
            //Crear objeto
            val nac = Nacionalidad(0,true ,nomb)

            //Agregar nueva nacionalidad
            viewModel.agregarNacionalidad(nac)
            Toast.makeText(requireContext(), "Registro guardado",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.devolver_nuevanac)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }


    }
}

