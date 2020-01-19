/**
 * El patrón adapter adapta una clase a otra compatible. 
 */

/* En el ejemplo, convertimos una fecha en estilo US a una fecha en estilo EU */

/**
 * Nuestra fechaUS tiene day, month, year.
 */
public class FechaUS {
    private int day, month, year;

    public FechaUS(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

/**
 * Tenemos una interfaz de cómo debe ser una fecha EU.
 */
public interface FechaEU {
    public int getDia();

    public int getMes();

    public int getAno();

    public void setDia(int dia);

    public void setMes(int mes);

    public void setAno(int ano);
}

/**
 * El adaptador recibe una fechaUS, y se comporta como una FechaEU.
 */

public class AdaptadorFecha implements FechaEU {
    FechaUS fechaUs;

    public AdaptadorFecha(FechaUS fechaUs) {
        this.fechaUs = fechaUs;
    }

    @Override
    public int getDia() {
        return fechaUs.getDay();
    }

    @Override
    public int getMes() {
        return fechaUs.getMonth();
    }

    @Override
    public int getAno() {
        return fechaUs.getYear();
    }

    @Override
    public void setDia(int dia) {
        fechaUs.setDay(dia);
    }

    @Override
    public void setMes(int mes) {
        fechaUs.setMonth(mes);
    }

    @Override
    public void setAno(int ano) {
        fechaUs.setYear(ano);
    }
}