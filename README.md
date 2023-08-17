# Дипломный проект по профессии «Инженер по тестированию»

---

## Тестирование мобильного приложения «Мобильный хоспис»

[Ссылка на задание](https://github.com/netology-code/qamid-diplom)

---

## Описание приложения

Приложение даёт функционал по работе с претензиями хосписа и включает в себя:
- информацию о претензиях и функционал для работы с ними;
- новостную сводку хосписа;
- тематические цитаты.

---

## Документация

- [План тестирования](https://github.com/dafokina/QAMID43-Diplom/blob/master/Plan.md)
- [Чек-лист](https://docs.google.com/spreadsheets/d/15YJiOt0RNHwI1rGuOKl2lF_f2KUyFNNZ/edit#gid=1474013527)
- [Тест-кейсы](https://docs.google.com/spreadsheets/d/1vHjyPISd3-K1KHmxQdC4k0CNVebzVVw9/edit#gid=716714085)
- [Отчет о тестировании](https://github.com/dafokina/QAMID43-Diplom/blob/master/Result.md)

---

## Процедура запуска приложения и автотестов

---

- Клонировать репозиторий командой `git clone https://github.com/dafokina/QAMID43-Diplom.git`;
- Открыть проект в Android Studio;
- Запустить приложение на эмуляторе Pixel XL API 29;
- Запустить тесты из командной строки`./gradlew connectedAndroidTest`;
- Подождать пока пройдут все тесты и посмотреть результат.

## Формирование отчета AllureReport

---

- Перейти к папке с тестами fmh-android\app\src\androidTest\java\ru\iteco\fmhandroid\ui\tests нажать ПКМ и выбрать Run 'Tests in 'ru.iteco.fmhandroid.ui.test' или комбинацией клавиш Ctrl+Shift+F10
- Выгрузить каталог `/data/data/ru.iteco.fmhandroid/files/allure-results` с эмулятора;
- Выполнить команду `allure serve`в терминале, находясь на уровень выше allure-results;
- Подождать генерации отчета и посмотреть его в открывшемся браузере.