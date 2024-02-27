# Личный проект "Веб-сервис по сокращению ссылок"

* Автор: Шмелёв Кирилл

---

### Техническое задание №1

#### Задание

Реализовать сервис, предоставляющий API по созданию сокращённых ссылок.

[Рисовашка](https://excalidraw.com/#room=79894a974aaa6e2deab1,zr5y5myMl3_XAXce_Xb9CQ)

Ссылка должна быть:
* Уникальной: на один оригинальный URL должна существовать только одна сокращенная ссылка; аналогично сокращенная ссылка может указывать только на один URL;
* Длиной 10 символов ровно;
* Содержит символы латинского алфавита в обоих регистрах, цифры 0-9, символ _ (нижнее подчеркивание). По сути Base63 кодирование;
* Реализовать 2 возможных режима работы приложения: с использованием базы данных и с использованием in-memory хранилища (по сути встроенного в язык типа данных на усмотрение). Подсказка - можно воспользоваться передачей флагов при запуске приложения консольной командой.

Сервис работает по протоколу HTTP и содержит следующие запросы:
* Метод POST, который принимает в теле запроса целевой (оригинальный) URL и записывает его в базу/хранилище;
* Метод GET, который в параметре пути (Path param) принимает сокращенный URL и возвращает оригинальный URL, соответствующий ему.

#### Требования

* Язык программирования - Java/Kotlin. Для работы с HTTP можно использовать любой фреймворк (например, Spring);
* База данных - любая SQL на выбор (MySQL, PostgreSQL, MS SQL и т.д.);
* Запросы к базе по возможности писать через SQL (строка sql запроса), а не через ORM/средства языка (если не лень, то желательно и так, и так, чтобы понял суть языка + запросов);
* Сокращенный URL должен иметь высокую степень уникальности (использовать либо хэш-функции, либо генератор рандомных чисел/последовательностей/байтов высокой степени случайности);
* (подсказка) При реализации in-memory решения сделать акцент на скорость и надежность работы получения/записи URL;
* Пример Get-запроса:
``` 
http://localhost:6969/<сокращенный_URL> 

Тело ответа (json):
{
“originalURL”: <оригинальный_URL>
}
```
* Пример Post-запроса:
```
http://localhost:6969/short

Тело запроса (json):
{
	“originalURL”: <оригинальный_URL>
}

Тело ответа (json):
{
	“shortURL”: <сокращенный_URL>
}
```
* Структура таблицы в базе данных SQL:
* * ID - первичный ключ (уникальный идентификатор сущности в таблице, желательно числовое поле с автоинкрементом);
* * Original_URL - оригинальный URL. Поле строкового типа произвольного размера. Уникальное;
* * Short_URL - сокращенный URL. Поле строкового типа размера 10 символов. Уникальное.
* Для тестирования отправки запросов можно использовать: Postman (удобное приложение, для веба почти must-have), 
утилита curl (в баше точно, в powershell надо отдельно смотреть, ее так или иначе используют все инструменты для 
отправки HTTP-запросов, но это не очень удобно делать самому), Swagger (больше графическая документация для API, в 
целом штука полезная, широко используется, но для твоей задачи мб немного overkill);
* Написать Unit-тесты для функций, работающих с in-memory;
* Добавить логи на запросы.

#### Полезные ссылки
* [Про HTTP](https://developer.mozilla.org/ru/docs/Web/HTTP/Overview)
* [Про SQL](https://roadmap.sh/sql) (понадобятся только самые базовые вещи, но если надо будет учить SQL - реально хорошая роадмапа)
* * https://www.mysql.com/
* * https://www.postgresql.org/
* [Java](https://metanit.com/java/)
* Технически можно попробовать для веба: https://spring.io/
* [Прикольная статья про тесты](https://habr.com/ru/articles/169381/)
* [Про Makefile](https://guides.hexlet.io/ru/makefile-as-task-runner/) - дико полезная срань для разработки


