package br.com.williandrade.imclive.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class MainActivityViewModel () : ViewModel() {

    //Cria vaiáveis mutáveis
    val peso = MutableLiveData(0.0)
    val altura = MutableLiveData(0.0)
    val imc = MutableLiveData(0.0)
    val classificacao = MutableLiveData("")

    private fun calcularImc(){
        imc.value = peso.value!! / (altura.value!!/100).pow(2)
        classificacao.value =
            if (imc.value!!.isInfinite() || imc.value!!.isNaN()) "Não classificado" else
            if (imc.value!! < 18.5) "Magro" else
            if (imc.value!! < 24.9) "Normal" else
            if (imc.value!! < 29.9) "Sobrepeso" else
            if (imc.value!! < 39.9) "Obesidade" else
            "Obesidade Grave"
    }

    init{
        peso.observeForever(){
            calcularImc()
        }

        altura.observeForever(){
            calcularImc()
        }
    }
}