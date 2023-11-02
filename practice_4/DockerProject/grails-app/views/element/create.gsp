<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Create Element</title>
    </head>
    <body>
        <h1>Create Element</h1>

        <g:form action="save">
            <label for="title">Title:</label>
            <g:textField name="title" required="true" />

            <g:submitButton name="Create" />
        </g:form>

        <g:link action="index">Back to List</g:link>
    </body>
</html>