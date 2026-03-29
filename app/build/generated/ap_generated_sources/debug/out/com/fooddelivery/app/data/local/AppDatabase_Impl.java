package com.fooddelivery.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile RestaurantDao _restaurantDao;

  private volatile DishDao _dishDao;

  private volatile CartDao _cartDao;

  private volatile AddressDao _addressDao;

  private volatile OrderDao _orderDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `restaurants` (`id` INTEGER NOT NULL, `name` TEXT, `englishName` TEXT, `imageUrl` TEXT, `rating` REAL NOT NULL, `monthlySales` INTEGER NOT NULL, `deliveryTime` TEXT, `deliveryFee` REAL NOT NULL, `minPrice` REAL NOT NULL, `distance` REAL NOT NULL, `categories` TEXT, `description` TEXT, `isFavorite` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `dishes` (`id` INTEGER NOT NULL, `restaurantId` INTEGER NOT NULL, `name` TEXT, `description` TEXT, `imageUrl` TEXT, `price` REAL NOT NULL, `originalPrice` REAL, `categoryName` TEXT, `monthSales` INTEGER NOT NULL, `stock` INTEGER NOT NULL, `isRecommended` INTEGER NOT NULL, `packingFee` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `cart_items` (`id` INTEGER NOT NULL, `dishId` INTEGER NOT NULL, `restaurantId` INTEGER NOT NULL, `name` TEXT, `price` REAL NOT NULL, `packingFee` REAL NOT NULL, `imageUrl` TEXT, `quantity` INTEGER NOT NULL, `categoryName` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `addresses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `phone` TEXT, `detailAddress` TEXT, `province` TEXT, `city` TEXT, `district` TEXT, `street` TEXT, `buildingInfo` TEXT, `floorInfo` TEXT, `doorplateInfo` TEXT, `addressTag` TEXT, `isDefault` INTEGER NOT NULL, `latitude` REAL, `longitude` REAL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `orders` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `orderNo` TEXT, `restaurantId` INTEGER NOT NULL, `restaurantName` TEXT, `totalAmount` REAL NOT NULL, `deliveryFee` REAL NOT NULL, `packingFee` REAL NOT NULL, `discountAmount` REAL NOT NULL, `actualAmount` REAL NOT NULL, `status` TEXT, `createTime` INTEGER, `payTime` INTEGER, `deliveryTime` INTEGER, `completeTime` INTEGER, `addressId` INTEGER NOT NULL, `addressDetail` TEXT, `receiverName` TEXT, `receiverPhone` TEXT, `items` TEXT, `riderName` TEXT, `riderPhone` TEXT, `estimatedDeliveryTime` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '90ecd7d7ebcb25426ccf23bc70f61193')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `restaurants`");
        db.execSQL("DROP TABLE IF EXISTS `dishes`");
        db.execSQL("DROP TABLE IF EXISTS `cart_items`");
        db.execSQL("DROP TABLE IF EXISTS `addresses`");
        db.execSQL("DROP TABLE IF EXISTS `orders`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRestaurants = new HashMap<String, TableInfo.Column>(13);
        _columnsRestaurants.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("englishName", new TableInfo.Column("englishName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("rating", new TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("monthlySales", new TableInfo.Column("monthlySales", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("deliveryTime", new TableInfo.Column("deliveryTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("deliveryFee", new TableInfo.Column("deliveryFee", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("minPrice", new TableInfo.Column("minPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("categories", new TableInfo.Column("categories", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRestaurants = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRestaurants = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRestaurants = new TableInfo("restaurants", _columnsRestaurants, _foreignKeysRestaurants, _indicesRestaurants);
        final TableInfo _existingRestaurants = TableInfo.read(db, "restaurants");
        if (!_infoRestaurants.equals(_existingRestaurants)) {
          return new RoomOpenHelper.ValidationResult(false, "restaurants(com.fooddelivery.app.data.model.Restaurant).\n"
                  + " Expected:\n" + _infoRestaurants + "\n"
                  + " Found:\n" + _existingRestaurants);
        }
        final HashMap<String, TableInfo.Column> _columnsDishes = new HashMap<String, TableInfo.Column>(12);
        _columnsDishes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("restaurantId", new TableInfo.Column("restaurantId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("originalPrice", new TableInfo.Column("originalPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("categoryName", new TableInfo.Column("categoryName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("monthSales", new TableInfo.Column("monthSales", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("stock", new TableInfo.Column("stock", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("isRecommended", new TableInfo.Column("isRecommended", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishes.put("packingFee", new TableInfo.Column("packingFee", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDishes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDishes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDishes = new TableInfo("dishes", _columnsDishes, _foreignKeysDishes, _indicesDishes);
        final TableInfo _existingDishes = TableInfo.read(db, "dishes");
        if (!_infoDishes.equals(_existingDishes)) {
          return new RoomOpenHelper.ValidationResult(false, "dishes(com.fooddelivery.app.data.model.Dish).\n"
                  + " Expected:\n" + _infoDishes + "\n"
                  + " Found:\n" + _existingDishes);
        }
        final HashMap<String, TableInfo.Column> _columnsCartItems = new HashMap<String, TableInfo.Column>(9);
        _columnsCartItems.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartItems.put("dishId", new TableInfo.Column("dishId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartItems.put("restaurantId", new TableInfo.Column("restaurantId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartItems.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartItems.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartItems.put("packingFee", new TableInfo.Column("packingFee", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartItems.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartItems.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartItems.put("categoryName", new TableInfo.Column("categoryName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCartItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCartItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCartItems = new TableInfo("cart_items", _columnsCartItems, _foreignKeysCartItems, _indicesCartItems);
        final TableInfo _existingCartItems = TableInfo.read(db, "cart_items");
        if (!_infoCartItems.equals(_existingCartItems)) {
          return new RoomOpenHelper.ValidationResult(false, "cart_items(com.fooddelivery.app.data.model.CartItem).\n"
                  + " Expected:\n" + _infoCartItems + "\n"
                  + " Found:\n" + _existingCartItems);
        }
        final HashMap<String, TableInfo.Column> _columnsAddresses = new HashMap<String, TableInfo.Column>(15);
        _columnsAddresses.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("phone", new TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("detailAddress", new TableInfo.Column("detailAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("province", new TableInfo.Column("province", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("district", new TableInfo.Column("district", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("street", new TableInfo.Column("street", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("buildingInfo", new TableInfo.Column("buildingInfo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("floorInfo", new TableInfo.Column("floorInfo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("doorplateInfo", new TableInfo.Column("doorplateInfo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("addressTag", new TableInfo.Column("addressTag", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("isDefault", new TableInfo.Column("isDefault", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("latitude", new TableInfo.Column("latitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddresses.put("longitude", new TableInfo.Column("longitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAddresses = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAddresses = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAddresses = new TableInfo("addresses", _columnsAddresses, _foreignKeysAddresses, _indicesAddresses);
        final TableInfo _existingAddresses = TableInfo.read(db, "addresses");
        if (!_infoAddresses.equals(_existingAddresses)) {
          return new RoomOpenHelper.ValidationResult(false, "addresses(com.fooddelivery.app.data.model.Address).\n"
                  + " Expected:\n" + _infoAddresses + "\n"
                  + " Found:\n" + _existingAddresses);
        }
        final HashMap<String, TableInfo.Column> _columnsOrders = new HashMap<String, TableInfo.Column>(22);
        _columnsOrders.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("orderNo", new TableInfo.Column("orderNo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("restaurantId", new TableInfo.Column("restaurantId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("restaurantName", new TableInfo.Column("restaurantName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("totalAmount", new TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("deliveryFee", new TableInfo.Column("deliveryFee", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("packingFee", new TableInfo.Column("packingFee", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("discountAmount", new TableInfo.Column("discountAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("actualAmount", new TableInfo.Column("actualAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("createTime", new TableInfo.Column("createTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("payTime", new TableInfo.Column("payTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("deliveryTime", new TableInfo.Column("deliveryTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("completeTime", new TableInfo.Column("completeTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("addressId", new TableInfo.Column("addressId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("addressDetail", new TableInfo.Column("addressDetail", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("receiverName", new TableInfo.Column("receiverName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("receiverPhone", new TableInfo.Column("receiverPhone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("items", new TableInfo.Column("items", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("riderName", new TableInfo.Column("riderName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("riderPhone", new TableInfo.Column("riderPhone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("estimatedDeliveryTime", new TableInfo.Column("estimatedDeliveryTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOrders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOrders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOrders = new TableInfo("orders", _columnsOrders, _foreignKeysOrders, _indicesOrders);
        final TableInfo _existingOrders = TableInfo.read(db, "orders");
        if (!_infoOrders.equals(_existingOrders)) {
          return new RoomOpenHelper.ValidationResult(false, "orders(com.fooddelivery.app.data.model.Order).\n"
                  + " Expected:\n" + _infoOrders + "\n"
                  + " Found:\n" + _existingOrders);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "90ecd7d7ebcb25426ccf23bc70f61193", "4a02a652f3bbd8ebe7b809113cefdb40");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "restaurants","dishes","cart_items","addresses","orders");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `restaurants`");
      _db.execSQL("DELETE FROM `dishes`");
      _db.execSQL("DELETE FROM `cart_items`");
      _db.execSQL("DELETE FROM `addresses`");
      _db.execSQL("DELETE FROM `orders`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RestaurantDao.class, RestaurantDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DishDao.class, DishDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CartDao.class, CartDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AddressDao.class, AddressDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OrderDao.class, OrderDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RestaurantDao restaurantDao() {
    if (_restaurantDao != null) {
      return _restaurantDao;
    } else {
      synchronized(this) {
        if(_restaurantDao == null) {
          _restaurantDao = new RestaurantDao_Impl(this);
        }
        return _restaurantDao;
      }
    }
  }

  @Override
  public DishDao dishDao() {
    if (_dishDao != null) {
      return _dishDao;
    } else {
      synchronized(this) {
        if(_dishDao == null) {
          _dishDao = new DishDao_Impl(this);
        }
        return _dishDao;
      }
    }
  }

  @Override
  public CartDao cartDao() {
    if (_cartDao != null) {
      return _cartDao;
    } else {
      synchronized(this) {
        if(_cartDao == null) {
          _cartDao = new CartDao_Impl(this);
        }
        return _cartDao;
      }
    }
  }

  @Override
  public AddressDao addressDao() {
    if (_addressDao != null) {
      return _addressDao;
    } else {
      synchronized(this) {
        if(_addressDao == null) {
          _addressDao = new AddressDao_Impl(this);
        }
        return _addressDao;
      }
    }
  }

  @Override
  public OrderDao orderDao() {
    if (_orderDao != null) {
      return _orderDao;
    } else {
      synchronized(this) {
        if(_orderDao == null) {
          _orderDao = new OrderDao_Impl(this);
        }
        return _orderDao;
      }
    }
  }
}
