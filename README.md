<details><summary>Запуск</summary>
Для того, чтобы запустить необходимо проделать следующие шаги на Windows, установите [Git Bash](https://git-scm.com/)

1. Склонируйте репозиторий

```shell
git clone https://github.com/maksim25y/TestApplication.git
```

2. Скачайте и установите Docker

Скачать и найти инструкцию по установке вы можете на официальном сайте [Docker](https://www.docker.com)

3. Запустите приложение в Docker

Для этого откройте терминал и перейдите в папку репозитория

```shell
cd TestApplication
```
Далее введите команду
```shell
docker-compose up
```
Готово! Сервер запущен.
Чтобы зайти на сайт перейдите по ссылке: localhost:8080

Чтобы остановить работу контейнеров, в терминале, откуда вы запускали docker-compose нажмите Ctrl+C (Control + C для Mac)
</details>
<details><summary>Функционал</summary>
Для получения рассчёта по средней зарплате за год и количеству дней отпуска необходимо зайти в Postman или др, далее выполнить GET запрос по адресу localhost:8080/calculate, указав в параметрах запроса среднюю ЗП за год averageSalary и количество дней отпуска amountVacationDays:
  
![image](https://github.com/user-attachments/assets/3da79f59-fe68-439e-8b41-86b4ec59d82f)

</details>
