# Итоговый проект по курсу data engineer от OTUS.RU 
## Описание проекта
Проект представляет упрощенный вариант мобильного сервиса.
Есть три сервиса: GEO, SMS, PAYMENT, которые генерируют данные и отравляют их в кафку.
У каждого сервиса свой топик в кафке: geo-service, sms-service, payment-service.
У кадого сервиса есть проверка корректности данных.

Validator-geo проверяет корректность данных по топику geo-service,
если данные корректны, то они отправляются в кафку с топиком: geo-valid иначе с топиком: geo-invalid.

Validator-sms проверяет корректность данных по топику sms-service,
если данные корректны, то они отправляются в кафку с топиком: sms-valid иначе с топиком: sms-invalid.

Validator-payment проверяет корректность данных по топику payment-service,
если данные корректны, то они отправляются в кафку с топиком: payment-valid иначе с топиком: payment-invalid.

По каждому валидному и невалидному топику есть свой сервис, который считывает данные и пишет в POSTGRESQL,
если данные валидны то они записываются в схему valid иначе в invalid.

Так же есть сервис счётчик, который считает количество данных по всем топикам.

Данные по валидным топикам считываем спарк стримингом.

Есть 3 стриминговых приложения, которые считывают данные по трём валидным сервисам.
Полученные агрегаты записываются в файлы формата Delta.

## Запуск проекта
На сервере должны стоять: java-8, mvn, sbt, docker, docker-compose так же в домашнем каталоге должен быть spark-2.4.4-bin-hadoop2.7.
Деплой проекта осуществляется с помощью скрипта: clean_and_package_all_services.sh
Запуск всех сервисов с помощью скрипта: run_all_services.sh
Остановка всех сервисов с помощью скрипта: stop_all_services.sh
