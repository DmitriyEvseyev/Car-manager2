package com.dmitriyevseyev.carmanager2.shared.dto;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")

public class CarDTO {

    private Integer id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String color;
    private boolean isAfterCrash;

    public CarDTO() {
    }

    public CarDTO(Integer id, String name, LocalDate date, String color, boolean isAfterCrash) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.color = color;
        this.isAfterCrash = isAfterCrash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAfterCrash() {
        return isAfterCrash;
    }

    public void setAfterCrash(boolean afterCrash) {
        isAfterCrash = afterCrash;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.dmitriyevseyev.carmanager2.shared.Car car = (com.dmitriyevseyev.carmanager2.shared.Car) o;
        return isAfterCrash == car.isAfterCrash &&
                Objects.equals(id, car.id) &&
                Objects.equals(name, car.name) &&
                Objects.equals(date, car.date) &&
                Objects.equals(color, car.color);
    }

     */

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, color, isAfterCrash);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", color='" + color + '\'' +
                ", isAfterCrash=" + isAfterCrash +
                '}';
    }
/*

    public static com.dmitriyevseyev.carmanager2.shared.Car.Builder builder() {
        return new com.dmitriyevseyev.carmanager2.shared.Car.Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;
        private LocalDate date;
        private String color;
        private boolean isAfterCrash;

        Builder() {
        }

        public com.dmitriyevseyev.carmanager2.shared.Car.Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public com.dmitriyevseyev.carmanager2.shared.Car.Builder name(String name) {
            this.name = name;

            return this;
        }

        public com.dmitriyevseyev.carmanager2.shared.Car.Builder date(LocalDate date) {
            this.date = date;

            return this;
        }

        public com.dmitriyevseyev.carmanager2.shared.Car.Builder color(String color) {
            this.color = color;

            return this;
        }

        public com.dmitriyevseyev.carmanager2.shared.Car.Builder isAfterCrash(boolean isAfterCrash) {
            this.isAfterCrash = isAfterCrash;

            return this;
        }

        public com.dmitriyevseyev.carmanager2.shared.Car build() {
            return new com.dmitriyevseyev.carmanager2.shared.Car(
                    id,
                    name,
                    date,
                    color,
                    isAfterCrash
            );
        }
    }

 */
}

