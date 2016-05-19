# Sample Project for Jackson MrBean Module Issue

Run `mvn test` and you will see that the object deserializes fine
when the MrBean module is not present in the `ObjectMapper`. When
MrBean is present then it fails to deserialize the `Long` into
a `Calendar` instance.
