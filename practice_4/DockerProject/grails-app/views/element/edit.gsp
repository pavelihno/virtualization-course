<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Edit Element</title>
    </head>
    <body>
        <h1>Edit Element</h1>

        <g:form action="update" id="${element.id}">
            <label for="title">Title:</label>
            <g:textField name="title" value="${element.title}" required="true" />

            <g:submitButton name="Update" />
        </g:form>

        <g:link action="index">Back to List</g:link>
    </body>
</html>