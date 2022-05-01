package ni.edu.uca.peliculas.fragments.lista
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_nueva_nacionalidad.*
import kotlinx.android.synthetic.main.fragment_nuevo_idioma.*
import ni.edu.uca.peliculas.R
import ni.edu.uca.peliculas.adapters.IdiomaAdapter
import ni.edu.uca.peliculas.adapters.NacionalidadAdapter
import ni.edu.uca.peliculas.databinding.FragmentListaIdiomaBinding
import ni.edu.uca.peliculas.databinding.FragmentListaNacionalidadBinding
import ni.edu.uca.peliculas.viewmodels.IdiomaViewModels
import ni.edu.uca.peliculas.viewmodels.NacionalidadViewModels

class ListaIdiomaFragment : Fragment() {
    lateinit var fBinding: FragmentListaIdiomaBinding
    private lateinit var viewModel : IdiomaViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fBinding = FragmentListaIdiomaBinding.inflate(layoutInflater)
        val adapter = IdiomaAdapter()
        val recycleView = fBinding.RcvListaIdioma
        recycleView.adapter = adapter
        recycleView.layoutManager =
            LinearLayoutManager(requireContext())
        viewModel =
            ViewModelProvider(this).get(IdiomaViewModels::class.java)
        viewModel.lista.observe(viewLifecycleOwner, Observer
        {idm ->
            adapter.setData(idm)
        })
        //Agregar el menu
        setHasOptionsMenu(true)
        return fBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState:
    Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }
    private fun setupViews() {
        with(fBinding) {
            BtnAgregarIdioma.setOnClickListener {

                it.findNavController().navigate(R.id.agregar_idioma)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater:
    MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (item.itemId == R.id.delete_item) {
            eliminarTodo()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun eliminarTodo() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarTodo()
            Toast.makeText(
                requireContext(),
                "Registros eliminados satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando todos los registro")
        alerta.setMessage("¿Esta seguro de eliminar los registros?")
        alerta.create().show()
    }

}