package ClasesBase;

public class Portatil {

    private int idPortatil;
    private boolean prestado;
    private String marca;
    private int pulgadas;
    private String especificaciones;
    private String tipo;

    /**
     * Abstracción de un portátil. Se le deben dar valores con el builder.
     */
    public Portatil() {
    }

    public int getIdPortatil() {
        return idPortatil;
    }

    public void setIdPortatil(int idPortatil) {
        this.idPortatil = idPortatil;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    @Override
    public String toString() {
        return "Portatil{" + "idPortatil=" + idPortatil + ", prestado=" + prestado + ", marca=" + marca + ", pulgadas=" + pulgadas + ", especificaciones=" + especificaciones + '}';
    }

}
