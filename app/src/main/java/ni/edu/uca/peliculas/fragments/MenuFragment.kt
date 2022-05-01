package ni.edu.uca.peliculas.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import ni.edu.uca.peliculas.R
import ni.edu.uca.peliculas.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){

            btnIdioma.setOnClickListener {
                it.findNavController().navigate(R.id.menu_a_listaidm)
            }

            btnNacionalidad.setOnClickListener {
                it.findNavController().navigate(R.id.menu_a_listanac)
            }

        }
    }
}