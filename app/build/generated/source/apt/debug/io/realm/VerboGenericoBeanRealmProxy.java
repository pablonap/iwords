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
public class VerboGenericoBeanRealmProxy extends com.binary_winters.projects.iverbs.modelo.VerboGenericoBean
    implements RealmObjectProxy, VerboGenericoBeanRealmProxyInterface {

    static final class VerboGenericoBeanColumnInfo extends ColumnInfo {
        long idIndex;
        long verboEnEspIndex;
        long cadenaDeVerbosIndex;
        long archivoDeAudioEnStringIndex;
        long nombreArchivoAudioIndex;

        VerboGenericoBeanColumnInfo(SharedRealm realm, Table table) {
            super(5);
            this.idIndex = addColumnDetails(table, "id", RealmFieldType.INTEGER);
            this.verboEnEspIndex = addColumnDetails(table, "verboEnEsp", RealmFieldType.OBJECT);
            this.cadenaDeVerbosIndex = addColumnDetails(table, "cadenaDeVerbos", RealmFieldType.STRING);
            this.archivoDeAudioEnStringIndex = addColumnDetails(table, "archivoDeAudioEnString", RealmFieldType.STRING);
            this.nombreArchivoAudioIndex = addColumnDetails(table, "nombreArchivoAudio", RealmFieldType.STRING);
        }

        VerboGenericoBeanColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new VerboGenericoBeanColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final VerboGenericoBeanColumnInfo src = (VerboGenericoBeanColumnInfo) rawSrc;
            final VerboGenericoBeanColumnInfo dst = (VerboGenericoBeanColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.verboEnEspIndex = src.verboEnEspIndex;
            dst.cadenaDeVerbosIndex = src.cadenaDeVerbosIndex;
            dst.archivoDeAudioEnStringIndex = src.archivoDeAudioEnStringIndex;
            dst.nombreArchivoAudioIndex = src.nombreArchivoAudioIndex;
        }
    }

    private VerboGenericoBeanColumnInfo columnInfo;
    private ProxyState<com.binary_winters.projects.iverbs.modelo.VerboGenericoBean> proxyState;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("verboEnEsp");
        fieldNames.add("cadenaDeVerbos");
        fieldNames.add("archivoDeAudioEnString");
        fieldNames.add("nombreArchivoAudio");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    VerboGenericoBeanRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (VerboGenericoBeanColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.binary_winters.projects.iverbs.modelo.VerboGenericoBean>(this);
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
    public com.binary_winters.projects.iverbs.VerboEnEsp realmGet$verboEnEsp() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.verboEnEspIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.binary_winters.projects.iverbs.VerboEnEsp.class, proxyState.getRow$realm().getLink(columnInfo.verboEnEspIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$verboEnEsp(com.binary_winters.projects.iverbs.VerboEnEsp value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("verboEnEsp")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.verboEnEspIndex);
                return;
            }
            if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            }
            if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            }
            row.getTable().setLink(columnInfo.verboEnEspIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.verboEnEspIndex);
            return;
        }
        if (!(RealmObject.isManaged(value) && RealmObject.isValid(value))) {
            throw new IllegalArgumentException("'value' is not a valid managed object.");
        }
        if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
            throw new IllegalArgumentException("'value' belongs to a different Realm.");
        }
        proxyState.getRow$realm().setLink(columnInfo.verboEnEspIndex, ((RealmObjectProxy)value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$cadenaDeVerbos() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.cadenaDeVerbosIndex);
    }

    @Override
    public void realmSet$cadenaDeVerbos(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'cadenaDeVerbos' to null.");
            }
            row.getTable().setString(columnInfo.cadenaDeVerbosIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'cadenaDeVerbos' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.cadenaDeVerbosIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$archivoDeAudioEnString() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.archivoDeAudioEnStringIndex);
    }

    @Override
    public void realmSet$archivoDeAudioEnString(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'archivoDeAudioEnString' to null.");
            }
            row.getTable().setString(columnInfo.archivoDeAudioEnStringIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'archivoDeAudioEnString' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.archivoDeAudioEnStringIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$nombreArchivoAudio() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nombreArchivoAudioIndex);
    }

    @Override
    public void realmSet$nombreArchivoAudio(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'nombreArchivoAudio' to null.");
            }
            row.getTable().setString(columnInfo.nombreArchivoAudioIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'nombreArchivoAudio' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.nombreArchivoAudioIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("VerboGenericoBean");
        builder.addProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addLinkedProperty("verboEnEsp", RealmFieldType.OBJECT, "VerboEnEsp");
        builder.addProperty("cadenaDeVerbos", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addProperty("archivoDeAudioEnString", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addProperty("nombreArchivoAudio", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
         return expectedObjectSchemaInfo;
    }

    public static VerboGenericoBeanColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_VerboGenericoBean")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'VerboGenericoBean' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_VerboGenericoBean");
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

        final VerboGenericoBeanColumnInfo columnInfo = new VerboGenericoBeanColumnInfo(sharedRealm, table);

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
        if (!columnTypes.containsKey("verboEnEsp")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'verboEnEsp' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("verboEnEsp") != RealmFieldType.OBJECT) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'VerboEnEsp' for field 'verboEnEsp'");
        }
        if (!sharedRealm.hasTable("class_VerboEnEsp")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_VerboEnEsp' for field 'verboEnEsp'");
        }
        Table table_1 = sharedRealm.getTable("class_VerboEnEsp");
        if (!table.getLinkTarget(columnInfo.verboEnEspIndex).hasSameSchema(table_1)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmObject for field 'verboEnEsp': '" + table.getLinkTarget(columnInfo.verboEnEspIndex).getName() + "' expected - was '" + table_1.getName() + "'");
        }
        if (!columnTypes.containsKey("cadenaDeVerbos")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'cadenaDeVerbos' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("cadenaDeVerbos") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'cadenaDeVerbos' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.cadenaDeVerbosIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'cadenaDeVerbos' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'cadenaDeVerbos' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("archivoDeAudioEnString")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'archivoDeAudioEnString' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("archivoDeAudioEnString") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'archivoDeAudioEnString' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.archivoDeAudioEnStringIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'archivoDeAudioEnString' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'archivoDeAudioEnString' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("nombreArchivoAudio")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'nombreArchivoAudio' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("nombreArchivoAudio") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'nombreArchivoAudio' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.nombreArchivoAudioIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'nombreArchivoAudio' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'nombreArchivoAudio' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_VerboGenericoBean";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.binary_winters.projects.iverbs.modelo.VerboGenericoBean createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.binary_winters.projects.iverbs.modelo.VerboGenericoBean obj = null;
        if (update) {
            Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class), false, Collections.<String> emptyList());
                    obj = new io.realm.VerboGenericoBeanRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("verboEnEsp")) {
                excludeFields.add("verboEnEsp");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.VerboGenericoBeanRealmProxy) realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.VerboGenericoBeanRealmProxy) realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("verboEnEsp")) {
            if (json.isNull("verboEnEsp")) {
                ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$verboEnEsp(null);
            } else {
                com.binary_winters.projects.iverbs.VerboEnEsp verboEnEspObj = VerboEnEspRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("verboEnEsp"), update);
                ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$verboEnEsp(verboEnEspObj);
            }
        }
        if (json.has("cadenaDeVerbos")) {
            if (json.isNull("cadenaDeVerbos")) {
                ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$cadenaDeVerbos(null);
            } else {
                ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$cadenaDeVerbos((String) json.getString("cadenaDeVerbos"));
            }
        }
        if (json.has("archivoDeAudioEnString")) {
            if (json.isNull("archivoDeAudioEnString")) {
                ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$archivoDeAudioEnString(null);
            } else {
                ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$archivoDeAudioEnString((String) json.getString("archivoDeAudioEnString"));
            }
        }
        if (json.has("nombreArchivoAudio")) {
            if (json.isNull("nombreArchivoAudio")) {
                ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$nombreArchivoAudio(null);
            } else {
                ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$nombreArchivoAudio((String) json.getString("nombreArchivoAudio"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.binary_winters.projects.iverbs.modelo.VerboGenericoBean createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.binary_winters.projects.iverbs.modelo.VerboGenericoBean obj = new com.binary_winters.projects.iverbs.modelo.VerboGenericoBean();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("verboEnEsp")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$verboEnEsp(null);
                } else {
                    com.binary_winters.projects.iverbs.VerboEnEsp verboEnEspObj = VerboEnEspRealmProxy.createUsingJsonStream(realm, reader);
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$verboEnEsp(verboEnEspObj);
                }
            } else if (name.equals("cadenaDeVerbos")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$cadenaDeVerbos(null);
                } else {
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$cadenaDeVerbos((String) reader.nextString());
                }
            } else if (name.equals("archivoDeAudioEnString")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$archivoDeAudioEnString(null);
                } else {
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$archivoDeAudioEnString((String) reader.nextString());
                }
            } else if (name.equals("nombreArchivoAudio")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$nombreArchivoAudio(null);
                } else {
                    ((VerboGenericoBeanRealmProxyInterface) obj).realmSet$nombreArchivoAudio((String) reader.nextString());
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

    public static com.binary_winters.projects.iverbs.modelo.VerboGenericoBean copyOrUpdate(Realm realm, com.binary_winters.projects.iverbs.modelo.VerboGenericoBean object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) cachedRealmObject;
        }

        com.binary_winters.projects.iverbs.modelo.VerboGenericoBean realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
            if (rowIndex != Table.NO_MATCH) {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.VerboGenericoBeanRealmProxy();
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

    public static com.binary_winters.projects.iverbs.modelo.VerboGenericoBean copy(Realm realm, com.binary_winters.projects.iverbs.modelo.VerboGenericoBean newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.binary_winters.projects.iverbs.modelo.VerboGenericoBean realmObject = realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class, ((VerboGenericoBeanRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        VerboGenericoBeanRealmProxyInterface realmObjectSource = (VerboGenericoBeanRealmProxyInterface) newObject;
        VerboGenericoBeanRealmProxyInterface realmObjectCopy = (VerboGenericoBeanRealmProxyInterface) realmObject;


        com.binary_winters.projects.iverbs.VerboEnEsp verboEnEspObj = realmObjectSource.realmGet$verboEnEsp();
        if (verboEnEspObj == null) {
            realmObjectCopy.realmSet$verboEnEsp(null);
        } else {
            com.binary_winters.projects.iverbs.VerboEnEsp cacheverboEnEsp = (com.binary_winters.projects.iverbs.VerboEnEsp) cache.get(verboEnEspObj);
            if (cacheverboEnEsp != null) {
                realmObjectCopy.realmSet$verboEnEsp(cacheverboEnEsp);
            } else {
                realmObjectCopy.realmSet$verboEnEsp(VerboEnEspRealmProxy.copyOrUpdate(realm, verboEnEspObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$cadenaDeVerbos(realmObjectSource.realmGet$cadenaDeVerbos());
        realmObjectCopy.realmSet$archivoDeAudioEnString(realmObjectSource.realmGet$archivoDeAudioEnString());
        realmObjectCopy.realmSet$nombreArchivoAudio(realmObjectSource.realmGet$nombreArchivoAudio());
        return realmObject;
    }

    public static long insert(Realm realm, com.binary_winters.projects.iverbs.modelo.VerboGenericoBean object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        long tableNativePtr = table.getNativePtr();
        VerboGenericoBeanColumnInfo columnInfo = (VerboGenericoBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);

        com.binary_winters.projects.iverbs.VerboEnEsp verboEnEspObj = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$verboEnEsp();
        if (verboEnEspObj != null) {
            Long cacheverboEnEsp = cache.get(verboEnEspObj);
            if (cacheverboEnEsp == null) {
                cacheverboEnEsp = VerboEnEspRealmProxy.insert(realm, verboEnEspObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.verboEnEspIndex, rowIndex, cacheverboEnEsp, false);
        }
        String realmGet$cadenaDeVerbos = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$cadenaDeVerbos();
        if (realmGet$cadenaDeVerbos != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cadenaDeVerbosIndex, rowIndex, realmGet$cadenaDeVerbos, false);
        }
        String realmGet$archivoDeAudioEnString = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$archivoDeAudioEnString();
        if (realmGet$archivoDeAudioEnString != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, realmGet$archivoDeAudioEnString, false);
        }
        String realmGet$nombreArchivoAudio = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$nombreArchivoAudio();
        if (realmGet$nombreArchivoAudio != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, realmGet$nombreArchivoAudio, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        long tableNativePtr = table.getNativePtr();
        VerboGenericoBeanColumnInfo columnInfo = (VerboGenericoBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.binary_winters.projects.iverbs.modelo.VerboGenericoBean object = null;
        while (objects.hasNext()) {
            object = (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);

            com.binary_winters.projects.iverbs.VerboEnEsp verboEnEspObj = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$verboEnEsp();
            if (verboEnEspObj != null) {
                Long cacheverboEnEsp = cache.get(verboEnEspObj);
                if (cacheverboEnEsp == null) {
                    cacheverboEnEsp = VerboEnEspRealmProxy.insert(realm, verboEnEspObj, cache);
                }
                table.setLink(columnInfo.verboEnEspIndex, rowIndex, cacheverboEnEsp, false);
            }
            String realmGet$cadenaDeVerbos = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$cadenaDeVerbos();
            if (realmGet$cadenaDeVerbos != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cadenaDeVerbosIndex, rowIndex, realmGet$cadenaDeVerbos, false);
            }
            String realmGet$archivoDeAudioEnString = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$archivoDeAudioEnString();
            if (realmGet$archivoDeAudioEnString != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, realmGet$archivoDeAudioEnString, false);
            }
            String realmGet$nombreArchivoAudio = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$nombreArchivoAudio();
            if (realmGet$nombreArchivoAudio != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, realmGet$nombreArchivoAudio, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.binary_winters.projects.iverbs.modelo.VerboGenericoBean object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        long tableNativePtr = table.getNativePtr();
        VerboGenericoBeanColumnInfo columnInfo = (VerboGenericoBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);

        com.binary_winters.projects.iverbs.VerboEnEsp verboEnEspObj = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$verboEnEsp();
        if (verboEnEspObj != null) {
            Long cacheverboEnEsp = cache.get(verboEnEspObj);
            if (cacheverboEnEsp == null) {
                cacheverboEnEsp = VerboEnEspRealmProxy.insertOrUpdate(realm, verboEnEspObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.verboEnEspIndex, rowIndex, cacheverboEnEsp, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.verboEnEspIndex, rowIndex);
        }
        String realmGet$cadenaDeVerbos = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$cadenaDeVerbos();
        if (realmGet$cadenaDeVerbos != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cadenaDeVerbosIndex, rowIndex, realmGet$cadenaDeVerbos, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cadenaDeVerbosIndex, rowIndex, false);
        }
        String realmGet$archivoDeAudioEnString = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$archivoDeAudioEnString();
        if (realmGet$archivoDeAudioEnString != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, realmGet$archivoDeAudioEnString, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, false);
        }
        String realmGet$nombreArchivoAudio = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$nombreArchivoAudio();
        if (realmGet$nombreArchivoAudio != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, realmGet$nombreArchivoAudio, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        long tableNativePtr = table.getNativePtr();
        VerboGenericoBeanColumnInfo columnInfo = (VerboGenericoBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.binary_winters.projects.iverbs.modelo.VerboGenericoBean object = null;
        while (objects.hasNext()) {
            object = (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((VerboGenericoBeanRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);

            com.binary_winters.projects.iverbs.VerboEnEsp verboEnEspObj = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$verboEnEsp();
            if (verboEnEspObj != null) {
                Long cacheverboEnEsp = cache.get(verboEnEspObj);
                if (cacheverboEnEsp == null) {
                    cacheverboEnEsp = VerboEnEspRealmProxy.insertOrUpdate(realm, verboEnEspObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.verboEnEspIndex, rowIndex, cacheverboEnEsp, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.verboEnEspIndex, rowIndex);
            }
            String realmGet$cadenaDeVerbos = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$cadenaDeVerbos();
            if (realmGet$cadenaDeVerbos != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cadenaDeVerbosIndex, rowIndex, realmGet$cadenaDeVerbos, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.cadenaDeVerbosIndex, rowIndex, false);
            }
            String realmGet$archivoDeAudioEnString = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$archivoDeAudioEnString();
            if (realmGet$archivoDeAudioEnString != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, realmGet$archivoDeAudioEnString, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, false);
            }
            String realmGet$nombreArchivoAudio = ((VerboGenericoBeanRealmProxyInterface) object).realmGet$nombreArchivoAudio();
            if (realmGet$nombreArchivoAudio != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, realmGet$nombreArchivoAudio, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, false);
            }
        }
    }

    public static com.binary_winters.projects.iverbs.modelo.VerboGenericoBean createDetachedCopy(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.binary_winters.projects.iverbs.modelo.VerboGenericoBean unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.binary_winters.projects.iverbs.modelo.VerboGenericoBean();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) cachedObject.object;
            }
            unmanagedObject = (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        VerboGenericoBeanRealmProxyInterface unmanagedCopy = (VerboGenericoBeanRealmProxyInterface) unmanagedObject;
        VerboGenericoBeanRealmProxyInterface realmSource = (VerboGenericoBeanRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());

        // Deep copy of verboEnEsp
        unmanagedCopy.realmSet$verboEnEsp(VerboEnEspRealmProxy.createDetachedCopy(realmSource.realmGet$verboEnEsp(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$cadenaDeVerbos(realmSource.realmGet$cadenaDeVerbos());
        unmanagedCopy.realmSet$archivoDeAudioEnString(realmSource.realmGet$archivoDeAudioEnString());
        unmanagedCopy.realmSet$nombreArchivoAudio(realmSource.realmGet$nombreArchivoAudio());
        return unmanagedObject;
    }

    static com.binary_winters.projects.iverbs.modelo.VerboGenericoBean update(Realm realm, com.binary_winters.projects.iverbs.modelo.VerboGenericoBean realmObject, com.binary_winters.projects.iverbs.modelo.VerboGenericoBean newObject, Map<RealmModel, RealmObjectProxy> cache) {
        VerboGenericoBeanRealmProxyInterface realmObjectTarget = (VerboGenericoBeanRealmProxyInterface) realmObject;
        VerboGenericoBeanRealmProxyInterface realmObjectSource = (VerboGenericoBeanRealmProxyInterface) newObject;
        com.binary_winters.projects.iverbs.VerboEnEsp verboEnEspObj = realmObjectSource.realmGet$verboEnEsp();
        if (verboEnEspObj == null) {
            realmObjectTarget.realmSet$verboEnEsp(null);
        } else {
            com.binary_winters.projects.iverbs.VerboEnEsp cacheverboEnEsp = (com.binary_winters.projects.iverbs.VerboEnEsp) cache.get(verboEnEspObj);
            if (cacheverboEnEsp != null) {
                realmObjectTarget.realmSet$verboEnEsp(cacheverboEnEsp);
            } else {
                realmObjectTarget.realmSet$verboEnEsp(VerboEnEspRealmProxy.copyOrUpdate(realm, verboEnEspObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$cadenaDeVerbos(realmObjectSource.realmGet$cadenaDeVerbos());
        realmObjectTarget.realmSet$archivoDeAudioEnString(realmObjectSource.realmGet$archivoDeAudioEnString());
        realmObjectTarget.realmSet$nombreArchivoAudio(realmObjectSource.realmGet$nombreArchivoAudio());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("VerboGenericoBean = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{verboEnEsp:");
        stringBuilder.append(realmGet$verboEnEsp() != null ? "VerboEnEsp" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cadenaDeVerbos:");
        stringBuilder.append(realmGet$cadenaDeVerbos());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{archivoDeAudioEnString:");
        stringBuilder.append(realmGet$archivoDeAudioEnString());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nombreArchivoAudio:");
        stringBuilder.append(realmGet$nombreArchivoAudio());
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
        VerboGenericoBeanRealmProxy aVerboGenericoBean = (VerboGenericoBeanRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aVerboGenericoBean.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aVerboGenericoBean.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aVerboGenericoBean.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
