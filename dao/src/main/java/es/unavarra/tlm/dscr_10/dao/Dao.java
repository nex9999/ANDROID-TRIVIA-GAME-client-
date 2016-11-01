package es.unavarra.tlm.dscr_10.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Dao {
    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1, "es.unavarra.tlm.dscr_10.dao");

        Entity log = schema.addEntity("Log");
        log.addIdProperty();
        log.addStringProperty("nombre").notNull();
        log.addDateProperty("acceso").notNull();

        new DaoGenerator().generateAll(schema, "app/src/main/java");

    }
}
