package com.mirea

class Category {
    String name
    List<Tag> tags = []

    static hasMany = [tags: Tag]

    String toString() {
        return "Category: ${this.name}"
    }

    static constraints = {
        name nullable: false
    }
}