## Diagrama de classes

``` mermaid
classDiagram
  direction LR
  class User {
    id: String
    firstName: String
    lastName: String
    email: String
    password: String
  }

  class Address {
    <<embedded>>
    street: String
    number: String
    neighborhood: String
    city: String
    state: String
    postalCode: String
    complement: String
  }
  
  class Complaint {
    id: String
    title: String
    description: String
    address: Address
    status: StatusEnum
    imageUrl: String
    createdDate: Instant
    updatedDate: Instant
    isAnonymous: boolean
  }

  class StatusEnum {
    PENDING
    UNDER_REVIEW
    IN_PROGRESS
    RESOLVED
    CLOSED
    REJECTED
  }

  class Tag {
    _id: String
    name: String
  }

  User "1"-- "*" Complaint
  Complaint "1"--"1" Address
  Complaint "*"-- "1" Tag

```