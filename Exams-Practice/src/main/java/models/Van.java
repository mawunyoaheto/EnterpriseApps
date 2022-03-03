package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "van")
@DiscriminatorValue("VAN")
public class Van extends  Vehicle{
    private Color color;

    public Van() {
    }

    public Van(int year, Color color,Factory factory) {
        super(year,factory);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Van{" +
                "color=" + color +
                '}';
    }
}