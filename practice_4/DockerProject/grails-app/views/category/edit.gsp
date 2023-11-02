<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Edit Category</title>
    </head>
    <body>
        <h1>Edit Category</h1>

        <g:form action="update" id="${category.id}">
            <label for="name">Name:</label>
            <g:textField name="name" value="${category.name}" required="true" />

            <g:submitButton name="Update" />
        </g:form>

        <g:link action="index">Back to List</g:link>
    </body>
</html>