package com.fooddelivery.app.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.fooddelivery.app.data.model.Dish;
import java.lang.Class;
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DishDao_Impl implements DishDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Dish> __insertionAdapterOfDish;

  private final EntityDeletionOrUpdateAdapter<Dish> __deletionAdapterOfDish;

  private final EntityDeletionOrUpdateAdapter<Dish> __updateAdapterOfDish;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllDishes;

  public DishDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDish = new EntityInsertionAdapter<Dish>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `dishes` (`id`,`restaurantId`,`name`,`description`,`imageUrl`,`price`,`originalPrice`,`categoryName`,`monthSales`,`stock`,`isRecommended`,`packingFee`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Dish entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRestaurantId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getImageUrl());
        }
        statement.bindDouble(6, entity.getPrice());
        if (entity.getOriginalPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getOriginalPrice());
        }
        if (entity.getCategoryName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategoryName());
        }
        statement.bindLong(9, entity.getMonthSales());
        statement.bindLong(10, entity.getStock());
        final int _tmp = entity.isRecommended() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindDouble(12, entity.getPackingFee());
      }
    };
    this.__deletionAdapterOfDish = new EntityDeletionOrUpdateAdapter<Dish>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `dishes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Dish entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfDish = new EntityDeletionOrUpdateAdapter<Dish>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `dishes` SET `id` = ?,`restaurantId` = ?,`name` = ?,`description` = ?,`imageUrl` = ?,`price` = ?,`originalPrice` = ?,`categoryName` = ?,`monthSales` = ?,`stock` = ?,`isRecommended` = ?,`packingFee` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Dish entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRestaurantId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getImageUrl());
        }
        statement.bindDouble(6, entity.getPrice());
        if (entity.getOriginalPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getOriginalPrice());
        }
        if (entity.getCategoryName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategoryName());
        }
        statement.bindLong(9, entity.getMonthSales());
        statement.bindLong(10, entity.getStock());
        final int _tmp = entity.isRecommended() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindDouble(12, entity.getPackingFee());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllDishes = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM dishes";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Dish dish) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDish.insert(dish);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Dish dish) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDish.handle(dish);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Dish dish) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDish.handle(dish);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllDishes() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllDishes.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllDishes.release(_stmt);
    }
  }

  @Override
  public List<Dish> getDishesByRestaurant(final long restaurantId) {
    final String _sql = "SELECT * FROM dishes WHERE restaurantId = ? ORDER BY categoryName, id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfOriginalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "originalPrice");
      final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryName");
      final int _cursorIndexOfMonthSales = CursorUtil.getColumnIndexOrThrow(_cursor, "monthSales");
      final int _cursorIndexOfStock = CursorUtil.getColumnIndexOrThrow(_cursor, "stock");
      final int _cursorIndexOfIsRecommended = CursorUtil.getColumnIndexOrThrow(_cursor, "isRecommended");
      final int _cursorIndexOfPackingFee = CursorUtil.getColumnIndexOrThrow(_cursor, "packingFee");
      final List<Dish> _result = new ArrayList<Dish>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Dish _item;
        _item = new Dish();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _item.setImageUrl(_tmpImageUrl);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final Double _tmpOriginalPrice;
        if (_cursor.isNull(_cursorIndexOfOriginalPrice)) {
          _tmpOriginalPrice = null;
        } else {
          _tmpOriginalPrice = _cursor.getDouble(_cursorIndexOfOriginalPrice);
        }
        _item.setOriginalPrice(_tmpOriginalPrice);
        final String _tmpCategoryName;
        if (_cursor.isNull(_cursorIndexOfCategoryName)) {
          _tmpCategoryName = null;
        } else {
          _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
        }
        _item.setCategoryName(_tmpCategoryName);
        final int _tmpMonthSales;
        _tmpMonthSales = _cursor.getInt(_cursorIndexOfMonthSales);
        _item.setMonthSales(_tmpMonthSales);
        final int _tmpStock;
        _tmpStock = _cursor.getInt(_cursorIndexOfStock);
        _item.setStock(_tmpStock);
        final boolean _tmpIsRecommended;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsRecommended);
        _tmpIsRecommended = _tmp != 0;
        _item.setRecommended(_tmpIsRecommended);
        final double _tmpPackingFee;
        _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
        _item.setPackingFee(_tmpPackingFee);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Dish getDishById(final long dishId) {
    final String _sql = "SELECT * FROM dishes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dishId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfOriginalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "originalPrice");
      final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryName");
      final int _cursorIndexOfMonthSales = CursorUtil.getColumnIndexOrThrow(_cursor, "monthSales");
      final int _cursorIndexOfStock = CursorUtil.getColumnIndexOrThrow(_cursor, "stock");
      final int _cursorIndexOfIsRecommended = CursorUtil.getColumnIndexOrThrow(_cursor, "isRecommended");
      final int _cursorIndexOfPackingFee = CursorUtil.getColumnIndexOrThrow(_cursor, "packingFee");
      final Dish _result;
      if (_cursor.moveToFirst()) {
        _result = new Dish();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final long _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
        _result.setRestaurantId(_tmpRestaurantId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _result.setDescription(_tmpDescription);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _result.setImageUrl(_tmpImageUrl);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _result.setPrice(_tmpPrice);
        final Double _tmpOriginalPrice;
        if (_cursor.isNull(_cursorIndexOfOriginalPrice)) {
          _tmpOriginalPrice = null;
        } else {
          _tmpOriginalPrice = _cursor.getDouble(_cursorIndexOfOriginalPrice);
        }
        _result.setOriginalPrice(_tmpOriginalPrice);
        final String _tmpCategoryName;
        if (_cursor.isNull(_cursorIndexOfCategoryName)) {
          _tmpCategoryName = null;
        } else {
          _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
        }
        _result.setCategoryName(_tmpCategoryName);
        final int _tmpMonthSales;
        _tmpMonthSales = _cursor.getInt(_cursorIndexOfMonthSales);
        _result.setMonthSales(_tmpMonthSales);
        final int _tmpStock;
        _tmpStock = _cursor.getInt(_cursorIndexOfStock);
        _result.setStock(_tmpStock);
        final boolean _tmpIsRecommended;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsRecommended);
        _tmpIsRecommended = _tmp != 0;
        _result.setRecommended(_tmpIsRecommended);
        final double _tmpPackingFee;
        _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
        _result.setPackingFee(_tmpPackingFee);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getCategoriesByRestaurant(final long restaurantId) {
    final String _sql = "SELECT DISTINCT categoryName FROM dishes WHERE restaurantId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
