package br.com.williandrade.imclive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.williandrade.imclive.databinding.ActivityMainBinding
import br.com.williandrade.imclive.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mainActivityViewModel : MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Associa a activity com a ViewModel criada
        mainActivityViewModel = ViewModelProvider
            .NewInstanceFactory()
            .create(MainActivityViewModel::class.java)

        /**** Listeners - Entradas ****/
        //Slider Peso
        binding.sliderPeso.addOnChangeListener{_, value, _ ->
            mainActivityViewModel.peso.value = value.toDouble()
        }

        //Slider Altura
        binding.sliderAltura.addOnChangeListener { _, value, _ ->
            mainActivityViewModel.altura.value = value.toDouble()
        }

        /**** Watchers - Retornos ****/
        //IMC
        mainActivityViewModel.imc.observe(this) {
            binding.tvImcAtual.text = if (it > 0 && it.isFinite()) "%.2f".format(it) else "-/-"
        }

        //Obervação
        mainActivityViewModel.classificacao.observe(this){
            binding.tvObsImcAtual.text = "Classificação: $it"
        }

        //Peso
        mainActivityViewModel.peso.observe(this){
            binding.tvPeso.text = "%.1f KG".format(it)
        }

        //Altura
        mainActivityViewModel.altura.observe(this){
            binding.tvAltura.text = "%.0f cm".format(it)
        }

    }

}