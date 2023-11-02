package com.mirea

class Tag {
    String name
    List<Element> elements = []

    static hasMany = [elements: Element]

    String toString() {
        return "Tag: ${this.name}"
    }

    static constraints = {
        name nullable: false, unique: true
    }
}