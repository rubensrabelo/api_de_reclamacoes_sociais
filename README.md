## Diagrama de classes

``` mermaid
classDiagram
  direction LR
  class User {
    _id: String
    firstName: String
    lastName: String
    email: String
    password: String
  }

  class Address {
    <<embedded>>
    street: String
    neighborhood: String
    city: String
    state: String
  }
  
  class Complaint {
    _id: String
    title: String
    description: String
    address: Address
    status: StatusEnum
    image_url: String
    created_date: Instant
    updated_date: Instant
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