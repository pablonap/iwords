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
public class PalabraAgregadaBeanRealmProxy extends com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean
    implements RealmObjectProxy, PalabraAgregadaBeanRealmProxyInterface {

    static final class PalabraAgregadaBeanColumnInfo extends ColumnInfo {
        long idIndex;
        long palabraEnEspIndex;
        long cadenaEnInglesIndex;
        long markedWithStarIndex;
        long archivoDeAudioEnStringIndex;
        long nombreArchivoAudioIndex;

        PalabraAgregadaBeanColumnInfo(SharedRealm realm, Table table) {
            super(6);
            this.idIndex = addColumnDetails(table, "id", RealmFieldType.INTEGER);
            this.palabraEnEspIndex = addColumnDetails(table, "palabraEnEsp", RealmFieldType.STRING);
            this.cadenaEnInglesIndex = addColumnDetails(table, "cadenaEnIngles", RealmFieldType.STRING);
            this.markedWithStarIndex = addColumnDetails(table, "markedWithStar", RealmFieldType.BOOLEAN);
            this.archivoDeAudioEnStringIndex = addColumnDetails(table, "archivoDeAudioEnString", RealmFieldType.STRING);
            this.nombreArchivoAudioIndex = addColumnDetails(table, "nombreArchivoAudio", RealmFieldType.STRING);
        }

        PalabraAgregadaBeanColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new PalabraAgregadaBeanColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final PalabraAgregadaBeanColumnInfo src = (PalabraAgregadaBeanColumnInfo) rawSrc;
            final PalabraAgregadaBeanColumnInfo dst = (PalabraAgregadaBeanColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.palabraEnEspIndex = src.palabraEnEspIndex;
            dst.cadenaEnInglesIndex = src.cadenaEnInglesIndex;
            dst.markedWithStarIndex = src.markedWithStarIndex;
            dst.archivoDeAudioEnStringIndex = src.archivoDeAudioEnStringIndex;
            dst.nombreArchivoAudioIndex = src.nombreArchivoAudioIndex;
        }
    }

    private PalabraAgregadaBeanColumnInfo columnInfo;
    private ProxyState<com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean> proxyState;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("palabraEnEsp");
        fieldNames.add("cadenaEnIngles");
        fieldNames.add("markedWithStar");
        fieldNames.add("archivoDeAudioEnString");
        fieldNames.add("nombreArchivoAudio");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    PalabraAgregadaBeanRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (PalabraAgregadaBeanColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean>(this);
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
    public String realmGet$palabraEnEsp() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.palabraEnEspIndex);
    }

    @Override
    public void realmSet$palabraEnEsp(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'palabraEnEsp' to null.");
            }
            row.getTable().setString(columnInfo.palabraEnEspIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'palabraEnEsp' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.palabraEnEspIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$cadenaEnIngles() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.cadenaEnInglesIndex);
    }

    @Override
    public void realmSet$cadenaEnIngles(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'cadenaEnIngles' to null.");
            }
            row.getTable().setString(columnInfo.cadenaEnInglesIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'cadenaEnIngles' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.cadenaEnInglesIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Boolean realmGet$markedWithStar() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.markedWithStarIndex)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.markedWithStarIndex);
    }

    @Override
    public void realmSet$markedWithStar(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.markedWithStarIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.markedWithStarIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.markedWithStarIndex);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.markedWithStarIndex, value);
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
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("PalabraAgregadaBean");
        builder.addProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addProperty("palabraEnEsp", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addProperty("cadenaEnIngles", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addProperty("markedWithStar", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addProperty("archivoDeAudioEnString", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addProperty("nombreArchivoAudio", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
         return expectedObjectSchemaInfo;
    }

    public static PalabraAgregadaBeanColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_PalabraAgregadaBean")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'PalabraAgregadaBean' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_PalabraAgregadaBean");
        final long columnCount = table.getColumnCount();
        if (columnCount != 6) {
            if (columnCount < 6) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 6 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 6 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 6 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final PalabraAgregadaBeanColumnInfo columnInfo = new PalabraAgregadaBeanColumnInfo(sharedRealm, table);

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
        if (!columnTypes.containsKey("palabraEnEsp")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'palabraEnEsp' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("palabraEnEsp") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'palabraEnEsp' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.palabraEnEspIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'palabraEnEsp' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'palabraEnEsp' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("cadenaEnIngles")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'cadenaEnIngles' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("cadenaEnIngles") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'cadenaEnIngles' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.cadenaEnInglesIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'cadenaEnIngles' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'cadenaEnIngles' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("markedWithStar")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'markedWithStar' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("markedWithStar") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Boolean' for field 'markedWithStar' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.markedWithStarIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'markedWithStar' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'markedWithStar' or migrate using RealmObjectSchema.setNullable().");
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
        return "class_PalabraAgregadaBean";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean obj = null;
        if (update) {
            Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class), false, Collections.<String> emptyList());
                    obj = new io.realm.PalabraAgregadaBeanRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.PalabraAgregadaBeanRealmProxy) realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.PalabraAgregadaBeanRealmProxy) realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("palabraEnEsp")) {
            if (json.isNull("palabraEnEsp")) {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$palabraEnEsp(null);
            } else {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$palabraEnEsp((String) json.getString("palabraEnEsp"));
            }
        }
        if (json.has("cadenaEnIngles")) {
            if (json.isNull("cadenaEnIngles")) {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$cadenaEnIngles(null);
            } else {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$cadenaEnIngles((String) json.getString("cadenaEnIngles"));
            }
        }
        if (json.has("markedWithStar")) {
            if (json.isNull("markedWithStar")) {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$markedWithStar(null);
            } else {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$markedWithStar((boolean) json.getBoolean("markedWithStar"));
            }
        }
        if (json.has("archivoDeAudioEnString")) {
            if (json.isNull("archivoDeAudioEnString")) {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$archivoDeAudioEnString(null);
            } else {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$archivoDeAudioEnString((String) json.getString("archivoDeAudioEnString"));
            }
        }
        if (json.has("nombreArchivoAudio")) {
            if (json.isNull("nombreArchivoAudio")) {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$nombreArchivoAudio(null);
            } else {
                ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$nombreArchivoAudio((String) json.getString("nombreArchivoAudio"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean obj = new com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("palabraEnEsp")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$palabraEnEsp(null);
                } else {
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$palabraEnEsp((String) reader.nextString());
                }
            } else if (name.equals("cadenaEnIngles")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$cadenaEnIngles(null);
                } else {
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$cadenaEnIngles((String) reader.nextString());
                }
            } else if (name.equals("markedWithStar")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$markedWithStar(null);
                } else {
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$markedWithStar((boolean) reader.nextBoolean());
                }
            } else if (name.equals("archivoDeAudioEnString")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$archivoDeAudioEnString(null);
                } else {
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$archivoDeAudioEnString((String) reader.nextString());
                }
            } else if (name.equals("nombreArchivoAudio")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$nombreArchivoAudio(null);
                } else {
                    ((PalabraAgregadaBeanRealmProxyInterface) obj).realmSet$nombreArchivoAudio((String) reader.nextString());
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

    public static com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean copyOrUpdate(Realm realm, com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) cachedRealmObject;
        }

        com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
            if (rowIndex != Table.NO_MATCH) {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.PalabraAgregadaBeanRealmProxy();
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

    public static com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean copy(Realm realm, com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean realmObject = realm.createObjectInternal(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class, ((PalabraAgregadaBeanRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        PalabraAgregadaBeanRealmProxyInterface realmObjectSource = (PalabraAgregadaBeanRealmProxyInterface) newObject;
        PalabraAgregadaBeanRealmProxyInterface realmObjectCopy = (PalabraAgregadaBeanRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$palabraEnEsp(realmObjectSource.realmGet$palabraEnEsp());
        realmObjectCopy.realmSet$cadenaEnIngles(realmObjectSource.realmGet$cadenaEnIngles());
        realmObjectCopy.realmSet$markedWithStar(realmObjectSource.realmGet$markedWithStar());
        realmObjectCopy.realmSet$archivoDeAudioEnString(realmObjectSource.realmGet$archivoDeAudioEnString());
        realmObjectCopy.realmSet$nombreArchivoAudio(realmObjectSource.realmGet$nombreArchivoAudio());
        return realmObject;
    }

    public static long insert(Realm realm, com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        long tableNativePtr = table.getNativePtr();
        PalabraAgregadaBeanColumnInfo columnInfo = (PalabraAgregadaBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$palabraEnEsp = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$palabraEnEsp();
        if (realmGet$palabraEnEsp != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.palabraEnEspIndex, rowIndex, realmGet$palabraEnEsp, false);
        }
        String realmGet$cadenaEnIngles = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$cadenaEnIngles();
        if (realmGet$cadenaEnIngles != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cadenaEnInglesIndex, rowIndex, realmGet$cadenaEnIngles, false);
        }
        Boolean realmGet$markedWithStar = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$markedWithStar();
        if (realmGet$markedWithStar != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, realmGet$markedWithStar, false);
        }
        String realmGet$archivoDeAudioEnString = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$archivoDeAudioEnString();
        if (realmGet$archivoDeAudioEnString != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, realmGet$archivoDeAudioEnString, false);
        }
        String realmGet$nombreArchivoAudio = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$nombreArchivoAudio();
        if (realmGet$nombreArchivoAudio != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, realmGet$nombreArchivoAudio, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        long tableNativePtr = table.getNativePtr();
        PalabraAgregadaBeanColumnInfo columnInfo = (PalabraAgregadaBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean object = null;
        while (objects.hasNext()) {
            object = (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$palabraEnEsp = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$palabraEnEsp();
            if (realmGet$palabraEnEsp != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.palabraEnEspIndex, rowIndex, realmGet$palabraEnEsp, false);
            }
            String realmGet$cadenaEnIngles = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$cadenaEnIngles();
            if (realmGet$cadenaEnIngles != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cadenaEnInglesIndex, rowIndex, realmGet$cadenaEnIngles, false);
            }
            Boolean realmGet$markedWithStar = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$markedWithStar();
            if (realmGet$markedWithStar != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, realmGet$markedWithStar, false);
            }
            String realmGet$archivoDeAudioEnString = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$archivoDeAudioEnString();
            if (realmGet$archivoDeAudioEnString != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, realmGet$archivoDeAudioEnString, false);
            }
            String realmGet$nombreArchivoAudio = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$nombreArchivoAudio();
            if (realmGet$nombreArchivoAudio != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, realmGet$nombreArchivoAudio, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        long tableNativePtr = table.getNativePtr();
        PalabraAgregadaBeanColumnInfo columnInfo = (PalabraAgregadaBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        String realmGet$palabraEnEsp = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$palabraEnEsp();
        if (realmGet$palabraEnEsp != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.palabraEnEspIndex, rowIndex, realmGet$palabraEnEsp, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.palabraEnEspIndex, rowIndex, false);
        }
        String realmGet$cadenaEnIngles = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$cadenaEnIngles();
        if (realmGet$cadenaEnIngles != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cadenaEnInglesIndex, rowIndex, realmGet$cadenaEnIngles, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cadenaEnInglesIndex, rowIndex, false);
        }
        Boolean realmGet$markedWithStar = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$markedWithStar();
        if (realmGet$markedWithStar != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, realmGet$markedWithStar, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, false);
        }
        String realmGet$archivoDeAudioEnString = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$archivoDeAudioEnString();
        if (realmGet$archivoDeAudioEnString != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, realmGet$archivoDeAudioEnString, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, false);
        }
        String realmGet$nombreArchivoAudio = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$nombreArchivoAudio();
        if (realmGet$nombreArchivoAudio != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, realmGet$nombreArchivoAudio, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        long tableNativePtr = table.getNativePtr();
        PalabraAgregadaBeanColumnInfo columnInfo = (PalabraAgregadaBeanColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean object = null;
        while (objects.hasNext()) {
            object = (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            String realmGet$palabraEnEsp = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$palabraEnEsp();
            if (realmGet$palabraEnEsp != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.palabraEnEspIndex, rowIndex, realmGet$palabraEnEsp, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.palabraEnEspIndex, rowIndex, false);
            }
            String realmGet$cadenaEnIngles = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$cadenaEnIngles();
            if (realmGet$cadenaEnIngles != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cadenaEnInglesIndex, rowIndex, realmGet$cadenaEnIngles, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.cadenaEnInglesIndex, rowIndex, false);
            }
            Boolean realmGet$markedWithStar = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$markedWithStar();
            if (realmGet$markedWithStar != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, realmGet$markedWithStar, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, false);
            }
            String realmGet$archivoDeAudioEnString = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$archivoDeAudioEnString();
            if (realmGet$archivoDeAudioEnString != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, realmGet$archivoDeAudioEnString, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.archivoDeAudioEnStringIndex, rowIndex, false);
            }
            String realmGet$nombreArchivoAudio = ((PalabraAgregadaBeanRealmProxyInterface) object).realmGet$nombreArchivoAudio();
            if (realmGet$nombreArchivoAudio != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, realmGet$nombreArchivoAudio, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nombreArchivoAudioIndex, rowIndex, false);
            }
        }
    }

    public static com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean createDetachedCopy(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) cachedObject.object;
            }
            unmanagedObject = (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        PalabraAgregadaBeanRealmProxyInterface unmanagedCopy = (PalabraAgregadaBeanRealmProxyInterface) unmanagedObject;
        PalabraAgregadaBeanRealmProxyInterface realmSource = (PalabraAgregadaBeanRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$palabraEnEsp(realmSource.realmGet$palabraEnEsp());
        unmanagedCopy.realmSet$cadenaEnIngles(realmSource.realmGet$cadenaEnIngles());
        unmanagedCopy.realmSet$markedWithStar(realmSource.realmGet$markedWithStar());
        unmanagedCopy.realmSet$archivoDeAudioEnString(realmSource.realmGet$archivoDeAudioEnString());
        unmanagedCopy.realmSet$nombreArchivoAudio(realmSource.realmGet$nombreArchivoAudio());
        return unmanagedObject;
    }

    static com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean update(Realm realm, com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean realmObject, com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean newObject, Map<RealmModel, RealmObjectProxy> cache) {
        PalabraAgregadaBeanRealmProxyInterface realmObjectTarget = (PalabraAgregadaBeanRealmProxyInterface) realmObject;
        PalabraAgregadaBeanRealmProxyInterface realmObjectSource = (PalabraAgregadaBeanRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$palabraEnEsp(realmObjectSource.realmGet$palabraEnEsp());
        realmObjectTarget.realmSet$cadenaEnIngles(realmObjectSource.realmGet$cadenaEnIngles());
        realmObjectTarget.realmSet$markedWithStar(realmObjectSource.realmGet$markedWithStar());
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
        StringBuilder stringBuilder = new StringBuilder("PalabraAgregadaBean = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{palabraEnEsp:");
        stringBuilder.append(realmGet$palabraEnEsp());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cadenaEnIngles:");
        stringBuilder.append(realmGet$cadenaEnIngles());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{markedWithStar:");
        stringBuilder.append(realmGet$markedWithStar() != null ? realmGet$markedWithStar() : "null");
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
        PalabraAgregadaBeanRealmProxy aPalabraAgregadaBean = (PalabraAgregadaBeanRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPalabraAgregadaBean.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPalabraAgregadaBean.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPalabraAgregadaBean.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
