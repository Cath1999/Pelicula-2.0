package ni.edu.uca.peliculas.fragments.actualizar

import ni.edu.uca.peliculas.databinding.FragmentActualizarNacionalidadBinding
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ni.edu.uca.peliculas.R
import ni.edu.uca.peliculas.entidades.Nacionalidad
import ni.edu.uca.peliculas.viewmodels.NacionalidadViewModels

class ActualizarNacionalidadFragment : Fragment() {
    lateinit var fBinding: FragmentActualizarNacionalidadBinding
    private val args by navArgs<ActualizarNacionalidadFragmentArgs>()
    private lateinit var viewModel: NacionalidadViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fBinding =
            FragmentActualizarNacionalidadBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(NacionalidadViewModels::class.java)
        with(fBinding) {
            txtNombreNac.setText(args.currentNacionalidad.nombre)
            BtnActualizarNacionalidad.setOnClickListener {
                GuardarCambios()
            }
        }
        //Agregar menu
        setHasOptionsMenu(true)
        return fBinding.root
    }
    private fun GuardarCambios() {
        val nomb = fBinding.txtNombreNac.text.toString()

        if(nomb.isNotEmpty() && nomb.isNotEmpty())
        {
            //Crear el objeto
            val nac =
                Nacionalidad(args.currentNacionalidad.id_Nacionalidad, true, nomb)
            //Actualizar
            viewModel.actualizarNacionalidad(nac)
            Toast.makeText(requireContext(), "Registro actualizado",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.devolver_acNac)
        }
        else
        {
            Toast.makeText(requireContext(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater:
    MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (item.itemId == R.id.delete_item) {
            eliminarNacionalidad()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun eliminarNacionalidad() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarNacionalidad(args.currentNacionalidad)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.devolver_acNac)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando ${args.currentNacionalidad.nombre}")
        alerta.setMessage("¿Esta seguro de eliminar a ${args.currentNacionalidad.nombre}?")
        alerta.create().show()
    }

}