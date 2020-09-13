package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class HistorialDePartidaBeanRealmProxy extends com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean
    implements RealmObjectProxy, HistorialDePartidaBeanRealmProxyInterface {

    static final class HistorialDePartidaBeanColumnInfo extends ColumnInfo {
        long idIndex;
        long fechaDePartidaIndex;
        long cantidadDeVerbosIndex;
        long porcentajeAciertosIndex;
        long porcentajeErroresIndex;

        HistorialDePartidaBeanColumnInfo(SharedRealm realm, Table table) {
            super(5);
            this.idIndex = addColumnDetails(table, "id", RealmFieldType.INTEGER);
            this.fechaDePartidaIndex = addColumnDetails(table, "fechaDePartida", RealmFieldType.DATE);
            this.cantidadDeVerbosIndex = addColumnDetails(table, "cantidadDeVerbos", RealmFieldType.INTEGER);
            this.porcentajeAciertosIndex = addColumnDetails(table, "porcentajeAciertos", RealmFieldType.INTEGER);
            this.porcentajeErroresIndex = addColumnDetails(table, "porcentajeErrores", RealmFieldType.INTEGER);
        }

        HistorialDePartidaBeanColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new HistorialDePartidaBeanColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final HistorialDePartidaBeanColumnInfo src = (HistorialDePartidaBeanColumnInfo) rawSrc;
            final HistorialDePartidaBeanColumnInfo dst = (HistorialDePartidaBeanColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.fechaDePartidaIndex = src.fechaDePartidaIndex;
            dst.cantidadDeVerbosIndex = src.cantidadDeVerbosIndex;
            dst.porcentajeAciertosIndex = src.porcentajeAciertosIndex;
            dst.porcentajeErroresIndex = src.porcentajeErroresIndex;
        }
    }

    private HistorialDePartidaBeanColumnInfo columnInfo;
    private ProxyState<com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean> proxyState;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("fechaDePartida");
        fieldNames.add("cantidadDeVerbos");
        fieldNames.add("porcentajeAciertos");
        fieldNames.add("porcentajeErrores");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    HistorialDePartidaBeanRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (HistorialDePartidaBeanColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public Date realmGet$fechaDePartida() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.fechaDePartidaIndex);
    }

    @Override
    public void realmSet$fechaDePartida(Date value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'fechaDePartida' to null.");
            }
            row.getTable().setDate(columnInfo.fechaDePartidaIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'fechaDePartida' to null.");
        }
        proxyState.getRow$realm().setDate(columnInfo.fechaDePartidaIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$cantidadDeVerbos() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.cantidadDeVerbosIndex);
    }

    @Override
    public void realmSet$cantidadDeVerbos(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.cantidadDeVerbosIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.cantidadDeVerbosIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$porcentajeAciertos() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.porcentajeAciertosIndex);
    }

    @Override
    public void realmSet$porcentajeAciertos(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.porcentajeAciertosIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.porcentajeAciertosIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$porcentajeErrores() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.porcentajeErroresIndex);
    }

    @Override
    public void realmSet$porcentajeErrores(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.porcentajeErroresIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.porcentajeErroresIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("HistorialDePartidaBean");
        builder.addProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addProperty("fechaDePartida", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addProperty("cantidadDeVerbos", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addProperty("porcentajeAciertos", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addProperty("porcentajeErrores", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
         return expectedObjectSchemaInfo;
    }

    public static HistorialDePartidaBeanColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_HistorialDePartidaBean")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'HistorialDePartidaBean' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_HistorialDePartidaBean");
        final long columnCount = table.getColumnCount();
        if (columnCount != 5) {
            if (columnCount < 5) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 5 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 5 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 5 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final HistorialDePartidaBeanColumnInfo columnInfo = new HistorialDePartidaBeanColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.idIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field id");
            }
        }

        if (!columnTypes.containsKey("id")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("id") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'id' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.idIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("fechaDePartida")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'fechaDePartida' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("fechaDePartida") != RealmFieldType.DATE) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Date' for field 'fechaDePartida' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.fechaDePartidaIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'fechaDePartida' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'fechaDePartida' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("cantidadDeVerbos")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'cantidadDeVerbos' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("cantidadDeVerbos") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'cantidadDeVerbos' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.cantidadDeVerbosIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'cantidadDeVerbos' does support null values in the existing Realm file. Use corresponding boxed type for field 'cantidadDeVerbos' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("porcentajeAciertos")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'porcentajeAciertos' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("porcentajeAciertos") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'porcentajeAciertos' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.porcentajeAciertosIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'porcentajeAciertos' does support null values in the existing Realm file. Use corresponding boxed type for field 'porcentajeAciertos' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("porcentajeErrores")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'porcentajeErrores' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("porcentajeErrores") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'porcentajeErrores' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.porcentajeErroresIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'porcentajeErrores' does support null values in the existing Realm file. Use corresponding boxed type for field 'porcentajeErrores' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_HistorialDePartidaBean";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean obj = null;
        if (update) {
            Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class), false, Collections.<String> emptyList());
                    obj = new io.realm.HistorialDePartidaBeanRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.HistorialDePartidaBeanRealmProxy) realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.HistorialDePartidaBeanRealmProxy) realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("fechaDePartida")) {
            if (json.isNull("fechaDePartida")) {
                ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$fechaDePartida(null);
            } else {
                Object timestamp = json.get("fechaDePartida");
                if (timestamp instanceof String) {
                    ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$fechaDePartida(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$fechaDePartida(new Date(json.getLong("fechaDePartida")));
                }
            }
        }
        if (json.has("cantidadDeVerbos")) {
            if (json.isNull("cantidadDeVerbos")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'cantidadDeVerbos' to null.");
            } else {
                ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$cantidadDeVerbos((int) json.getInt("cantidadDeVerbos"));
            }
        }
        if (json.has("porcentajeAciertos")) {
            if (json.isNull("porcentajeAciertos")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'porcentajeAciertos' to null.");
            } else {
                ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$porcentajeAciertos((int) json.getInt("porcentajeAciertos"));
            }
        }
        if (json.has("porcentajeErrores")) {
            if (json.isNull("porcentajeErrores")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'porcentajeErrores' to null.");
            } else {
                ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$porcentajeErrores((int) json.getInt("porcentajeErrores"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean obj = new com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("fechaDePartida")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$fechaDePartida(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$fechaDePartida(new Date(timestamp));
                    }
                } else {
                    ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$fechaDePartida(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("cantidadDeVerbos")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'cantidadDeVerbos' to null.");
                } else {
                    ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$cantidadDeVerbos((int) reader.nextInt());
                }
            } else if (name.equals("porcentajeAciertos")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'porcentajeAciertos' to null.");
                } else {
                    ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$porcentajeAciertos((int) reader.nextInt());
                }
            } else if (name.equals("porcentajeErrores")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'porcentajeErrores' to null.");
                } else {
                    ((HistorialDePartidaBeanRealmProxyInterface) obj).realmSet$porcentajeErrores((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean copyOrUpdate(Realm realm, com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) cachedRealmObject;
        }

        com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
            if (rowIndex != Table.NO_MATCH) {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.HistorialDePartidaBeanRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean copy(Realm realm, com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean realmObject = realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class, ((HistorialDePartidaBeanRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        HistorialDePartidaBeanRealmProxyInterface realmObjectSource = (HistorialDePartidaBeanRealmProxyInterface) newObject;
        HistorialDePartidaBeanRealmProxyInterface realmObjectCopy = (HistorialDePartidaBeanRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$fechaDePartida(realmObjectSource.realmGet$fechaDePartida());
        realmObjectCopy.realmSet$cantidadDeVerbos(realmObjectSource.realmGet$cantidadDeVerbos());
        realmObjectCopy.realmSet$porcentajeAciertos(realmObjectSource.realmGet$porcentajeAciertos());
        realmObjectCopy.realmSet$porcentajeErrores(realmObjectSource.realmGet$porcentajeErrores());
        return realmObject;
    }

    public static long insert(Realm realm, com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        long tableNativePtr = table.getNativePtr();
        HistorialDePartidaBeanColumnInfo columnInfo = (HistorialDePartidaBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$fechaDePartida = ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$fechaDePartida();
        if (realmGet$fechaDePartida != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.fechaDePartidaIndex, rowIndex, realmGet$fechaDePartida.getTime(), false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.cantidadDeVerbosIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$cantidadDeVerbos(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.porcentajeAciertosIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$porcentajeAciertos(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.porcentajeErroresIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$porcentajeErrores(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        long tableNativePtr = table.getNativePtr();
        HistorialDePartidaBeanColumnInfo columnInfo = (HistorialDePartidaBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean object = null;
        while (objects.hasNext()) {
            object = (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            java.util.Date realmGet$fechaDePartida = ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$fechaDePartida();
            if (realmGet$fechaDePartida != null) {
                Table.nativeSetTimestamp(tableNativePtr, columnInfo.fechaDePartidaIndex, rowIndex, realmGet$fechaDePartida.getTime(), false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.cantidadDeVerbosIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$cantidadDeVerbos(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.porcentajeAciertosIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$porcentajeAciertos(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.porcentajeErroresIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$porcentajeErrores(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        long tableNativePtr = table.getNativePtr();
        HistorialDePartidaBeanColumnInfo columnInfo = (HistorialDePartidaBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$fechaDePartida = ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$fechaDePartida();
        if (realmGet$fechaDePartida != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.fechaDePartidaIndex, rowIndex, realmGet$fechaDePartida.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fechaDePartidaIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.cantidadDeVerbosIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$cantidadDeVerbos(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.porcentajeAciertosIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$porcentajeAciertos(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.porcentajeErroresIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$porcentajeErrores(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        long tableNativePtr = table.getNativePtr();
        HistorialDePartidaBeanColumnInfo columnInfo = (HistorialDePartidaBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean object = null;
        while (objects.hasNext()) {
            object = (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            java.util.Date realmGet$fechaDePartida = ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$fechaDePartida();
            if (realmGet$fechaDePartida != null) {
                Table.nativeSetTimestamp(tableNativePtr, columnInfo.fechaDePartidaIndex, rowIndex, realmGet$fechaDePartida.getTime(), false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.fechaDePartidaIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.cantidadDeVerbosIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$cantidadDeVerbos(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.porcentajeAciertosIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$porcentajeAciertos(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.porcentajeErroresIndex, rowIndex, ((HistorialDePartidaBeanRealmProxyInterface) object).realmGet$porcentajeErrores(), false);
        }
    }

    public static com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean createDetachedCopy(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) cachedObject.object;
            }
            unmanagedObject = (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        HistorialDePartidaBeanRealmProxyInterface unmanagedCopy = (HistorialDePartidaBeanRealmProxyInterface) unmanagedObject;
        HistorialDePartidaBeanRealmProxyInterface realmSource = (HistorialDePartidaBeanRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$fechaDePartida(realmSource.realmGet$fechaDePartida());
        unmanagedCopy.realmSet$cantidadDeVerbos(realmSource.realmGet$cantidadDeVerbos());
        unmanagedCopy.realmSet$porcentajeAciertos(realmSource.realmGet$porcentajeAciertos());
        unmanagedCopy.realmSet$porcentajeErrores(realmSource.realmGet$porcentajeErrores());
        return unmanagedObject;
    }

    static com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean update(Realm realm, com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean realmObject, com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean newObject, Map<RealmModel, RealmObjectProxy> cache) {
        HistorialDePartidaBeanRealmProxyInterface realmObjectTarget = (HistorialDePartidaBeanRealmProxyInterface) realmObject;
        HistorialDePartidaBeanRealmProxyInterface realmObjectSource = (HistorialDePartidaBeanRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$fechaDePartida(realmObjectSource.realmGet$fechaDePartida());
        realmObjectTarget.realmSet$cantidadDeVerbos(realmObjectSource.realmGet$cantidadDeVerbos());
        realmObjectTarget.realmSet$porcentajeAciertos(realmObjectSource.realmGet$porcentajeAciertos());
        realmObjectTarget.realmSet$porcentajeErrores(realmObjectSource.realmGet$porcentajeErrores());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("HistorialDePartidaBean = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fechaDePartida:");
        stringBuilder.append(realmGet$fechaDePartida());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cantidadDeVerbos:");
        stringBuilder.append(realmGet$cantidadDeVerbos());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{porcentajeAciertos:");
        stringBuilder.append(realmGet$porcentajeAciertos());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{porcentajeErrores:");
        stringBuilder.append(realmGet$porcentajeErrores());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialDePartidaBeanRealmProxy aHistorialDePartidaBean = (HistorialDePartidaBeanRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aHistorialDePartidaBean.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aHistorialDePartidaBean.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aHistorialDePartidaBean.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
