swagger: "2.0"
info:
  version: 1.0.0
  title: Friendships Manager
  description: A sample API in order to keep track of persons and friendships
  contact:
    name: Georgiana Druță si Daniel Martoncă
  license:
    name: Apache 2.0
    url: https://www.apache.org/licenses/LICENSE-2.0.html
schemes:
  - https
consumes:
  - application/json
produces:
  - application/json
paths:
  /persons/{id}:
    get:
      description: Returns a persons with a specific id
      operationId: getPerson
      parameters:
        - name: id
          in: path
          description: ID of person to show
          required: true
          type: string
      responses:
        "200":
          description: ok
        default:
          description: unexpected error

    delete:
      description: deletes a single person based on the ID supplied
      operationId: deletePerson
      parameters:
        - name: id
          in: path
          description: ID of person to delete
          required: true
          type: string
      responses:
        "204":
          description: person deleted
        default:
          description: unexpected error

  /persons/count:
    get:
      description: Returns the number of persons available in the database
      operationId: getPersons
      parameters:
      responses:
        "200":
          description: ok
          schema:
            type: integer
        default:
          description: unexpected error

  /persons:
    get:
      description: Returns all persons available in the database
      operationId: getPersons
      parameters:
      responses:
        "200":
          description: ok
          schema:
            type: array
        default:
          description: unexpected error

  /persons/{id}/{number}:
    post:
      description: Updates a new person in the database
      operationId: updatePerson
      parameters:
        - name: id, name
          in: body
          description: modifying the name of a person
          schema:
          required: true
      responses:
        "200":
          description: ok
        default:
          description: unexpected error

  /persons/{name}:
    put:
      description: Creates a new person in the database
      operationId: createPerson
      parameters:
        - name: name
          in: body
          description: creating a person with the given name
          schema:
          required: true
      responses:
        "200":
          description: ok
        default:
          description: unexpected error

