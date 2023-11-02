package com.mirea

class CategoryController {

    def index() {
        def categories = Category.findAll()
        render(view: 'index', model: [categories: categories])
    }

    def create() {
        render(view: 'create')
    }

    def save() {
        def newCategory = new Category()
        newCategory.name = params['name']
        
        Category.withTransaction {
            if (newCategory.save(flush: true)) {
                flash.message = "Category saved successfully"
                redirect(action: 'index')
            } else {
                flash.error = "Failed to save Category"
                render(view: 'create', model: [category: newCategory])
            }
        }
    }

    def edit(Long id) {
        def category = Category.get(id)
        if (!category) {
            flash.error = "Category not found"
            redirect(action: 'index')
            return
        }
        render(view: 'edit', model: [category: category])
    }

    def update(Long id) {
        def category = Category.get(id)
        if (!category) {
            flash.error = "Category not found"
            redirect(action: 'index')
            return
        }

        category.name = params['name']

        Category.withTransaction {
            if (category.save(flush: true)) {
                flash.message = "Category updated successfully"
                redirect(action: 'index')
            } else {
                flash.error = "Failed to update Category"
                render(view: 'edit', model: [category: category])
            }
        }
    }

    def delete(Long id) {
        def category = Category.get(id)
        if (!category) {
            flash.error = "Category not found"
        } else {
            Category.withTransaction {
                category.delete()
                flash.message = "Category deleted successfully"
            }
        }
        redirect(action: 'index')
    }
}