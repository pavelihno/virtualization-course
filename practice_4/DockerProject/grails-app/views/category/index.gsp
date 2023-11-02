<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Tag List</title>
    </head>
    <body>
        <h1>Category List</h1>

        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${categories}" var="category">
                    <tr>
                        <td>${category.name}</td>
                        <td>
                            <g:link action="edit" id="${category.id}">Edit</g:link>
                            <g:link action="delete" id="${category.id}">Delete</g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>

        <g:link action="create">Create new Category</g:link>
    </body>
</html>