package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Registo implements Serializable {
    private double frequenciaCardiaca;
    private double temperatura;
    private double saturacaoOxigenio;
    private Date dataHora;

    public Registo(double frequenciaCardiaca, double temperatura, double saturacaoOxigenio, Date dataHora) {
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.temperatura = temperatura;
        this.saturacaoOxigenio = saturacaoOxigenio;
        this.dataHora = dataHora;
    }

    // Getters
    public double getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getSaturacaoOxigenio() {
        return saturacaoOxigenio;
    }

    public Date getDataHora() {
        return dataHora;
    }

    // Setters
    public void setFrequenciaCardiaca(double frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public void setSaturacaoOxigenio(double saturacaoOxigenio) {
        this.saturacaoOxigenio = saturacaoOxigenio;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return String.format(
                "Registo [FC: %.1f bpm, Temp: %.1f ºC, SpO2: %.1f%%, DataHora: %s]",
                frequenciaCardiaca, temperatura, saturacaoOxigenio, sdf.format(dataHora)
        );
    }
}
