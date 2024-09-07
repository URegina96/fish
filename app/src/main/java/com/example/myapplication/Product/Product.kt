package com.example.myapplication.Product

import android.os.Parcel // Импортируем класс Parcel для работы с сериализацией
import android.os.Parcelable // Импортируем интерфейс Parcelable для возможности передачи объектов между компонентами

// Определяем класс данных Product с тремя свойствами: name, price и imageResId
data class Product(
    val name: String, // Название продукта
    val price: Double, // Цена продукта (изменено на Double, чтобы учитывать дробные значения)
    val imageResId: Int // Ресурс изображения продукта (идентификатор изображения)
) : Parcelable { // Реализуем интерфейс Parcelable для возможности передачи объектов Product между Activity или Fragments
    // Конструктор для восстановления объекта Product из Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "", // Читаем строковое значение name из Parcel, если null, то присваиваем пустую строку
        parcel.readDouble(), // Читаем price как Double из Parcel
        parcel.readInt() // Читаем imageResId как Int из Parcel
    )

    // Метод для записи данных объекта в Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name) // Записываем name в Parcel
        parcel.writeDouble(price) // Записываем price как Double в Parcel
        parcel.writeInt(imageResId) // Записываем imageResId в Parcel
    }

    // Метод для описания содержимого (обычно возвращает 0)
    override fun describeContents(): Int {
        return 0 // Здесь нет специальных содержимого, поэтому возвращаем 0
    }

    // Компаньон объект для создания экземпляров Parcelable
    companion object CREATOR : Parcelable.Creator<Product> { // Реализуем интерфейс Parcelable.Creator для создания объектов Product из Parcel
        // Метод для создания нового экземпляра Product из Parcel
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel) // Возвращаем новый объект Product, используя конструктор, который принимает Parcel
        }

        // Метод для создания массива пустых объектов Product заданного размера
        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size) // Возвращаем массив null размером size
        }
    }
}
