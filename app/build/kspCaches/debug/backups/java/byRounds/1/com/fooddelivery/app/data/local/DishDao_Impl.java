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
import com.fooddelivery.app.data.model.Dish;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
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
public final class DishDao_Impl implements DishDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Dish> __insertionAdapterOfDish;

  private final EntityDeletionOrUpdateAdapter<Dish> __updateAdapterOfDish;

  public DishDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDish = new EntityInsertionAdapter<Dish>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `dishes` (`id`,`restaurantId`,`name`,`description`,`imageUrl`,`price`,`originalPrice`,`categoryName`,`monthSales`,`stock`,`isRecommended`,`packingFee`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Dish entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRestaurantId());
        statement.bindString(3, entity.getName());
        statement.bindString(4, entity.getDescription());
        statement.bindString(5, entity.getImageUrl());
        statement.bindDouble(6, entity.getPrice());
        if (entity.getOriginalPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getOriginalPrice());
        }
        statement.bindString(8, entity.getCategoryName());
        statement.bindLong(9, entity.getMonthSales());
        statement.bindLong(10, entity.getStock());
        final int _tmp = entity.isRecommended() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindDouble(12, entity.getPackingFee());
      }
    };
    this.__updateAdapterOfDish = new EntityDeletionOrUpdateAdapter<Dish>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `dishes` SET `id` = ?,`restaurantId` = ?,`name` = ?,`description` = ?,`imageUrl` = ?,`price` = ?,`originalPrice` = ?,`categoryName` = ?,`monthSales` = ?,`stock` = ?,`isRecommended` = ?,`packingFee` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Dish entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRestaurantId());
        statement.bindString(3, entity.getName());
        statement.bindString(4, entity.getDescription());
        statement.bindString(5, entity.getImageUrl());
        statement.bindDouble(6, entity.getPrice());
        if (entity.getOriginalPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getOriginalPrice());
        }
        statement.bindString(8, entity.getCategoryName());
        statement.bindLong(9, entity.getMonthSales());
        statement.bindLong(10, entity.getStock());
        final int _tmp = entity.isRecommended() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindDouble(12, entity.getPackingFee());
        statement.bindLong(13, entity.getId());
      }
    };
  }

  @Override
  public Object insertDishes(final List<Dish> dishes,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDish.insert(dishes);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDish(final Dish dish, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDish.handle(dish);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Dish>> getDishesByRestaurant(final long restaurantId) {
    final String _sql = "SELECT * FROM dishes WHERE restaurantId = ? ORDER BY categoryName, id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"dishes"}, new Callable<List<Dish>>() {
      @Override
      @NonNull
      public List<Dish> call() throws Exception {
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
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpRestaurantId;
            _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpOriginalPrice;
            if (_cursor.isNull(_cursorIndexOfOriginalPrice)) {
              _tmpOriginalPrice = null;
            } else {
              _tmpOriginalPrice = _cursor.getDouble(_cursorIndexOfOriginalPrice);
            }
            final String _tmpCategoryName;
            _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            final int _tmpMonthSales;
            _tmpMonthSales = _cursor.getInt(_cursorIndexOfMonthSales);
            final int _tmpStock;
            _tmpStock = _cursor.getInt(_cursorIndexOfStock);
            final boolean _tmpIsRecommended;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRecommended);
            _tmpIsRecommended = _tmp != 0;
            final double _tmpPackingFee;
            _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
            _item = new Dish(_tmpId,_tmpRestaurantId,_tmpName,_tmpDescription,_tmpImageUrl,_tmpPrice,_tmpOriginalPrice,_tmpCategoryName,_tmpMonthSales,_tmpStock,_tmpIsRecommended,_tmpPackingFee);
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
  public Object getDishById(final long dishId, final Continuation<? super Dish> $completion) {
    final String _sql = "SELECT * FROM dishes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dishId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Dish>() {
      @Override
      @Nullable
      public Dish call() throws Exception {
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
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpRestaurantId;
            _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpOriginalPrice;
            if (_cursor.isNull(_cursorIndexOfOriginalPrice)) {
              _tmpOriginalPrice = null;
            } else {
              _tmpOriginalPrice = _cursor.getDouble(_cursorIndexOfOriginalPrice);
            }
            final String _tmpCategoryName;
            _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            final int _tmpMonthSales;
            _tmpMonthSales = _cursor.getInt(_cursorIndexOfMonthSales);
            final int _tmpStock;
            _tmpStock = _cursor.getInt(_cursorIndexOfStock);
            final boolean _tmpIsRecommended;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRecommended);
            _tmpIsRecommended = _tmp != 0;
            final double _tmpPackingFee;
            _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
            _result = new Dish(_tmpId,_tmpRestaurantId,_tmpName,_tmpDescription,_tmpImageUrl,_tmpPrice,_tmpOriginalPrice,_tmpCategoryName,_tmpMonthSales,_tmpStock,_tmpIsRecommended,_tmpPackingFee);
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
  public Object getCategoriesByRestaurant(final long restaurantId,
      final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT categoryName FROM dishes WHERE restaurantId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
