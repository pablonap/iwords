package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class);
        modelClasses.add(com.binary_winters.projects.iverbs.VerboEnEsp.class);
        modelClasses.add(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class);
        modelClasses.add(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class);
        modelClasses.add(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap<Class<? extends RealmModel>, OsObjectSchemaInfo>();
        infoMap.put(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class, io.realm.SituacionGenericaRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.binary_winters.projects.iverbs.VerboEnEsp.class, io.realm.VerboEnEspRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class, io.realm.PalabraAgregadaBeanRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class, io.realm.VerboGenericoBeanRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class, io.realm.HistorialDePartidaBeanRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm, boolean allowExtraColumns) {
        checkClass(clazz);

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            return io.realm.SituacionGenericaRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            return io.realm.VerboEnEspRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            return io.realm.PalabraAgregadaBeanRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            return io.realm.VerboGenericoBeanRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            return io.realm.HistorialDePartidaBeanRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            return io.realm.SituacionGenericaRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            return io.realm.VerboEnEspRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            return io.realm.PalabraAgregadaBeanRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            return io.realm.VerboGenericoBeanRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            return io.realm.HistorialDePartidaBeanRealmProxy.getFieldNames();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            return io.realm.SituacionGenericaRealmProxy.getTableName();
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            return io.realm.VerboEnEspRealmProxy.getTableName();
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            return io.realm.PalabraAgregadaBeanRealmProxy.getTableName();
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            return io.realm.VerboGenericoBeanRealmProxy.getTableName();
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            return io.realm.HistorialDePartidaBeanRealmProxy.getTableName();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
                return clazz.cast(new io.realm.SituacionGenericaRealmProxy());
            }
            if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
                return clazz.cast(new io.realm.VerboEnEspRealmProxy());
            }
            if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
                return clazz.cast(new io.realm.PalabraAgregadaBeanRealmProxy());
            }
            if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
                return clazz.cast(new io.realm.VerboGenericoBeanRealmProxy());
            }
            if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
                return clazz.cast(new io.realm.HistorialDePartidaBeanRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            return clazz.cast(io.realm.SituacionGenericaRealmProxy.copyOrUpdate(realm, (com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica) obj, update, cache));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            return clazz.cast(io.realm.VerboEnEspRealmProxy.copyOrUpdate(realm, (com.binary_winters.projects.iverbs.VerboEnEsp) obj, update, cache));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            return clazz.cast(io.realm.PalabraAgregadaBeanRealmProxy.copyOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) obj, update, cache));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            return clazz.cast(io.realm.VerboGenericoBeanRealmProxy.copyOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) obj, update, cache));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            return clazz.cast(io.realm.HistorialDePartidaBeanRealmProxy.copyOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) obj, update, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            io.realm.SituacionGenericaRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica) object, cache);
        } else if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            io.realm.VerboEnEspRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.VerboEnEsp) object, cache);
        } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            io.realm.PalabraAgregadaBeanRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) object, cache);
        } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            io.realm.VerboGenericoBeanRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) object, cache);
        } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            io.realm.HistorialDePartidaBeanRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
                io.realm.SituacionGenericaRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica) object, cache);
            } else if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
                io.realm.VerboEnEspRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.VerboEnEsp) object, cache);
            } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
                io.realm.PalabraAgregadaBeanRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) object, cache);
            } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
                io.realm.VerboGenericoBeanRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) object, cache);
            } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
                io.realm.HistorialDePartidaBeanRealmProxy.insert(realm, (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
                    io.realm.SituacionGenericaRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
                    io.realm.VerboEnEspRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
                    io.realm.PalabraAgregadaBeanRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
                    io.realm.VerboGenericoBeanRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
                    io.realm.HistorialDePartidaBeanRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            io.realm.SituacionGenericaRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica) obj, cache);
        } else if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            io.realm.VerboEnEspRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.VerboEnEsp) obj, cache);
        } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            io.realm.PalabraAgregadaBeanRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) obj, cache);
        } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            io.realm.VerboGenericoBeanRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) obj, cache);
        } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            io.realm.HistorialDePartidaBeanRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
                io.realm.SituacionGenericaRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica) object, cache);
            } else if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
                io.realm.VerboEnEspRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.VerboEnEsp) object, cache);
            } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
                io.realm.PalabraAgregadaBeanRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) object, cache);
            } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
                io.realm.VerboGenericoBeanRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) object, cache);
            } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
                io.realm.HistorialDePartidaBeanRealmProxy.insertOrUpdate(realm, (com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
                    io.realm.SituacionGenericaRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
                    io.realm.VerboEnEspRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
                    io.realm.PalabraAgregadaBeanRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
                    io.realm.VerboGenericoBeanRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
                    io.realm.HistorialDePartidaBeanRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            return clazz.cast(io.realm.SituacionGenericaRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            return clazz.cast(io.realm.VerboEnEspRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            return clazz.cast(io.realm.PalabraAgregadaBeanRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            return clazz.cast(io.realm.VerboGenericoBeanRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            return clazz.cast(io.realm.HistorialDePartidaBeanRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            return clazz.cast(io.realm.SituacionGenericaRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            return clazz.cast(io.realm.VerboEnEspRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            return clazz.cast(io.realm.PalabraAgregadaBeanRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            return clazz.cast(io.realm.VerboGenericoBeanRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            return clazz.cast(io.realm.HistorialDePartidaBeanRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica.class)) {
            return clazz.cast(io.realm.SituacionGenericaRealmProxy.createDetachedCopy((com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.VerboEnEsp.class)) {
            return clazz.cast(io.realm.VerboEnEspRealmProxy.createDetachedCopy((com.binary_winters.projects.iverbs.VerboEnEsp) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean.class)) {
            return clazz.cast(io.realm.PalabraAgregadaBeanRealmProxy.createDetachedCopy((com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.VerboGenericoBean.class)) {
            return clazz.cast(io.realm.VerboGenericoBeanRealmProxy.createDetachedCopy((com.binary_winters.projects.iverbs.modelo.VerboGenericoBean) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean.class)) {
            return clazz.cast(io.realm.HistorialDePartidaBeanRealmProxy.createDetachedCopy((com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

}
