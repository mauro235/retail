## retail test

### Running the API
```
./gradlew bootRun
```

### Running the tests
```
./gradlew test
```

### Some remarks about the project:

- Clean architecture was used for project structure
- Spring boot 3.x was selected alongside Java 17 (LTS support)
- Where necessary functional programming was used, so entities and most vars are immutable
- I could have added more entities and tables, for instance: products, currencies. But I think that with the brand table is enough to demonstrate the concept of relational entities and design.
- I have replaced the field prices.price_list in favor of prices.id, because it seems like and incremental identity
- I have used 2 decimal digits for storing the price but could be whatever precision required
- I didn't add unit and integration tests because it's out of scope, but I normally add them
- I have set the date format to yyyy-MM-dd HH:mm:ss, but could be changed in a constant
- I have made the decision of not adding interfaces between layers because it will be an overkill. 
I understand that many developers would prefer interfaces between layers in hexagonal or similar architectures,
but it's a personal criteria or preference. There are many pros and cons of adding interfaces where are not
needed like adding interfaces for JPA interfaces for instance.
- I hope it's clear that I have preferred to not add interfaces in this task, but otherwise I have no
problem in using them if that's the preference for the project.

### Sample request
```
curl --location --request GET 'http://localhost:8080/prices' \
--header 'Content-Type: application/json' \
--data-raw '{
"application_date": "2020-06-14 16:00:00",
"brand_id": 1,
"product_id": 35455
}'
```

### Sample response
```
{
    "product_id": 35455,
    "brand_id": 1,
    "price_id": 2,
    "start_date": "2020-06-14 15:00:00",
    "end_date": "2020-06-14 18:30:00",
    "price": 25.45,
    "currency": "EUR"
}
```