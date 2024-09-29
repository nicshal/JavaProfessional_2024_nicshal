package ru.nicshal.advanced;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.cache.DataTemplateCache;
import ru.nicshal.advanced.core.repository.executor.DbExecutorImpl;
import ru.nicshal.advanced.core.sessionmanager.TransactionRunnerJdbc;
import ru.nicshal.advanced.crm.datasource.DriverManagerDataSource;
import ru.nicshal.advanced.crm.model.Client;
import ru.nicshal.advanced.crm.model.Manager;
import ru.nicshal.advanced.crm.service.DbServiceClientDoubleImpl;
import ru.nicshal.advanced.crm.service.DbServiceManagerDoubleImpl;
import ru.nicshal.advanced.crm.service.DbServiceManagerImpl;
import ru.nicshal.advanced.jdbc.mapper.*;

import java.util.List;

@SuppressWarnings({"java:S125", "java:S1481"})
public class HomeWorkCache {
    private static final String URL = "jdbc:postgresql://localhost:5430/demoDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";

    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
        // Общая часть
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        var transactionRunner = new TransactionRunnerJdbc(dataSource);
        var dbExecutor = new DbExecutorImpl();

        // Работа с клиентом
        EntityClassMetaData<Client> entityClassMetaDataClient = EntityClassMetaDataImpl.of(Client.class);
        EntitySQLMetaData entitySQLMetaDataClient = EntitySQLMetaDataImpl.of(entityClassMetaDataClient);
        var dataTemplateClientCache = new DataTemplateCache<>(
                new DataTemplateDoubleJdbc<>(dbExecutor, entitySQLMetaDataClient, entityClassMetaDataClient));

        var dbServiceClientCache = new DbServiceClientDoubleImpl(transactionRunner, dataTemplateClientCache);
        dbServiceClientCache.saveClient(new Client("dbServiceFirst"));

        var clientSecond = dbServiceClientCache.saveClient(new Client("dbServiceSecond"));
        var clientSecondSelected = dbServiceClientCache
                .getClient(clientSecond.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);

        // Работа с менеджером
        EntityClassMetaData<Manager> entityClassMetaDataManager = EntityClassMetaDataImpl.of(Manager.class);
        EntitySQLMetaData entitySQLMetaDataManager = EntitySQLMetaDataImpl.of(entityClassMetaDataManager);
        var dataTemplateManagerCache = new DataTemplateCache<>(
                new DataTemplateDoubleJdbc<>(dbExecutor, entitySQLMetaDataManager, entityClassMetaDataManager));

        var dbServiceManagerCache = new DbServiceManagerDoubleImpl(transactionRunner, dataTemplateManagerCache);
        dbServiceManagerCache.saveManager(new Manager("ManagerFirst"));

        var managerSecond = dbServiceManagerCache.saveManager(new Manager("ManagerSecond"));

        long start = System.nanoTime();
        var managerSecondSelectedCache = dbServiceManagerCache
                .getManager(managerSecond.getNo())
                .orElseThrow(() -> new RuntimeException("Manager not found, id:" + managerSecond.getNo()));
        long finish = System.nanoTime();
        long elapsed = finish - start;
        log.info("managerSecondSelected:{}", managerSecondSelectedCache);
        log.info("Время выполнения (кеш):{}", elapsed);
        //22:13:59.242 [main] INFO ru.nicshal.advanced.HomeWork -- Время выполнения (кеш):494521

        var dataTemplateManagerNoCache =
                new DataTemplateJdbc<>(dbExecutor, entitySQLMetaDataManager, entityClassMetaDataManager);

        var dbServiceManagerNoCache = new DbServiceManagerImpl(transactionRunner, dataTemplateManagerNoCache);

        start = System.nanoTime();
        var managerSecondSelectedNoCache = dbServiceManagerNoCache
                .getManager(managerSecond.getNo())
                .orElseThrow(() -> new RuntimeException("Manager not found, id:" + managerSecond.getNo()));
        finish = System.nanoTime();
        elapsed = finish - start;
        log.info("managerSecondSelected:{}", managerSecondSelectedNoCache);
        log.info("Время выполнения (база):{}", elapsed);
        //22:13:59.244 [main] INFO ru.nicshal.advanced.HomeWork -- Время выполнения (база):1953839

        List<Manager> managerList = dbServiceManagerCache.findAll();
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }

}
