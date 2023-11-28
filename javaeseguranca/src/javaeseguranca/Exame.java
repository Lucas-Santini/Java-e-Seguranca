package javaeseguranca;

public class Exame {
    private String potassio;
    private String tipoDado;

    public Exame(String potassio, String tipoDado) {
        this.potassio = potassio;
        this.tipoDado = tipoDado;
    }

    public String getPotassio() {
        return potassio;
    }

    public void setPotassio(String potassio) {
        this.potassio = potassio;
    }

    public String getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(String tipoDado) {
        this.tipoDado = tipoDado;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "potassio='" + potassio + '\'' +
                ", tipoDado='" + tipoDado + '\'' +
                '}';
    }
}
