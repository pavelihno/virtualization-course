<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Element List</title>
    </head>
    <body>
        <h1>Element List</h1>

        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${elements}" var="element">
                    <tr>
                        <td>${element.title}</td>
                        <td>
                            <g:link action="edit" id="${element.id}">Edit</g:link>
                            <g:link action="delete" id="${element.id}">Delete</g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>

        <g:link action="create">Create new Element</g:link>
        <g:link action="getImage">Get image</g:link>
    </body>
</html>