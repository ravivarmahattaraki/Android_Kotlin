package com.example.android_kotlin.DesignPattern.CreationalPatterns.Builder;

import android.app.Notification;
import android.content.Context;

public class JavaCarBuilder {
    private int carModel;
    private String brand;
    private String color;

    JavaCarBuilder(Builder builder){
        this.carModel = builder.carModel;
        this.brand = builder.brand;
        this.color = builder.color;
    }
    public static class Builder{
        private int carModel;
        private String brand;
        private String color;
        public Builder(){}

        public Builder setCarModel(int carModel){
            this.carModel = carModel;
            return this;
        }

        public Builder setBrand(String brand){
            this.brand = brand;
            return this;
        }

        public Builder setColor(String color){
            this.color = color;
            return this;
        }

        public JavaCarBuilder build(){
            return new JavaCarBuilder(this);
        }
    }

    @Override
    public String toString() {
        return "JavaCarBuilder{" +
                "Hashcode='"+this.hashCode() + '\''+
                ", carModel='" + carModel+ '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
