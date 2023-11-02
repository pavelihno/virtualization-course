<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Edit Tag</title>
    </head>
    <body>
        <h1>Edit Tag</h1>

        <g:form action="update" id="${tag.id}">
            <label for="name">Name:</label>
            <g:textField name="name" value="${tag.name}" required="true" />

            <g:submitButton name="Update" />
        </g:form>

        <g:link action="index">Back to List</g:link>
    </body>
</html>