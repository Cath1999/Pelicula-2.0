package ni.edu.uca.peliculas.fragments.actualizar


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ni.edu.uca.peliculas.R
import ni.edu.uca.peliculas.databinding.FragmentActualizarIdiomaBinding
import ni.edu.uca.peliculas.entidades.Idioma
import ni.edu.uca.peliculas.viewmodels.IdiomaViewModels


class ActualizarIdiomaFragment : Fragment() {

    lateinit var fBinding: FragmentActualizarIdiomaBinding
    private val args by navArgs<ActualizarIdiomaFragmentArgs>()
    private lateinit var viewModel: IdiomaViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fBinding =
            FragmentActualizarIdiomaBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(IdiomaViewModels::class.java)
        with(fBinding) {
            txtNombreIdioma.setText(args.currentIdioma.nombre)
            BtnActualizarIdioma.setOnClickListener {
                GuardarCambios()
            }
        }
        //Agregar menu
        setHasOptionsMenu(true)
        return fBinding.root
    }
    private fun GuardarCambios() {
        val nomb = fBinding.txtNombreIdioma.text.toString()

        if(nomb.isNotEmpty() && nomb.isNotEmpty())
        {
            //Crear el objeto
            val idm =
                Idioma(args.currentIdioma.id_Idioma, true, nomb)
            //Actualizar
            viewModel.actualizarIdioma(idm)
            Toast.makeText(requireContext(), "Registro actualizado",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.devolver_acId)
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
            eliminarIdioma()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun eliminarIdioma() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarIdioma(args.currentIdioma)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.devolver_acId)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando ${args.currentIdioma.nombre}")
        alerta.setMessage("¿Esta seguro de eliminar a ${args.currentIdioma.nombre}?")
        alerta.create().show()
    }

}