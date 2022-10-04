package com.example.android_kotlin.DesignPattern.CreationalPatterns.Builder

import android.graphics.Color

class KotlinCarBuilder(private var carModel: Int, private  var carBrand: String, private var carColor: String) {

    /**Using kotlin nested class*/
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
    /**Using Kotlin companion object*/
    companion object Builder2 {
        private var carModel = 0
        private var carBrand = ""
        private var carColor = ""
        public fun setCarModel(carModel: Int): Builder2 {
            this.carModel = carModel
            return this
        }

        public fun setCarBrand(carBrand: String): Builder2 {
            this.carBrand = carBrand
            return this
        }

        public fun setCarColor(color: String): Builder2 {
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