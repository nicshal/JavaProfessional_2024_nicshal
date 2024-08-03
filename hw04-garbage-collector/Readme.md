# Определение нужного размера хипа

### Результаты запуска приложения с разным размеров хипа
```
6 mb - OutOfMemoryError
7 mb - 
16:08:59.685 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
16:08:59.685 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
16:08:59.685 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
16:08:59.685 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
16:08:59.685 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
16:08:59.685 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:109507, sec:109
8 mb - 
16:03:53.044 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
16:03:53.044 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
16:03:53.044 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
16:03:53.044 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
16:03:53.044 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
16:03:53.044 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:110478, sec:110
16 mb - 
16:01:18.875 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
16:01:18.875 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
16:01:18.875 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
16:01:18.876 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
16:01:18.876 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
16:01:18.876 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:139868, sec:139
32 mb - 
15:58:21.481 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
15:58:21.481 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
15:58:21.481 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
15:58:21.481 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
15:58:21.481 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
15:58:21.481 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:253341, sec:253
64 mb - 
15:53:18.841 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
15:53:18.841 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
15:53:18.841 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
15:53:18.841 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
15:53:18.841 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
15:53:18.841 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:337331, sec:337
---
128 mb - 
15:46:30.360 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
15:46:30.361 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
15:46:30.361 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
15:46:30.361 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
15:46:30.361 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
15:46:30.361 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:360998, sec:360
---
256 mb - 
15:14:06.202 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
15:14:06.202 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
15:14:06.202 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
15:14:06.202 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
15:14:06.202 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
15:14:06.202 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:370730, sec:370
---
512 mb - 
15:21:47.272 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
15:21:47.272 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
15:21:47.272 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
15:21:47.272 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
15:21:47.272 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
15:21:47.272 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:372238, sec:372
---
1024 mb - 
15:29:19.774 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
15:29:19.774 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
15:29:19.774 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
15:29:19.774 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
15:29:19.774 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
15:29:19.774 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:380981, sec:380
---
2048 mb - 
15:39:32.023 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
15:39:32.024 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
15:39:32.024 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
15:39:32.024 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
15:39:32.024 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
15:39:32.024 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:414052, sec:414
---
```

Вывод: минимальное время выполнения показывает на размере хипа в 7 mb. Меньший размер хипа приводит к падению программы

### Результаты запуска приложения после оптимизации. Размер хипа - 7 mb
```
7 mb - 
16:26:46.283 [main] INFO ru.nicshal.homework.CalcDemo -- PrevValue:1999999
16:26:46.283 [main] INFO ru.nicshal.homework.CalcDemo -- PrevPrevValue:1999998
16:26:46.283 [main] INFO ru.nicshal.homework.CalcDemo -- SumLastThreeValues:5999994
16:26:46.283 [main] INFO ru.nicshal.homework.CalcDemo -- SomeValue:1335543424
16:26:46.283 [main] INFO ru.nicshal.homework.CalcDemo -- Sum:-1455759936
16:26:46.283 [main] INFO ru.nicshal.homework.CalcDemo -- spend msec:373, sec:0
```

Вывод: после оптимизации уменьшили время выполнения программы в 200 раз