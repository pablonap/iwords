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
public class VerboEnEspRealmProxy extends com.binary_winters.projects.iverbs.VerboEnEsp
    implements RealmObjectProxy, VerboEnEspRealmProxyInterface {

    static final class VerboEnEspColumnInfo extends ColumnInfo {
        long idIndex;
        long nombreDeVerboIndex;
        long VERBO_DESDE_APPIndex;
        long markedWithStarIndex;

        VerboEnEspColumnInfo(SharedRealm realm, Table table) {
            super(4);
            this.idIndex = addColumnDetails(table, "id", RealmFieldType.INTEGER);
            this.nombreDeVerboIndex = addColumnDetails(table, "nombreDeVerbo", RealmFieldType.STRING);
            this.VERBO_DESDE_APPIndex = addColumnDetails(table, "VERBO_DESDE_APP", RealmFieldType.BOOLEAN);
            this.markedWithStarIndex = addColumnDetails(table, "markedWithStar", RealmFieldType.BOOLEAN);
        }

        VerboEnEspColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new VerboEnEspColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final VerboEnEspColumnInfo src = (VerboEnEspColumnInfo) rawSrc;
            final VerboEnEspColumnInfo dst = (VerboEnEspColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.nombreDeVerboIndex = src.nombreDeVerboIndex;
            dst.VERBO_DESDE_APPIndex = src.VERBO_DESDE_APPIndex;
            dst.markedWithStarIndex = src.markedWithStarIndex;
        }
    }

    private VerboEnEspColumnInfo columnInfo;
    private ProxyState<com.binary_winters.projects.iverbs.VerboEnEsp> proxyState;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("nombreDeVerbo");
        fieldNames.add("VERBO_DESDE_APP");
        fieldNames.add("markedWithStar");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    VerboEnEspRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (VerboEnEspColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.binary_winters.projects.iverbs.VerboEnEsp>(this);
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
    public String realmGet$nombreDeVerbo() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nombreDeVerboIndex);
    }

    @Override
    public void realmSet$nombreDeVerbo(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nombreDeVerboIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nombreDeVerboIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nombreDeVerboIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nombreDeVerboIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Boolean realmGet$VERBO_DESDE_APP() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.VERBO_DESDE_APPIndex)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.VERBO_DESDE_APPIndex);
    }

    @Override
    public void realmSet$VERBO_DESDE_APP(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.VERBO_DESDE_APPIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.VERBO_DESDE_APPIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.VERBO_DESDE_APPIndex);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.VERBO_DESDE_APPIndex, value);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("VerboEnEsp");
        builder.addProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addProperty("nombreDeVerbo", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addProperty("VERBO_DESDE_APP", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addProperty("markedWithStar", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
         return expectedObjectSchemaInfo;
    }

    public static VerboEnEspColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_VerboEnEsp")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'VerboEnEsp' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_VerboEnEsp");
        final long columnCount = table.getColumnCount();
        if (columnCount != 4) {
            if (columnCount < 4) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final VerboEnEspColumnInfo columnInfo = new VerboEnEspColumnInfo(sharedRealm, table);

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
        if (!columnTypes.containsKey("nombreDeVerbo")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'nombreDeVerbo' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("nombreDeVerbo") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'nombreDeVerbo' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.nombreDeVerboIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'nombreDeVerbo' is required. Either set @Required to field 'nombreDeVerbo' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("VERBO_DESDE_APP")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'VERBO_DESDE_APP' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("VERBO_DESDE_APP") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Boolean' for field 'VERBO_DESDE_APP' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.VERBO_DESDE_APPIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'VERBO_DESDE_APP' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'VERBO_DESDE_APP' or migrate using RealmObjectSchema.setNullable().");
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

        return columnInfo;
    }

    public static String getTableName() {
        return "class_VerboEnEsp";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.binary_winters.projects.iverbs.VerboEnEsp createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.binary_winters.projects.iverbs.VerboEnEsp obj = null;
        if (update) {
            Table table = realm.getTable(com.binary_winters.projects.iverbs.VerboEnEsp.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.VerboEnEsp.class), false, Collections.<String> emptyList());
                    obj = new io.realm.VerboEnEspRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.VerboEnEspRealmProxy) realm.createObjectInternal(com.binary_winters.projects.iverbs.VerboEnEsp.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.VerboEnEspRealmProxy) realm.createObjectInternal(com.binary_winters.projects.iverbs.VerboEnEsp.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("nombreDeVerbo")) {
            if (json.isNull("nombreDeVerbo")) {
                ((VerboEnEspRealmProxyInterface) obj).realmSet$nombreDeVerbo(null);
            } else {
                ((VerboEnEspRealmProxyInterface) obj).realmSet$nombreDeVerbo((String) json.getString("nombreDeVerbo"));
            }
        }
        if (json.has("VERBO_DESDE_APP")) {
            if (json.isNull("VERBO_DESDE_APP")) {
                ((VerboEnEspRealmProxyInterface) obj).realmSet$VERBO_DESDE_APP(null);
            } else {
                ((VerboEnEspRealmProxyInterface) obj).realmSet$VERBO_DESDE_APP((boolean) json.getBoolean("VERBO_DESDE_APP"));
            }
        }
        if (json.has("markedWithStar")) {
            if (json.isNull("markedWithStar")) {
                ((VerboEnEspRealmProxyInterface) obj).realmSet$markedWithStar(null);
            } else {
                ((VerboEnEspRealmProxyInterface) obj).realmSet$markedWithStar((boolean) json.getBoolean("markedWithStar"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.binary_winters.projects.iverbs.VerboEnEsp createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.binary_winters.projects.iverbs.VerboEnEsp obj = new com.binary_winters.projects.iverbs.VerboEnEsp();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((VerboEnEspRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("nombreDeVerbo")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VerboEnEspRealmProxyInterface) obj).realmSet$nombreDeVerbo(null);
                } else {
                    ((VerboEnEspRealmProxyInterface) obj).realmSet$nombreDeVerbo((String) reader.nextString());
                }
            } else if (name.equals("VERBO_DESDE_APP")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VerboEnEspRealmProxyInterface) obj).realmSet$VERBO_DESDE_APP(null);
                } else {
                    ((VerboEnEspRealmProxyInterface) obj).realmSet$VERBO_DESDE_APP((boolean) reader.nextBoolean());
                }
            } else if (name.equals("markedWithStar")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VerboEnEspRealmProxyInterface) obj).realmSet$markedWithStar(null);
                } else {
                    ((VerboEnEspRealmProxyInterface) obj).realmSet$markedWithStar((boolean) reader.nextBoolean());
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

    public static com.binary_winters.projects.iverbs.VerboEnEsp copyOrUpdate(Realm realm, com.binary_winters.projects.iverbs.VerboEnEsp object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.binary_winters.projects.iverbs.VerboEnEsp) cachedRealmObject;
        }

        com.binary_winters.projects.iverbs.VerboEnEsp realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.binary_winters.projects.iverbs.VerboEnEsp.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
            if (rowIndex != Table.NO_MATCH) {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.VerboEnEsp.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.VerboEnEspRealmProxy();
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

    public static com.binary_winters.projects.iverbs.VerboEnEsp copy(Realm realm, com.binary_winters.projects.iverbs.VerboEnEsp newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.binary_winters.projects.iverbs.VerboEnEsp) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.binary_winters.projects.iverbs.VerboEnEsp realmObject = realm.createObjectInternal(com.binary_winters.projects.iverbs.VerboEnEsp.class, ((VerboEnEspRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        VerboEnEspRealmProxyInterface realmObjectSource = (VerboEnEspRealmProxyInterface) newObject;
        VerboEnEspRealmProxyInterface realmObjectCopy = (VerboEnEspRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$nombreDeVerbo(realmObjectSource.realmGet$nombreDeVerbo());
        realmObjectCopy.realmSet$VERBO_DESDE_APP(realmObjectSource.realmGet$VERBO_DESDE_APP());
        realmObjectCopy.realmSet$markedWithStar(realmObjectSource.realmGet$markedWithStar());
        return realmObject;
    }

    public static long insert(Realm realm, com.binary_winters.projects.iverbs.VerboEnEsp object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        long tableNativePtr = table.getNativePtr();
        VerboEnEspColumnInfo columnInfo = (VerboEnEspColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((VerboEnEspRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$nombreDeVerbo = ((VerboEnEspRealmProxyInterface) object).realmGet$nombreDeVerbo();
        if (realmGet$nombreDeVerbo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreDeVerboIndex, rowIndex, realmGet$nombreDeVerbo, false);
        }
        Boolean realmGet$VERBO_DESDE_APP = ((VerboEnEspRealmProxyInterface) object).realmGet$VERBO_DESDE_APP();
        if (realmGet$VERBO_DESDE_APP != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.VERBO_DESDE_APPIndex, rowIndex, realmGet$VERBO_DESDE_APP, false);
        }
        Boolean realmGet$markedWithStar = ((VerboEnEspRealmProxyInterface) object).realmGet$markedWithStar();
        if (realmGet$markedWithStar != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, realmGet$markedWithStar, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        long tableNativePtr = table.getNativePtr();
        VerboEnEspColumnInfo columnInfo = (VerboEnEspColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.binary_winters.projects.iverbs.VerboEnEsp object = null;
        while (objects.hasNext()) {
            object = (com.binary_winters.projects.iverbs.VerboEnEsp) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((VerboEnEspRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$nombreDeVerbo = ((VerboEnEspRealmProxyInterface) object).realmGet$nombreDeVerbo();
            if (realmGet$nombreDeVerbo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreDeVerboIndex, rowIndex, realmGet$nombreDeVerbo, false);
            }
            Boolean realmGet$VERBO_DESDE_APP = ((VerboEnEspRealmProxyInterface) object).realmGet$VERBO_DESDE_APP();
            if (realmGet$VERBO_DESDE_APP != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.VERBO_DESDE_APPIndex, rowIndex, realmGet$VERBO_DESDE_APP, false);
            }
            Boolean realmGet$markedWithStar = ((VerboEnEspRealmProxyInterface) object).realmGet$markedWithStar();
            if (realmGet$markedWithStar != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, realmGet$markedWithStar, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.binary_winters.projects.iverbs.VerboEnEsp object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        long tableNativePtr = table.getNativePtr();
        VerboEnEspColumnInfo columnInfo = (VerboEnEspColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((VerboEnEspRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        String realmGet$nombreDeVerbo = ((VerboEnEspRealmProxyInterface) object).realmGet$nombreDeVerbo();
        if (realmGet$nombreDeVerbo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreDeVerboIndex, rowIndex, realmGet$nombreDeVerbo, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nombreDeVerboIndex, rowIndex, false);
        }
        Boolean realmGet$VERBO_DESDE_APP = ((VerboEnEspRealmProxyInterface) object).realmGet$VERBO_DESDE_APP();
        if (realmGet$VERBO_DESDE_APP != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.VERBO_DESDE_APPIndex, rowIndex, realmGet$VERBO_DESDE_APP, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.VERBO_DESDE_APPIndex, rowIndex, false);
        }
        Boolean realmGet$markedWithStar = ((VerboEnEspRealmProxyInterface) object).realmGet$markedWithStar();
        if (realmGet$markedWithStar != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, realmGet$markedWithStar, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        long tableNativePtr = table.getNativePtr();
        VerboEnEspColumnInfo columnInfo = (VerboEnEspColumnInfo) realm.schema.getColumnInfo(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.binary_winters.projects.iverbs.VerboEnEsp object = null;
        while (objects.hasNext()) {
            object = (com.binary_winters.projects.iverbs.VerboEnEsp) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((VerboEnEspRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((VerboEnEspRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            String realmGet$nombreDeVerbo = ((VerboEnEspRealmProxyInterface) object).realmGet$nombreDeVerbo();
            if (realmGet$nombreDeVerbo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreDeVerboIndex, rowIndex, realmGet$nombreDeVerbo, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nombreDeVerboIndex, rowIndex, false);
            }
            Boolean realmGet$VERBO_DESDE_APP = ((VerboEnEspRealmProxyInterface) object).realmGet$VERBO_DESDE_APP();
            if (realmGet$VERBO_DESDE_APP != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.VERBO_DESDE_APPIndex, rowIndex, realmGet$VERBO_DESDE_APP, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.VERBO_DESDE_APPIndex, rowIndex, false);
            }
            Boolean realmGet$markedWithStar = ((VerboEnEspRealmProxyInterface) object).realmGet$markedWithStar();
            if (realmGet$markedWithStar != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, realmGet$markedWithStar, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.markedWithStarIndex, rowIndex, false);
            }
        }
    }

    public static com.binary_winters.projects.iverbs.VerboEnEsp createDetachedCopy(com.binary_winters.projects.iverbs.VerboEnEsp realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.binary_winters.projects.iverbs.VerboEnEsp unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.binary_winters.projects.iverbs.VerboEnEsp();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.binary_winters.projects.iverbs.VerboEnEsp) cachedObject.object;
            }
            unmanagedObject = (com.binary_winters.projects.iverbs.VerboEnEsp) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        VerboEnEspRealmProxyInterface unmanagedCopy = (VerboEnEspRealmProxyInterface) unmanagedObject;
        VerboEnEspRealmProxyInterface realmSource = (VerboEnEspRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$nombreDeVerbo(realmSource.realmGet$nombreDeVerbo());
        unmanagedCopy.realmSet$VERBO_DESDE_APP(realmSource.realmGet$VERBO_DESDE_APP());
        unmanagedCopy.realmSet$markedWithStar(realmSource.realmGet$markedWithStar());
        return unmanagedObject;
    }

    static com.binary_winters.projects.iverbs.VerboEnEsp update(Realm realm, com.binary_winters.projects.iverbs.VerboEnEsp realmObject, com.binary_winters.projects.iverbs.VerboEnEsp newObject, Map<RealmModel, RealmObjectProxy> cache) {
        VerboEnEspRealmProxyInterface realmObjectTarget = (VerboEnEspRealmProxyInterface) realmObject;
        VerboEnEspRealmProxyInterface realmObjectSource = (VerboEnEspRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$nombreDeVerbo(realmObjectSource.realmGet$nombreDeVerbo());
        realmObjectTarget.realmSet$VERBO_DESDE_APP(realmObjectSource.realmGet$VERBO_DESDE_APP());
        realmObjectTarget.realmSet$markedWithStar(realmObjectSource.realmGet$markedWithStar());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("VerboEnEsp = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nombreDeVerbo:");
        stringBuilder.append(realmGet$nombreDeVerbo() != null ? realmGet$nombreDeVerbo() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{VERBO_DESDE_APP:");
        stringBuilder.append(realmGet$VERBO_DESDE_APP() != null ? realmGet$VERBO_DESDE_APP() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{markedWithStar:");
        stringBuilder.append(realmGet$markedWithStar() != null ? realmGet$markedWithStar() : "null");
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
        VerboEnEspRealmProxy aVerboEnEsp = (VerboEnEspRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aVerboEnEsp.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aVerboEnEsp.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aVerboEnEsp.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
