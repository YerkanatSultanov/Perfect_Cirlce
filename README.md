# Perfect_Cirlce
nFactorial Incubator 2023 task

### Об игре

* Моя игра была написана на Java и JavaFX.
  * Логика игры - Java
  * Дизайн - JavaFX
***
* Смысл игры заключается в том, чтобы игрок нарисовал, насколько это возможно, идеальный круг

***

### Как запускать игру?
* Проект состоит из одного класса, так что вам лучше скопировать весь код с класса Main, открыть новый проект JavaFX, именно JavaFX, на своем компьютере и запустить проект
* Есть одно но, из-за того что стили в проекте который с гита, на вашем новом проекте где вы будете запускать код не будет стилей, а именно шрифта.

***
![img_1.png](img_1.png)

### Главный баг в игре
* Игра недоработанная, есть баг при рисовании круга.
  * Программа высчитывает сколько раз вы прошли через линию, которую сама же высчитывает, и если вы прошли через эту линию 2 раза то круг считается завершенным.
И баг состоит в том, что программа может сразу за пол круга сосчитать, что вы прошли два раза через эту линию и посчитает, что круг завершенный.
