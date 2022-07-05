# University-Employee-Management-System

REST-API employee management system for universities based on Spring (MVC, Data JPA, Boot, Validator), PostgreSQL and Thymeleaf.

///////////////////////////

Система управления персоналом для вузов на базе Spring (MVC, Data JPA, Boot, Validator), PostgreSQL и Thymeleaf. 

Программа состоит из сущностей: подразделение (departments), сотрудник (employees), повышение квалификации (trainings). 
Сотрудники входят в состав подразделения. У сотрудников есть повышения квалификации.
Все сущности можно создать, редактировать и удалять по однотипным запросам. 

Пример запросов для управления подразделением:
- GET /departments - все подразделения
- GET /departments/{id} - подразделение по id
- GET /departments/new - форма создания подразделения
- POST /departments - создание подразделения
- GET /departments/{id}/edit - редактирование подразделения по id
- PATCH /departments/{id} - обновление подразделения
- DELETE /departments/{id} - удаление подразделения

Точно такие же запросы и для сотрудников, и для повышений квалификации. Дополнительно у повышений квалификаций есть запросы поиска:
- GET /trainings/search - форма поиска
- POST /trainings/search - поиск

Скриншоты программы:

![image](https://user-images.githubusercontent.com/99965044/177241546-df948520-fbde-4b98-ac38-77abee4b6edb.png)
![image](https://user-images.githubusercontent.com/99965044/177241568-dd09026d-bd6f-4142-be8f-47650c9b09a1.png)
![image](https://user-images.githubusercontent.com/99965044/177241734-809fe929-6d43-40bb-a724-8bfcac284315.png)
![image](https://user-images.githubusercontent.com/99965044/177241920-83c5bea3-2751-480b-a80e-689b5060881c.png)
![image](https://user-images.githubusercontent.com/99965044/177241942-393a29bf-4d01-4d47-8cd1-386049def51f.png)
![image](https://user-images.githubusercontent.com/99965044/177242062-1f80e15b-c11e-40cc-8430-92829e9a16f6.png)
