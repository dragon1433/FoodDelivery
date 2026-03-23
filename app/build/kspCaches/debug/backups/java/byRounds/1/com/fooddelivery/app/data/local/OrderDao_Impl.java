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
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.data.model.OrderItem;
import com.fooddelivery.app.data.model.OrderStatus;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class OrderDao_Impl implements OrderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Order> __insertionAdapterOfOrder;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Order> __deletionAdapterOfOrder;

  private final EntityDeletionOrUpdateAdapter<Order> __updateAdapterOfOrder;

  public OrderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOrder = new EntityInsertionAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `orders` (`id`,`orderNo`,`restaurantId`,`restaurantName`,`totalAmount`,`deliveryFee`,`packingFee`,`discountAmount`,`actualAmount`,`status`,`createTime`,`payTime`,`deliveryTime`,`completeTime`,`addressId`,`addressDetail`,`receiverName`,`receiverPhone`,`items`,`riderName`,`riderPhone`,`estimatedDeliveryTime`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Order entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getOrderNo());
        statement.bindLong(3, entity.getRestaurantId());
        statement.bindString(4, entity.getRestaurantName());
        statement.bindDouble(5, entity.getTotalAmount());
        statement.bindDouble(6, entity.getDeliveryFee());
        statement.bindDouble(7, entity.getPackingFee());
        statement.bindDouble(8, entity.getDiscountAmount());
        statement.bindDouble(9, entity.getActualAmount());
        statement.bindString(10, __OrderStatus_enumToString(entity.getStatus()));
        final Long _tmp = __converters.fromDate(entity.getCreateTime());
        if (_tmp == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, _tmp);
        }
        final Long _tmp_1 = __converters.fromDate(entity.getPayTime());
        if (_tmp_1 == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp_1);
        }
        final Long _tmp_2 = __converters.fromDate(entity.getDeliveryTime());
        if (_tmp_2 == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, _tmp_2);
        }
        final Long _tmp_3 = __converters.fromDate(entity.getCompleteTime());
        if (_tmp_3 == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, _tmp_3);
        }
        statement.bindLong(15, entity.getAddressId());
        statement.bindString(16, entity.getAddressDetail());
        statement.bindString(17, entity.getReceiverName());
        statement.bindString(18, entity.getReceiverPhone());
        final String _tmp_4 = __converters.fromOrderItemList(entity.getItems());
        if (_tmp_4 == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, _tmp_4);
        }
        if (entity.getRiderName() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getRiderName());
        }
        if (entity.getRiderPhone() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getRiderPhone());
        }
        if (entity.getEstimatedDeliveryTime() == null) {
          statement.bindNull(22);
        } else {
          statement.bindString(22, entity.getEstimatedDeliveryTime());
        }
      }
    };
    this.__deletionAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `orders` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Order entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `orders` SET `id` = ?,`orderNo` = ?,`restaurantId` = ?,`restaurantName` = ?,`totalAmount` = ?,`deliveryFee` = ?,`packingFee` = ?,`discountAmount` = ?,`actualAmount` = ?,`status` = ?,`createTime` = ?,`payTime` = ?,`deliveryTime` = ?,`completeTime` = ?,`addressId` = ?,`addressDetail` = ?,`receiverName` = ?,`receiverPhone` = ?,`items` = ?,`riderName` = ?,`riderPhone` = ?,`estimatedDeliveryTime` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Order entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getOrderNo());
        statement.bindLong(3, entity.getRestaurantId());
        statement.bindString(4, entity.getRestaurantName());
        statement.bindDouble(5, entity.getTotalAmount());
        statement.bindDouble(6, entity.getDeliveryFee());
        statement.bindDouble(7, entity.getPackingFee());
        statement.bindDouble(8, entity.getDiscountAmount());
        statement.bindDouble(9, entity.getActualAmount());
        statement.bindString(10, __OrderStatus_enumToString(entity.getStatus()));
        final Long _tmp = __converters.fromDate(entity.getCreateTime());
        if (_tmp == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, _tmp);
        }
        final Long _tmp_1 = __converters.fromDate(entity.getPayTime());
        if (_tmp_1 == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp_1);
        }
        final Long _tmp_2 = __converters.fromDate(entity.getDeliveryTime());
        if (_tmp_2 == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, _tmp_2);
        }
        final Long _tmp_3 = __converters.fromDate(entity.getCompleteTime());
        if (_tmp_3 == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, _tmp_3);
        }
        statement.bindLong(15, entity.getAddressId());
        statement.bindString(16, entity.getAddressDetail());
        statement.bindString(17, entity.getReceiverName());
        statement.bindString(18, entity.getReceiverPhone());
        final String _tmp_4 = __converters.fromOrderItemList(entity.getItems());
        if (_tmp_4 == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, _tmp_4);
        }
        if (entity.getRiderName() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getRiderName());
        }
        if (entity.getRiderPhone() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getRiderPhone());
        }
        if (entity.getEstimatedDeliveryTime() == null) {
          statement.bindNull(22);
        } else {
          statement.bindString(22, entity.getEstimatedDeliveryTime());
        }
        statement.bindLong(23, entity.getId());
      }
    };
  }

  @Override
  public Object insertOrder(final Order order, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfOrder.insertAndReturnId(order);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOrder(final Order order, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfOrder.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateOrder(final Order order, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfOrder.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Order>> getAllOrders() {
    final String _sql = "SELECT * FROM orders ORDER BY createTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"orders"}, new Callable<List<Order>>() {
      @Override
      @NonNull
      public List<Order> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrderNo = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNo");
          final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantId");
          final int _cursorIndexOfRestaurantName = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantName");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfDeliveryFee = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryFee");
          final int _cursorIndexOfPackingFee = CursorUtil.getColumnIndexOrThrow(_cursor, "packingFee");
          final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
          final int _cursorIndexOfActualAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "actualAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
          final int _cursorIndexOfPayTime = CursorUtil.getColumnIndexOrThrow(_cursor, "payTime");
          final int _cursorIndexOfDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryTime");
          final int _cursorIndexOfCompleteTime = CursorUtil.getColumnIndexOrThrow(_cursor, "completeTime");
          final int _cursorIndexOfAddressId = CursorUtil.getColumnIndexOrThrow(_cursor, "addressId");
          final int _cursorIndexOfAddressDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "addressDetail");
          final int _cursorIndexOfReceiverName = CursorUtil.getColumnIndexOrThrow(_cursor, "receiverName");
          final int _cursorIndexOfReceiverPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "receiverPhone");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfRiderName = CursorUtil.getColumnIndexOrThrow(_cursor, "riderName");
          final int _cursorIndexOfRiderPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "riderPhone");
          final int _cursorIndexOfEstimatedDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDeliveryTime");
          final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Order _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpOrderNo;
            _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
            final long _tmpRestaurantId;
            _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
            final String _tmpRestaurantName;
            _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpDeliveryFee;
            _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
            final double _tmpPackingFee;
            _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
            final double _tmpDiscountAmount;
            _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
            final double _tmpActualAmount;
            _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
            final OrderStatus _tmpStatus;
            _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final Date _tmpCreateTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreateTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
            }
            final Date _tmp_1 = __converters.toDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpCreateTime = _tmp_1;
            }
            final Date _tmpPayTime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPayTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfPayTime);
            }
            _tmpPayTime = __converters.toDate(_tmp_2);
            final Date _tmpDeliveryTime;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfDeliveryTime);
            }
            _tmpDeliveryTime = __converters.toDate(_tmp_3);
            final Date _tmpCompleteTime;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfCompleteTime);
            }
            _tmpCompleteTime = __converters.toDate(_tmp_4);
            final long _tmpAddressId;
            _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
            final String _tmpAddressDetail;
            _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
            final String _tmpReceiverName;
            _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
            final String _tmpReceiverPhone;
            _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
            final List<OrderItem> _tmpItems;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfItems);
            }
            final List<OrderItem> _tmp_6 = __converters.toOrderItemList(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.List<com.fooddelivery.app.data.model.OrderItem>', but it was NULL.");
            } else {
              _tmpItems = _tmp_6;
            }
            final String _tmpRiderName;
            if (_cursor.isNull(_cursorIndexOfRiderName)) {
              _tmpRiderName = null;
            } else {
              _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
            }
            final String _tmpRiderPhone;
            if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
              _tmpRiderPhone = null;
            } else {
              _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
            }
            final String _tmpEstimatedDeliveryTime;
            if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
              _tmpEstimatedDeliveryTime = null;
            } else {
              _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
            }
            _item = new Order(_tmpId,_tmpOrderNo,_tmpRestaurantId,_tmpRestaurantName,_tmpTotalAmount,_tmpDeliveryFee,_tmpPackingFee,_tmpDiscountAmount,_tmpActualAmount,_tmpStatus,_tmpCreateTime,_tmpPayTime,_tmpDeliveryTime,_tmpCompleteTime,_tmpAddressId,_tmpAddressDetail,_tmpReceiverName,_tmpReceiverPhone,_tmpItems,_tmpRiderName,_tmpRiderPhone,_tmpEstimatedDeliveryTime);
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
  public Object getOrderById(final long orderId, final Continuation<? super Order> $completion) {
    final String _sql = "SELECT * FROM orders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Order>() {
      @Override
      @Nullable
      public Order call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrderNo = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNo");
          final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantId");
          final int _cursorIndexOfRestaurantName = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantName");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfDeliveryFee = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryFee");
          final int _cursorIndexOfPackingFee = CursorUtil.getColumnIndexOrThrow(_cursor, "packingFee");
          final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
          final int _cursorIndexOfActualAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "actualAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
          final int _cursorIndexOfPayTime = CursorUtil.getColumnIndexOrThrow(_cursor, "payTime");
          final int _cursorIndexOfDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryTime");
          final int _cursorIndexOfCompleteTime = CursorUtil.getColumnIndexOrThrow(_cursor, "completeTime");
          final int _cursorIndexOfAddressId = CursorUtil.getColumnIndexOrThrow(_cursor, "addressId");
          final int _cursorIndexOfAddressDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "addressDetail");
          final int _cursorIndexOfReceiverName = CursorUtil.getColumnIndexOrThrow(_cursor, "receiverName");
          final int _cursorIndexOfReceiverPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "receiverPhone");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfRiderName = CursorUtil.getColumnIndexOrThrow(_cursor, "riderName");
          final int _cursorIndexOfRiderPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "riderPhone");
          final int _cursorIndexOfEstimatedDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDeliveryTime");
          final Order _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpOrderNo;
            _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
            final long _tmpRestaurantId;
            _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
            final String _tmpRestaurantName;
            _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpDeliveryFee;
            _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
            final double _tmpPackingFee;
            _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
            final double _tmpDiscountAmount;
            _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
            final double _tmpActualAmount;
            _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
            final OrderStatus _tmpStatus;
            _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final Date _tmpCreateTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreateTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
            }
            final Date _tmp_1 = __converters.toDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpCreateTime = _tmp_1;
            }
            final Date _tmpPayTime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPayTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfPayTime);
            }
            _tmpPayTime = __converters.toDate(_tmp_2);
            final Date _tmpDeliveryTime;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfDeliveryTime);
            }
            _tmpDeliveryTime = __converters.toDate(_tmp_3);
            final Date _tmpCompleteTime;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfCompleteTime);
            }
            _tmpCompleteTime = __converters.toDate(_tmp_4);
            final long _tmpAddressId;
            _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
            final String _tmpAddressDetail;
            _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
            final String _tmpReceiverName;
            _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
            final String _tmpReceiverPhone;
            _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
            final List<OrderItem> _tmpItems;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfItems);
            }
            final List<OrderItem> _tmp_6 = __converters.toOrderItemList(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.List<com.fooddelivery.app.data.model.OrderItem>', but it was NULL.");
            } else {
              _tmpItems = _tmp_6;
            }
            final String _tmpRiderName;
            if (_cursor.isNull(_cursorIndexOfRiderName)) {
              _tmpRiderName = null;
            } else {
              _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
            }
            final String _tmpRiderPhone;
            if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
              _tmpRiderPhone = null;
            } else {
              _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
            }
            final String _tmpEstimatedDeliveryTime;
            if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
              _tmpEstimatedDeliveryTime = null;
            } else {
              _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
            }
            _result = new Order(_tmpId,_tmpOrderNo,_tmpRestaurantId,_tmpRestaurantName,_tmpTotalAmount,_tmpDeliveryFee,_tmpPackingFee,_tmpDiscountAmount,_tmpActualAmount,_tmpStatus,_tmpCreateTime,_tmpPayTime,_tmpDeliveryTime,_tmpCompleteTime,_tmpAddressId,_tmpAddressDetail,_tmpReceiverName,_tmpReceiverPhone,_tmpItems,_tmpRiderName,_tmpRiderPhone,_tmpEstimatedDeliveryTime);
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
  public Flow<List<Order>> getOrdersByStatus(final OrderStatus status) {
    final String _sql = "SELECT * FROM orders WHERE status = ? ORDER BY createTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __OrderStatus_enumToString(status));
    return CoroutinesRoom.createFlow(__db, false, new String[] {"orders"}, new Callable<List<Order>>() {
      @Override
      @NonNull
      public List<Order> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrderNo = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNo");
          final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantId");
          final int _cursorIndexOfRestaurantName = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantName");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfDeliveryFee = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryFee");
          final int _cursorIndexOfPackingFee = CursorUtil.getColumnIndexOrThrow(_cursor, "packingFee");
          final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
          final int _cursorIndexOfActualAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "actualAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
          final int _cursorIndexOfPayTime = CursorUtil.getColumnIndexOrThrow(_cursor, "payTime");
          final int _cursorIndexOfDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryTime");
          final int _cursorIndexOfCompleteTime = CursorUtil.getColumnIndexOrThrow(_cursor, "completeTime");
          final int _cursorIndexOfAddressId = CursorUtil.getColumnIndexOrThrow(_cursor, "addressId");
          final int _cursorIndexOfAddressDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "addressDetail");
          final int _cursorIndexOfReceiverName = CursorUtil.getColumnIndexOrThrow(_cursor, "receiverName");
          final int _cursorIndexOfReceiverPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "receiverPhone");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfRiderName = CursorUtil.getColumnIndexOrThrow(_cursor, "riderName");
          final int _cursorIndexOfRiderPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "riderPhone");
          final int _cursorIndexOfEstimatedDeliveryTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDeliveryTime");
          final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Order _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpOrderNo;
            _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
            final long _tmpRestaurantId;
            _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
            final String _tmpRestaurantName;
            _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpDeliveryFee;
            _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
            final double _tmpPackingFee;
            _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
            final double _tmpDiscountAmount;
            _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
            final double _tmpActualAmount;
            _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
            final OrderStatus _tmpStatus;
            _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final Date _tmpCreateTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreateTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
            }
            final Date _tmp_1 = __converters.toDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpCreateTime = _tmp_1;
            }
            final Date _tmpPayTime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPayTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfPayTime);
            }
            _tmpPayTime = __converters.toDate(_tmp_2);
            final Date _tmpDeliveryTime;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfDeliveryTime);
            }
            _tmpDeliveryTime = __converters.toDate(_tmp_3);
            final Date _tmpCompleteTime;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfCompleteTime);
            }
            _tmpCompleteTime = __converters.toDate(_tmp_4);
            final long _tmpAddressId;
            _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
            final String _tmpAddressDetail;
            _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
            final String _tmpReceiverName;
            _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
            final String _tmpReceiverPhone;
            _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
            final List<OrderItem> _tmpItems;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfItems);
            }
            final List<OrderItem> _tmp_6 = __converters.toOrderItemList(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.List<com.fooddelivery.app.data.model.OrderItem>', but it was NULL.");
            } else {
              _tmpItems = _tmp_6;
            }
            final String _tmpRiderName;
            if (_cursor.isNull(_cursorIndexOfRiderName)) {
              _tmpRiderName = null;
            } else {
              _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
            }
            final String _tmpRiderPhone;
            if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
              _tmpRiderPhone = null;
            } else {
              _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
            }
            final String _tmpEstimatedDeliveryTime;
            if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
              _tmpEstimatedDeliveryTime = null;
            } else {
              _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
            }
            _item = new Order(_tmpId,_tmpOrderNo,_tmpRestaurantId,_tmpRestaurantName,_tmpTotalAmount,_tmpDeliveryFee,_tmpPackingFee,_tmpDiscountAmount,_tmpActualAmount,_tmpStatus,_tmpCreateTime,_tmpPayTime,_tmpDeliveryTime,_tmpCompleteTime,_tmpAddressId,_tmpAddressDetail,_tmpReceiverName,_tmpReceiverPhone,_tmpItems,_tmpRiderName,_tmpRiderPhone,_tmpEstimatedDeliveryTime);
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

  private String __OrderStatus_enumToString(@NonNull final OrderStatus _value) {
    switch (_value) {
      case PENDING_PAYMENT: return "PENDING_PAYMENT";
      case PAID: return "PAID";
      case PREPARING: return "PREPARING";
      case DELIVERING: return "DELIVERING";
      case DELIVERED: return "DELIVERED";
      case COMPLETED: return "COMPLETED";
      case CANCELLED: return "CANCELLED";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private OrderStatus __OrderStatus_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "PENDING_PAYMENT": return OrderStatus.PENDING_PAYMENT;
      case "PAID": return OrderStatus.PAID;
      case "PREPARING": return OrderStatus.PREPARING;
      case "DELIVERING": return OrderStatus.DELIVERING;
      case "DELIVERED": return OrderStatus.DELIVERED;
      case "COMPLETED": return OrderStatus.COMPLETED;
      case "CANCELLED": return OrderStatus.CANCELLED;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
