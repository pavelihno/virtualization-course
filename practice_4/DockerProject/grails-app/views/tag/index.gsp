<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Tag List</title>
    </head>
    <body>
        <h1>Tag List</h1>

        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${tags}" var="tag">
                    <tr>
                        <td>${tag.name}</td>
                        <td>
                            <g:link action="edit" id="${tag.id}">Edit</g:link>
                            <g:link action="delete" id="${tag.id}">Delete</g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>

        <g:link action="create">Create new Tag</g:link>
    </body>
</html>