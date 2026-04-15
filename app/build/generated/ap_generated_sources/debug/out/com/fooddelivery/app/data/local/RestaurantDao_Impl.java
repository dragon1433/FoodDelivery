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
import com.fooddelivery.app.data.model.Restaurant;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RestaurantDao_Impl implements RestaurantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Restaurant> __insertionAdapterOfRestaurant;

  private final EntityDeletionOrUpdateAdapter<Restaurant> __deletionAdapterOfRestaurant;

  private final EntityDeletionOrUpdateAdapter<Restaurant> __updateAdapterOfRestaurant;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRestaurants;

  public RestaurantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRestaurant = new EntityInsertionAdapter<Restaurant>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `restaurants` (`id`,`name`,`englishName`,`imageUrl`,`rating`,`monthlySales`,`deliveryTime`,`deliveryFee`,`minPrice`,`distance`,`categories`,`description`,`isFavorite`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Restaurant entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getEnglishName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEnglishName());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getImageUrl());
        }
        statement.bindDouble(5, entity.getRating());
        statement.bindLong(6, entity.getMonthlySales());
        if (entity.getDeliveryTime() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDeliveryTime());
        }
        statement.bindDouble(8, entity.getDeliveryFee());
        statement.bindDouble(9, entity.getMinPrice());
        statement.bindDouble(10, entity.getDistance());
        if (entity.getCategories() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getCategories());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getDescription());
        }
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(13, _tmp);
      }
    };
    this.__deletionAdapterOfRestaurant = new EntityDeletionOrUpdateAdapter<Restaurant>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `restaurants` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Restaurant entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRestaurant = new EntityDeletionOrUpdateAdapter<Restaurant>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `restaurants` SET `id` = ?,`name` = ?,`englishName` = ?,`imageUrl` = ?,`rating` = ?,`monthlySales` = ?,`deliveryTime` = ?,`deliveryFee` = ?,`minPrice` = ?,`distance` = ?,`categories` = ?,`description` = ?,`isFavorite` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Restaurant entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getEnglishName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEnglishName());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getImageUrl());
        }
        statement.bindDouble(5, entity.getRating());
        statement.bindLong(6, entity.getMonthlySales());
        if (entity.getDeliveryTime() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDeliveryTime());
        }
        statement.bindDouble(8, entity.getDeliveryFee());
        statement.bindDouble(9, entity.getMinPrice());
        statement.bindDouble(10, entity.getDistance());
        if (entity.getCategories() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getCategories());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getDescription());
        }
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(13, _tmp);
        statement.bindLong(14, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllRestaurants = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM restaurants";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Restaurant restaurant) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRestaurant.insert(restaurant);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Restaurant restaurant) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRestaurant.handle(restaurant);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Restaurant restaurant) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfRestaurant.handle(restaurant);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllRestaurants() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRestaurants.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllRestaurants.release(_stmt);
    }
  }

  @Override
  public List<Restaurant> getAllRestaurants() {
    final String _sql = "SELECT * FROM restaurants ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfEnglishName = CursorUtil.getColumnIndexOrThrow(_cursor, "englishName");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfMonthlySales = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlySales");
      final int _cursorIndexOfDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryTime");
      final int _cursorIndexOfDeliveryFee = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryFee");
      final int _cursorIndexOfMinPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "minPrice");
      final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
      final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final List<Restaurant> _result = new ArrayList<Restaurant>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Restaurant _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpEnglishName;
        if (_cursor.isNull(_cursorIndexOfEnglishName)) {
          _tmpEnglishName = null;
        } else {
          _tmpEnglishName = _cursor.getString(_cursorIndexOfEnglishName);
        }
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        final int _tmpMonthlySales;
        _tmpMonthlySales = _cursor.getInt(_cursorIndexOfMonthlySales);
        final String _tmpDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmpDeliveryTime = null;
        } else {
          _tmpDeliveryTime = _cursor.getString(_cursorIndexOfDeliveryTime);
        }
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        final double _tmpMinPrice;
        _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
        final double _tmpDistance;
        _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
        final String _tmpCategories;
        if (_cursor.isNull(_cursorIndexOfCategories)) {
          _tmpCategories = null;
        } else {
          _tmpCategories = _cursor.getString(_cursorIndexOfCategories);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _item = new Restaurant(_tmpId,_tmpName,_tmpEnglishName,_tmpImageUrl,_tmpRating,_tmpMonthlySales,_tmpDeliveryTime,_tmpDeliveryFee,_tmpMinPrice,_tmpDistance,_tmpCategories,_tmpDescription,_tmpIsFavorite);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Restaurant getRestaurantById(final long restaurantId) {
    final String _sql = "SELECT * FROM restaurants WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfEnglishName = CursorUtil.getColumnIndexOrThrow(_cursor, "englishName");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfMonthlySales = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlySales");
      final int _cursorIndexOfDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryTime");
      final int _cursorIndexOfDeliveryFee = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryFee");
      final int _cursorIndexOfMinPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "minPrice");
      final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
      final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final Restaurant _result;
      if (_cursor.moveToFirst()) {
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpEnglishName;
        if (_cursor.isNull(_cursorIndexOfEnglishName)) {
          _tmpEnglishName = null;
        } else {
          _tmpEnglishName = _cursor.getString(_cursorIndexOfEnglishName);
        }
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        final int _tmpMonthlySales;
        _tmpMonthlySales = _cursor.getInt(_cursorIndexOfMonthlySales);
        final String _tmpDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmpDeliveryTime = null;
        } else {
          _tmpDeliveryTime = _cursor.getString(_cursorIndexOfDeliveryTime);
        }
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        final double _tmpMinPrice;
        _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
        final double _tmpDistance;
        _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
        final String _tmpCategories;
        if (_cursor.isNull(_cursorIndexOfCategories)) {
          _tmpCategories = null;
        } else {
          _tmpCategories = _cursor.getString(_cursorIndexOfCategories);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _result = new Restaurant(_tmpId,_tmpName,_tmpEnglishName,_tmpImageUrl,_tmpRating,_tmpMonthlySales,_tmpDeliveryTime,_tmpDeliveryFee,_tmpMinPrice,_tmpDistance,_tmpCategories,_tmpDescription,_tmpIsFavorite);
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
  public List<Restaurant> searchRestaurants(final String keyword) {
    final String _sql = "SELECT * FROM restaurants WHERE name LIKE '%' || ? || '%' OR categories LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (keyword == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, keyword);
    }
    _argIndex = 2;
    if (keyword == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, keyword);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfEnglishName = CursorUtil.getColumnIndexOrThrow(_cursor, "englishName");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfMonthlySales = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlySales");
      final int _cursorIndexOfDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryTime");
      final int _cursorIndexOfDeliveryFee = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryFee");
      final int _cursorIndexOfMinPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "minPrice");
      final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
      final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final List<Restaurant> _result = new ArrayList<Restaurant>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Restaurant _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpEnglishName;
        if (_cursor.isNull(_cursorIndexOfEnglishName)) {
          _tmpEnglishName = null;
        } else {
          _tmpEnglishName = _cursor.getString(_cursorIndexOfEnglishName);
        }
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        final int _tmpMonthlySales;
        _tmpMonthlySales = _cursor.getInt(_cursorIndexOfMonthlySales);
        final String _tmpDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmpDeliveryTime = null;
        } else {
          _tmpDeliveryTime = _cursor.getString(_cursorIndexOfDeliveryTime);
        }
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        final double _tmpMinPrice;
        _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
        final double _tmpDistance;
        _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
        final String _tmpCategories;
        if (_cursor.isNull(_cursorIndexOfCategories)) {
          _tmpCategories = null;
        } else {
          _tmpCategories = _cursor.getString(_cursorIndexOfCategories);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _item = new Restaurant(_tmpId,_tmpName,_tmpEnglishName,_tmpImageUrl,_tmpRating,_tmpMonthlySales,_tmpDeliveryTime,_tmpDeliveryFee,_tmpMinPrice,_tmpDistance,_tmpCategories,_tmpDescription,_tmpIsFavorite);
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
