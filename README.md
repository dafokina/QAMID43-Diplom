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