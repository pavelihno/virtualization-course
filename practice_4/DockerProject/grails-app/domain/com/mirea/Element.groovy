package com.mirea

class Element {
    String title
    Tag tag

    String toString() {
        return "Element: ${this.title}"
    }

    static constraints = {
        title nullable: false
    }
}