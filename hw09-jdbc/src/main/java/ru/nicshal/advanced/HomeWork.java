package ru.nicshal.advanced;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.core.repository.executor.DbExecutorImpl;
import ru.nicshal.advanced.core.sessionmanager.TransactionRunnerJdbc;
import ru.nicshal.advanced.crm.datasource.DriverManagerDataSource;
import ru.nicshal.advanced.crm.model.Client;
import ru.nicshal.advanced.crm.model.Manager;
import ru.nicshal.advanced.crm.service.DbServiceManagerImpl;
import ru.nicshal.advanced.crm.service.DbServiceClientImpl;
import ru.nicshal.advanced.jdbc.mapper.*;

@SuppressWarnings({"java:S125", "java:S1481"})
public class HomeWork {
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
        var dataTemplateClient = new DataTemplateJdbc<>(
                dbExecutor, entitySQLMetaDataClient, entityClassMetaDataClient);

        // Код дальше должен остаться
        var dbServiceClient = new DbServiceClientImpl(transactionRunner, dataTemplateClient);
        dbServiceClient.saveClient(new Client("dbServiceFirst"));

        var clientSecond = dbServiceClient.saveClient(new Client("dbServiceSecond"));
        var clientSecondSelected = dbServiceClient
                .getClient(clientSecond.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);

        EntityClassMetaData<Manager> entityClassMetaDataManager = EntityClassMetaDataImpl.of(Manager.class);
        EntitySQLMetaData entitySQLMetaDataManager = EntitySQLMetaDataImpl.of(entityClassMetaDataManager);
        var dataTemplateManager = new DataTemplateJdbc<>(dbExecutor, entitySQLMetaDataManager, entityClassMetaDataManager);

        var dbServiceManager = new DbServiceManagerImpl(transactionRunner, dataTemplateManager);
        dbServiceManager.saveManager(new Manager("ManagerFirst"));

        var managerSecond = dbServiceManager.saveManager(new Manager("ManagerSecond"));
        var managerSecondSelected = dbServiceManager
                .getManager(managerSecond.getNo())
                .orElseThrow(() -> new RuntimeException("Manager not found, id:" + managerSecond.getNo()));
        log.info("managerSecondSelected:{}", managerSecondSelected);
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
