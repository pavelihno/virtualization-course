<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Create Category</title>
    </head>
    <body>
        <h1>Create Category</h1>

        <g:form action="save">
            <label for="name">Name:</label>
            <g:textField name="name" required="true" />

            <g:submitButton name="Create" />
        </g:form>

        <g:link action="index">Back to List</g:link>
    </body>
</html>