package ru.nicshal.advanced.homework13;

import ru.nicshal.advanced.homework13.appcontainer.AppComponentsContainerImpl;
import ru.nicshal.advanced.homework13.appcontainer.api.AppComponentsContainer;
import ru.nicshal.advanced.homework13.config.AppConfig;
import ru.nicshal.advanced.homework13.services.GameProcessor;
import ru.nicshal.advanced.homework13.services.GameProcessorImpl;

public class App {

    public static void main(String[] args) {
        // Опциональные варианты
        // AppComponentsContainer container = new AppComponentsContainerImpl(AppConfig1.class, AppConfig2.class);

        // Тут можно использовать библиотеку Reflections (см. зависимости)
        // AppComponentsContainer container = new AppComponentsContainerImpl("ru.otus.config");

        // Обязательный вариант
        AppComponentsContainer container = new AppComponentsContainerImpl(AppConfig.class);

        // Приложение должно работать в каждом из указанных ниже вариантов
        GameProcessor gameProcessor = container.getAppComponent(GameProcessor.class);
        //GameProcessor gameProcessor = container.getAppComponent(GameProcessorImpl.class);
        //GameProcessor gameProcessor = container.getAppComponent("gameProcessor");

        gameProcessor.startGame();
    }
}
