package com.dmitriyevseyev.carmanager2.view;

import com.dmitriyevseyev.carmanager2.model.Car;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.util.Date;
import java.util.Objects;

public class CarFx {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty date;
    private SimpleStringProperty color;
    private SimpleBooleanProperty isAfterCrash;
    private CheckBox checkBox;

    public CarFx() {
    }

    public CarFx(Integer id, String name, String date, String color, Boolean isAfterCrash) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.color = new SimpleStringProperty(color);
        this.isAfterCrash = new SimpleBooleanProperty(isAfterCrash);
        this.checkBox = new CheckBox();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public boolean isIsAfterCrash() {
        return isAfterCrash.get();
    }

    public SimpleBooleanProperty isAfterCrashProperty() {
        return isAfterCrash;
    }

    public void setIsAfterCrash(boolean isAfterCrash) {
        this.isAfterCrash.set(isAfterCrash);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarFx carFx = (CarFx) o;
        return Objects.equals(id, carFx.id) &&
                Objects.equals(name, carFx.name) &&
                Objects.equals(date, carFx.date) &&
                Objects.equals(color, carFx.color) &&
                Objects.equals(isAfterCrash, carFx.isAfterCrash) &&
                Objects.equals(checkBox, carFx.checkBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, color, isAfterCrash, checkBox);
    }

    @Override
    public String toString() {
        return "CarFx{" +
                "id=" + id +
                ", name=" + name +
                ", date=" + date +
                ", color=" + color +
                ", isAfterCrash=" + isAfterCrash +
                ", checkBox=" + checkBox +
                '}';
    }

    public static Builder builder() {
        return new CarFx.Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String date;
        private String color;
        private boolean isAfterCrash;
        private CheckBox checkBox;

        Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;

            // why we return this? it's chain pattern. you can read more about it
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder date(String date) {
            this.date = date;

            return this;
        }

        public Builder color(String color) {
            this.color = color;

            return this;
        }

        public Builder isAfterCrash(boolean isAfterCrash) {
            this.isAfterCrash = isAfterCrash;

            return this;
        }

        public Builder checkBox(CheckBox checkBox) {
            this.checkBox = checkBox;

            return this;
        }

        public CarFx build() {
            return new CarFx(
                    id,
                    name,
                    date,
                    color,
                    isAfterCrash
            );
        }
    }
}

