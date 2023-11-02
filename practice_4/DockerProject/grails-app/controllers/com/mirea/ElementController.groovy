package com.mirea


import org.springframework.http.MediaType
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import java.nio.file.Path
import java.nio.file.Files

class ElementController {

    def index() {
        def elements = Element.findAll()
        render(view: 'index', model: [elements: elements])
    }

    def create() {
        render(view: 'create')
    }

    def save() {
        def newElement = new Element()
        newElement.title = params['title']
        
        Element.withTransaction {
            if (newElement.save(flush: true)) {
                flash.message = "Element saved successfully"
                redirect(action: 'index')
            } else {
                flash.error = "Failed to save Element"
                render(view: 'create', model: [element: newElement])
            }
        }
    }

    def edit(Long id) {
        def element = Element.get(id)
        if (!element) {
            flash.error = "Element not found"
            redirect(action: 'index')
            return
        }
        render(view: 'edit', model: [element: element])
    }

    def update(Long id) {
        def element = Element.get(id)
        if (!element) {
            flash.error = "Element not found"
            redirect(action: 'index')
            return
        }

        element.title = params['title']

        Element.withTransaction {
            if (element.save(flush: true)) {
                flash.message = "Element updated successfully"
                redirect(action: 'index')
            } else {
                flash.error = "Failed to update Element"
                render(view: 'edit', model: [element: element])
            }
        }
    }

    def delete(Long id) {
        def element = Element.get(id)
        if (!element) {
            flash.error = "Element not found"
        } else {
            Element.withTransaction {
                element.delete()
                flash.message = "Element deleted successfully"
            }
        }
        redirect(action: 'index')
    }

    def getImage() {
        def storagePath = grailsApplication.config.grails.app.storagePath
        def directoryPath = Path.of(storagePath)
        def imageResource
        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            try (def directoryStream = Files.newDirectoryStream(directoryPath, "*.png")) {
                for (Path filePath : directoryStream) {
                    imageResource = new UrlResource("file:$filePath")
                    break
                }
            }
        }

        if (imageResource) {
            response.setHeader('Content-Disposition', 'attachment; filename="image.png"')
            response.setContentType(MediaType.IMAGE_PNG.toString())
            def imageData = imageResource.inputStream.readAllBytes()
            response.outputStream.write(imageData)
        } else {
            response.setStatus(404)
        }
    }
}