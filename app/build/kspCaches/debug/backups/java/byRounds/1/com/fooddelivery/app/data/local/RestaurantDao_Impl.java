package com.fooddelivery.app.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.fooddelivery.app.data.model.Restaurant;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RestaurantDao_Impl implements RestaurantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Restaurant> __insertionAdapterOfRestaurant;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Restaurant> __updateAdapterOfRestaurant;

  public RestaurantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRestaurant = new EntityInsertionAdapter<Restaurant>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `restaurants` (`id`,`name`,`imageUrl`,`rating`,`monthlySales`,`deliveryTime`,`deliveryFee`,`minPrice`,`distance`,`categories`,`description`,`isFavorite`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Restaurant entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getImageUrl());
        statement.bindDouble(4, entity.getRating());
        statement.bindLong(5, entity.getMonthlySales());
        statement.bindString(6, entity.getDeliveryTime());
        statement.bindDouble(7, entity.getDeliveryFee());
        statement.bindDouble(8, entity.getMinPrice());
        statement.bindDouble(9, entity.getDistance());
        final String _tmp = __converters.fromStringList(entity.getCategories());
        if (_tmp == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmp);
        }
        statement.bindString(11, entity.getDescription());
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
      }
    };
    this.__updateAdapterOfRestaurant = new EntityDeletionOrUpdateAdapter<Restaurant>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `restaurants` SET `id` = ?,`name` = ?,`imageUrl` = ?,`rating` = ?,`monthlySales` = ?,`deliveryTime` = ?,`deliveryFee` = ?,`minPrice` = ?,`distance` = ?,`categories` = ?,`description` = ?,`isFavorite` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Restaurant entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getImageUrl());
        statement.bindDouble(4, entity.getRating());
        statement.bindLong(5, entity.getMonthlySales());
        statement.bindString(6, entity.getDeliveryTime());
        statement.bindDouble(7, entity.getDeliveryFee());
        statement.bindDouble(8, entity.getMinPrice());
        statement.bindDouble(9, entity.getDistance());
        final String _tmp = __converters.fromStringList(entity.getCategories());
        if (_tmp == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmp);
        }
        statement.bindString(11, entity.getDescription());
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
        statement.bindLong(13, entity.getId());
      }
    };
  }

  @Override
  public Object insertRestaurants(final List<Restaurant> restaurants,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRestaurant.insert(restaurants);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRestaurant(final Restaurant restaurant,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRestaurant.handle(restaurant);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Restaurant>> getAllRestaurants() {
    final String _sql = "SELECT * FROM restaurants ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"restaurants"}, new Callable<List<Restaurant>>() {
      @Override
      @NonNull
      public List<Restaurant> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
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
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final int _tmpMonthlySales;
            _tmpMonthlySales = _cursor.getInt(_cursorIndexOfMonthlySales);
            final String _tmpDeliveryTime;
            _tmpDeliveryTime = _cursor.getString(_cursorIndexOfDeliveryTime);
            final double _tmpDeliveryFee;
            _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
            final double _tmpMinPrice;
            _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
            final double _tmpDistance;
            _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
            final List<String> _tmpCategories;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategories)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategories);
            }
            final List<String> _tmp_1 = __converters.toStringList(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.List<java.lang.String>', but it was NULL.");
            } else {
              _tmpCategories = _tmp_1;
            }
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final boolean _tmpIsFavorite;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_2 != 0;
            _item = new Restaurant(_tmpId,_tmpName,_tmpImageUrl,_tmpRating,_tmpMonthlySales,_tmpDeliveryTime,_tmpDeliveryFee,_tmpMinPrice,_tmpDistance,_tmpCategories,_tmpDescription,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getRestaurantById(final long restaurantId,
      final Continuation<? super Restaurant> $completion) {
    final String _sql = "SELECT * FROM restaurants WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Restaurant>() {
      @Override
      @Nullable
      public Restaurant call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
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
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final int _tmpMonthlySales;
            _tmpMonthlySales = _cursor.getInt(_cursorIndexOfMonthlySales);
            final String _tmpDeliveryTime;
            _tmpDeliveryTime = _cursor.getString(_cursorIndexOfDeliveryTime);
            final double _tmpDeliveryFee;
            _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
            final double _tmpMinPrice;
            _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
            final double _tmpDistance;
            _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
            final List<String> _tmpCategories;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategories)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategories);
            }
            final List<String> _tmp_1 = __converters.toStringList(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.List<java.lang.String>', but it was NULL.");
            } else {
              _tmpCategories = _tmp_1;
            }
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final boolean _tmpIsFavorite;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_2 != 0;
            _result = new Restaurant(_tmpId,_tmpName,_tmpImageUrl,_tmpRating,_tmpMonthlySales,_tmpDeliveryTime,_tmpDeliveryFee,_tmpMinPrice,_tmpDistance,_tmpCategories,_tmpDescription,_tmpIsFavorite);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Restaurant>> searchRestaurants(final String keyword) {
    final String _sql = "SELECT * FROM restaurants WHERE name LIKE '%' || ? || '%' OR categories LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, keyword);
    _argIndex = 2;
    _statement.bindString(_argIndex, keyword);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"restaurants"}, new Callable<List<Restaurant>>() {
      @Override
      @NonNull
      public List<Restaurant> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
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
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final int _tmpMonthlySales;
            _tmpMonthlySales = _cursor.getInt(_cursorIndexOfMonthlySales);
            final String _tmpDeliveryTime;
            _tmpDeliveryTime = _cursor.getString(_cursorIndexOfDeliveryTime);
            final double _tmpDeliveryFee;
            _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
            final double _tmpMinPrice;
            _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
            final double _tmpDistance;
            _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
            final List<String> _tmpCategories;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategories)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategories);
            }
            final List<String> _tmp_1 = __converters.toStringList(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.List<java.lang.String>', but it was NULL.");
            } else {
              _tmpCategories = _tmp_1;
            }
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final boolean _tmpIsFavorite;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_2 != 0;
            _item = new Restaurant(_tmpId,_tmpName,_tmpImageUrl,_tmpRating,_tmpMonthlySales,_tmpDeliveryTime,_tmpDeliveryFee,_tmpMinPrice,_tmpDistance,_tmpCategories,_tmpDescription,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
