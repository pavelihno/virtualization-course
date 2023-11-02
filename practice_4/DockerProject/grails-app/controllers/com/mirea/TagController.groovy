package com.mirea

class TagController {

    def index() {
        def tags = Tag.findAll()
        render(view: 'index', model: [tags: tags])
    }

    def create() {
        render(view: 'create')
    }

    def save() {
        def newTag = new Tag()
        newTag.name = params['name']
        
        Tag.withTransaction {
            if (newTag.save(flush: true)) {
                flash.message = "Tag saved successfully"
                redirect(action: 'index')
            } else {
                flash.error = "Failed to save Tag"
                render(view: 'create', model: [tag: newTag])
            }
        }
    }

    def edit(Long id) {
        def tag = Tag.get(id)
        if (!tag) {
            flash.error = "Tag not found"
            redirect(action: 'index')
            return
        }
        render(view: 'edit', model: [tag: tag])
    }

    def update(Long id) {
        def tag = Tag.get(id)
        if (!tag) {
            flash.error = "Tag not found"
            redirect(action: 'index')
            return
        }

        tag.name = params['name']

        Tag.withTransaction {
            if (tag.save(flush: true)) {
                flash.message = "Tag updated successfully"
                redirect(action: 'index')
            } else {
                flash.error = "Failed to update Tag"
                render(view: 'edit', model: [tag: tag])
            }
        }
    }

    def delete(Long id) {
        def tag = Tag.get(id)
        if (!tag) {
            flash.error = "Tag not found"
        } else {
            Tag.withTransaction {
                tag.delete()
                flash.message = "Tag deleted successfully"
            }
        }
        redirect(action: 'index')
    }
}