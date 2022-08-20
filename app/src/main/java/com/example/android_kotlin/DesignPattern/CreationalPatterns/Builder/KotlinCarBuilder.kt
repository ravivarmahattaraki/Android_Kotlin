package com.example.android_kotlin.DesignPattern.CreationalPatterns.Builder

import android.graphics.Color

class KotlinCarBuilder(var carModel: Int,
                       var carBrand: String,
                       var carColor: String) {

    class Builder {
        private var carModel = 0
        private var carBrand = ""
        private var carColor = ""
        public fun setCarModel(carModel: Int): Builder {
            this.carModel = carModel
            return this
        }

        public fun setCarBrand(carBrand: String): Builder {
            this.carBrand = carBrand
            return this
        }

        public fun setCarColor(color: String): Builder {
            this.carColor = color
            return this
        }

        public fun build(): KotlinCarBuilder {
            return KotlinCarBuilder(this.carModel, this.carBrand, this.carColor)
        }
    }

    override fun toString(): String {
        super.toString()
        return "KotlinCarBuilder : {Hashcode = '${this.hashCode()}', " +
                "CarModel = '$carModel', " +
                "CarBrand = '$carBrand', " +
                "CarColor = '$carColor'}"


    }
}