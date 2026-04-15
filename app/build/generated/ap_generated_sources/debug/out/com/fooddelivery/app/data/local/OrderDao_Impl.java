package com.fooddelivery.app.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
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
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;

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
        return "INSERT OR REPLACE INTO `orders` (`id`,`userId`,`orderNo`,`restaurantId`,`restaurantName`,`totalAmount`,`deliveryFee`,`packingFee`,`discountAmount`,`actualAmount`,`status`,`createTime`,`payTime`,`deliveryTime`,`completeTime`,`addressId`,`addressDetail`,`receiverName`,`receiverPhone`,`items`,`riderName`,`riderPhone`,`estimatedDeliveryTime`,`rating`,`reviewComment`,`reviewTime`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Order entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getOrderNo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getOrderNo());
        }
        statement.bindLong(4, entity.getRestaurantId());
        if (entity.getRestaurantName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRestaurantName());
        }
        statement.bindDouble(6, entity.getTotalAmount());
        statement.bindDouble(7, entity.getDeliveryFee());
        statement.bindDouble(8, entity.getPackingFee());
        statement.bindDouble(9, entity.getDiscountAmount());
        statement.bindDouble(10, entity.getActualAmount());
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, __OrderStatus_enumToString(entity.getStatus()));
        }
        final Long _tmp = __converters.fromDate(entity.getCreateTime());
        if (_tmp == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp);
        }
        final Long _tmp_1 = __converters.fromDate(entity.getPayTime());
        if (_tmp_1 == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, _tmp_1);
        }
        final Long _tmp_2 = __converters.fromDate(entity.getDeliveryTime());
        if (_tmp_2 == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, _tmp_2);
        }
        final Long _tmp_3 = __converters.fromDate(entity.getCompleteTime());
        if (_tmp_3 == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, _tmp_3);
        }
        statement.bindLong(16, entity.getAddressId());
        if (entity.getAddressDetail() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getAddressDetail());
        }
        if (entity.getReceiverName() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getReceiverName());
        }
        if (entity.getReceiverPhone() == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, entity.getReceiverPhone());
        }
        final String _tmp_4 = __converters.fromOrderItemList(entity.getItems());
        if (_tmp_4 == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, _tmp_4);
        }
        if (entity.getRiderName() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getRiderName());
        }
        if (entity.getRiderPhone() == null) {
          statement.bindNull(22);
        } else {
          statement.bindString(22, entity.getRiderPhone());
        }
        if (entity.getEstimatedDeliveryTime() == null) {
          statement.bindNull(23);
        } else {
          statement.bindString(23, entity.getEstimatedDeliveryTime());
        }
        statement.bindDouble(24, entity.getRating());
        if (entity.getReviewComment() == null) {
          statement.bindNull(25);
        } else {
          statement.bindString(25, entity.getReviewComment());
        }
        final Long _tmp_5 = __converters.fromDate(entity.getReviewTime());
        if (_tmp_5 == null) {
          statement.bindNull(26);
        } else {
          statement.bindLong(26, _tmp_5);
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
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Order entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `orders` SET `id` = ?,`userId` = ?,`orderNo` = ?,`restaurantId` = ?,`restaurantName` = ?,`totalAmount` = ?,`deliveryFee` = ?,`packingFee` = ?,`discountAmount` = ?,`actualAmount` = ?,`status` = ?,`createTime` = ?,`payTime` = ?,`deliveryTime` = ?,`completeTime` = ?,`addressId` = ?,`addressDetail` = ?,`receiverName` = ?,`receiverPhone` = ?,`items` = ?,`riderName` = ?,`riderPhone` = ?,`estimatedDeliveryTime` = ?,`rating` = ?,`reviewComment` = ?,`reviewTime` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Order entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getOrderNo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getOrderNo());
        }
        statement.bindLong(4, entity.getRestaurantId());
        if (entity.getRestaurantName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRestaurantName());
        }
        statement.bindDouble(6, entity.getTotalAmount());
        statement.bindDouble(7, entity.getDeliveryFee());
        statement.bindDouble(8, entity.getPackingFee());
        statement.bindDouble(9, entity.getDiscountAmount());
        statement.bindDouble(10, entity.getActualAmount());
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, __OrderStatus_enumToString(entity.getStatus()));
        }
        final Long _tmp = __converters.fromDate(entity.getCreateTime());
        if (_tmp == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp);
        }
        final Long _tmp_1 = __converters.fromDate(entity.getPayTime());
        if (_tmp_1 == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, _tmp_1);
        }
        final Long _tmp_2 = __converters.fromDate(entity.getDeliveryTime());
        if (_tmp_2 == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, _tmp_2);
        }
        final Long _tmp_3 = __converters.fromDate(entity.getCompleteTime());
        if (_tmp_3 == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, _tmp_3);
        }
        statement.bindLong(16, entity.getAddressId());
        if (entity.getAddressDetail() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getAddressDetail());
        }
        if (entity.getReceiverName() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getReceiverName());
        }
        if (entity.getReceiverPhone() == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, entity.getReceiverPhone());
        }
        final String _tmp_4 = __converters.fromOrderItemList(entity.getItems());
        if (_tmp_4 == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, _tmp_4);
        }
        if (entity.getRiderName() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getRiderName());
        }
        if (entity.getRiderPhone() == null) {
          statement.bindNull(22);
        } else {
          statement.bindString(22, entity.getRiderPhone());
        }
        if (entity.getEstimatedDeliveryTime() == null) {
          statement.bindNull(23);
        } else {
          statement.bindString(23, entity.getEstimatedDeliveryTime());
        }
        statement.bindDouble(24, entity.getRating());
        if (entity.getReviewComment() == null) {
          statement.bindNull(25);
        } else {
          statement.bindString(25, entity.getReviewComment());
        }
        final Long _tmp_5 = __converters.fromDate(entity.getReviewTime());
        if (_tmp_5 == null) {
          statement.bindNull(26);
        } else {
          statement.bindLong(26, _tmp_5);
        }
        statement.bindLong(27, entity.getId());
      }
    };
  }

  @Override
  public void insert(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrder.insert(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfOrder.handle(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfOrder.handle(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Order> getOrdersByUserId(final long userId) {
    final String _sql = "SELECT * FROM orders WHERE userId = ? ORDER BY createTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
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
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfReviewComment = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewComment");
      final int _cursorIndexOfReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewTime");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpOrderNo;
        if (_cursor.isNull(_cursorIndexOfOrderNo)) {
          _tmpOrderNo = null;
        } else {
          _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
        }
        _item.setOrderNo(_tmpOrderNo);
        final long _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpRestaurantName;
        if (_cursor.isNull(_cursorIndexOfRestaurantName)) {
          _tmpRestaurantName = null;
        } else {
          _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
        }
        _item.setRestaurantName(_tmpRestaurantName);
        final double _tmpTotalAmount;
        _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.setTotalAmount(_tmpTotalAmount);
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        _item.setDeliveryFee(_tmpDeliveryFee);
        final double _tmpPackingFee;
        _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
        _item.setPackingFee(_tmpPackingFee);
        final double _tmpDiscountAmount;
        _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.setDiscountAmount(_tmpDiscountAmount);
        final double _tmpActualAmount;
        _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
        _item.setActualAmount(_tmpActualAmount);
        final OrderStatus _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        }
        _item.setStatus(_tmpStatus);
        final Date _tmpCreateTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfCreateTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
        }
        _tmpCreateTime = __converters.toDate(_tmp);
        _item.setCreateTime(_tmpCreateTime);
        final Date _tmpPayTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfPayTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfPayTime);
        }
        _tmpPayTime = __converters.toDate(_tmp_1);
        _item.setPayTime(_tmpPayTime);
        final Date _tmpDeliveryTime;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDeliveryTime);
        }
        _tmpDeliveryTime = __converters.toDate(_tmp_2);
        _item.setDeliveryTime(_tmpDeliveryTime);
        final Date _tmpCompleteTime;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfCompleteTime);
        }
        _tmpCompleteTime = __converters.toDate(_tmp_3);
        _item.setCompleteTime(_tmpCompleteTime);
        final long _tmpAddressId;
        _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
        _item.setAddressId(_tmpAddressId);
        final String _tmpAddressDetail;
        if (_cursor.isNull(_cursorIndexOfAddressDetail)) {
          _tmpAddressDetail = null;
        } else {
          _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
        }
        _item.setAddressDetail(_tmpAddressDetail);
        final String _tmpReceiverName;
        if (_cursor.isNull(_cursorIndexOfReceiverName)) {
          _tmpReceiverName = null;
        } else {
          _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
        }
        _item.setReceiverName(_tmpReceiverName);
        final String _tmpReceiverPhone;
        if (_cursor.isNull(_cursorIndexOfReceiverPhone)) {
          _tmpReceiverPhone = null;
        } else {
          _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
        }
        _item.setReceiverPhone(_tmpReceiverPhone);
        final List<OrderItem> _tmpItems;
        final String _tmp_4;
        if (_cursor.isNull(_cursorIndexOfItems)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getString(_cursorIndexOfItems);
        }
        _tmpItems = __converters.toOrderItemList(_tmp_4);
        _item.setItems(_tmpItems);
        final String _tmpRiderName;
        if (_cursor.isNull(_cursorIndexOfRiderName)) {
          _tmpRiderName = null;
        } else {
          _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
        }
        _item.setRiderName(_tmpRiderName);
        final String _tmpRiderPhone;
        if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
          _tmpRiderPhone = null;
        } else {
          _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
        }
        _item.setRiderPhone(_tmpRiderPhone);
        final String _tmpEstimatedDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
          _tmpEstimatedDeliveryTime = null;
        } else {
          _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
        }
        _item.setEstimatedDeliveryTime(_tmpEstimatedDeliveryTime);
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        _item.setRating(_tmpRating);
        final String _tmpReviewComment;
        if (_cursor.isNull(_cursorIndexOfReviewComment)) {
          _tmpReviewComment = null;
        } else {
          _tmpReviewComment = _cursor.getString(_cursorIndexOfReviewComment);
        }
        _item.setReviewComment(_tmpReviewComment);
        final Date _tmpReviewTime;
        final Long _tmp_5;
        if (_cursor.isNull(_cursorIndexOfReviewTime)) {
          _tmp_5 = null;
        } else {
          _tmp_5 = _cursor.getLong(_cursorIndexOfReviewTime);
        }
        _tmpReviewTime = __converters.toDate(_tmp_5);
        _item.setReviewTime(_tmpReviewTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Order getOrderByIdAndUserId(final long orderId, final long userId) {
    final String _sql = "SELECT * FROM orders WHERE id = ? AND userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
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
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfReviewComment = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewComment");
      final int _cursorIndexOfReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewTime");
      final Order _result;
      if (_cursor.moveToFirst()) {
        _result = new Order();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpOrderNo;
        if (_cursor.isNull(_cursorIndexOfOrderNo)) {
          _tmpOrderNo = null;
        } else {
          _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
        }
        _result.setOrderNo(_tmpOrderNo);
        final long _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
        _result.setRestaurantId(_tmpRestaurantId);
        final String _tmpRestaurantName;
        if (_cursor.isNull(_cursorIndexOfRestaurantName)) {
          _tmpRestaurantName = null;
        } else {
          _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
        }
        _result.setRestaurantName(_tmpRestaurantName);
        final double _tmpTotalAmount;
        _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _result.setTotalAmount(_tmpTotalAmount);
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        _result.setDeliveryFee(_tmpDeliveryFee);
        final double _tmpPackingFee;
        _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
        _result.setPackingFee(_tmpPackingFee);
        final double _tmpDiscountAmount;
        _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _result.setDiscountAmount(_tmpDiscountAmount);
        final double _tmpActualAmount;
        _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
        _result.setActualAmount(_tmpActualAmount);
        final OrderStatus _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        }
        _result.setStatus(_tmpStatus);
        final Date _tmpCreateTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfCreateTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
        }
        _tmpCreateTime = __converters.toDate(_tmp);
        _result.setCreateTime(_tmpCreateTime);
        final Date _tmpPayTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfPayTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfPayTime);
        }
        _tmpPayTime = __converters.toDate(_tmp_1);
        _result.setPayTime(_tmpPayTime);
        final Date _tmpDeliveryTime;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDeliveryTime);
        }
        _tmpDeliveryTime = __converters.toDate(_tmp_2);
        _result.setDeliveryTime(_tmpDeliveryTime);
        final Date _tmpCompleteTime;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfCompleteTime);
        }
        _tmpCompleteTime = __converters.toDate(_tmp_3);
        _result.setCompleteTime(_tmpCompleteTime);
        final long _tmpAddressId;
        _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
        _result.setAddressId(_tmpAddressId);
        final String _tmpAddressDetail;
        if (_cursor.isNull(_cursorIndexOfAddressDetail)) {
          _tmpAddressDetail = null;
        } else {
          _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
        }
        _result.setAddressDetail(_tmpAddressDetail);
        final String _tmpReceiverName;
        if (_cursor.isNull(_cursorIndexOfReceiverName)) {
          _tmpReceiverName = null;
        } else {
          _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
        }
        _result.setReceiverName(_tmpReceiverName);
        final String _tmpReceiverPhone;
        if (_cursor.isNull(_cursorIndexOfReceiverPhone)) {
          _tmpReceiverPhone = null;
        } else {
          _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
        }
        _result.setReceiverPhone(_tmpReceiverPhone);
        final List<OrderItem> _tmpItems;
        final String _tmp_4;
        if (_cursor.isNull(_cursorIndexOfItems)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getString(_cursorIndexOfItems);
        }
        _tmpItems = __converters.toOrderItemList(_tmp_4);
        _result.setItems(_tmpItems);
        final String _tmpRiderName;
        if (_cursor.isNull(_cursorIndexOfRiderName)) {
          _tmpRiderName = null;
        } else {
          _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
        }
        _result.setRiderName(_tmpRiderName);
        final String _tmpRiderPhone;
        if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
          _tmpRiderPhone = null;
        } else {
          _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
        }
        _result.setRiderPhone(_tmpRiderPhone);
        final String _tmpEstimatedDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
          _tmpEstimatedDeliveryTime = null;
        } else {
          _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
        }
        _result.setEstimatedDeliveryTime(_tmpEstimatedDeliveryTime);
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        _result.setRating(_tmpRating);
        final String _tmpReviewComment;
        if (_cursor.isNull(_cursorIndexOfReviewComment)) {
          _tmpReviewComment = null;
        } else {
          _tmpReviewComment = _cursor.getString(_cursorIndexOfReviewComment);
        }
        _result.setReviewComment(_tmpReviewComment);
        final Date _tmpReviewTime;
        final Long _tmp_5;
        if (_cursor.isNull(_cursorIndexOfReviewTime)) {
          _tmp_5 = null;
        } else {
          _tmp_5 = _cursor.getLong(_cursorIndexOfReviewTime);
        }
        _tmpReviewTime = __converters.toDate(_tmp_5);
        _result.setReviewTime(_tmpReviewTime);
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
  public List<Order> getOrdersByUserIdAndStatus(final long userId, final OrderStatus status) {
    final String _sql = "SELECT * FROM orders WHERE userId = ? AND status = ? ORDER BY createTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, __OrderStatus_enumToString(status));
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
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
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfReviewComment = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewComment");
      final int _cursorIndexOfReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewTime");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpOrderNo;
        if (_cursor.isNull(_cursorIndexOfOrderNo)) {
          _tmpOrderNo = null;
        } else {
          _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
        }
        _item.setOrderNo(_tmpOrderNo);
        final long _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpRestaurantName;
        if (_cursor.isNull(_cursorIndexOfRestaurantName)) {
          _tmpRestaurantName = null;
        } else {
          _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
        }
        _item.setRestaurantName(_tmpRestaurantName);
        final double _tmpTotalAmount;
        _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.setTotalAmount(_tmpTotalAmount);
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        _item.setDeliveryFee(_tmpDeliveryFee);
        final double _tmpPackingFee;
        _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
        _item.setPackingFee(_tmpPackingFee);
        final double _tmpDiscountAmount;
        _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.setDiscountAmount(_tmpDiscountAmount);
        final double _tmpActualAmount;
        _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
        _item.setActualAmount(_tmpActualAmount);
        final OrderStatus _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        }
        _item.setStatus(_tmpStatus);
        final Date _tmpCreateTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfCreateTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
        }
        _tmpCreateTime = __converters.toDate(_tmp);
        _item.setCreateTime(_tmpCreateTime);
        final Date _tmpPayTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfPayTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfPayTime);
        }
        _tmpPayTime = __converters.toDate(_tmp_1);
        _item.setPayTime(_tmpPayTime);
        final Date _tmpDeliveryTime;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDeliveryTime);
        }
        _tmpDeliveryTime = __converters.toDate(_tmp_2);
        _item.setDeliveryTime(_tmpDeliveryTime);
        final Date _tmpCompleteTime;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfCompleteTime);
        }
        _tmpCompleteTime = __converters.toDate(_tmp_3);
        _item.setCompleteTime(_tmpCompleteTime);
        final long _tmpAddressId;
        _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
        _item.setAddressId(_tmpAddressId);
        final String _tmpAddressDetail;
        if (_cursor.isNull(_cursorIndexOfAddressDetail)) {
          _tmpAddressDetail = null;
        } else {
          _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
        }
        _item.setAddressDetail(_tmpAddressDetail);
        final String _tmpReceiverName;
        if (_cursor.isNull(_cursorIndexOfReceiverName)) {
          _tmpReceiverName = null;
        } else {
          _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
        }
        _item.setReceiverName(_tmpReceiverName);
        final String _tmpReceiverPhone;
        if (_cursor.isNull(_cursorIndexOfReceiverPhone)) {
          _tmpReceiverPhone = null;
        } else {
          _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
        }
        _item.setReceiverPhone(_tmpReceiverPhone);
        final List<OrderItem> _tmpItems;
        final String _tmp_4;
        if (_cursor.isNull(_cursorIndexOfItems)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getString(_cursorIndexOfItems);
        }
        _tmpItems = __converters.toOrderItemList(_tmp_4);
        _item.setItems(_tmpItems);
        final String _tmpRiderName;
        if (_cursor.isNull(_cursorIndexOfRiderName)) {
          _tmpRiderName = null;
        } else {
          _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
        }
        _item.setRiderName(_tmpRiderName);
        final String _tmpRiderPhone;
        if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
          _tmpRiderPhone = null;
        } else {
          _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
        }
        _item.setRiderPhone(_tmpRiderPhone);
        final String _tmpEstimatedDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
          _tmpEstimatedDeliveryTime = null;
        } else {
          _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
        }
        _item.setEstimatedDeliveryTime(_tmpEstimatedDeliveryTime);
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        _item.setRating(_tmpRating);
        final String _tmpReviewComment;
        if (_cursor.isNull(_cursorIndexOfReviewComment)) {
          _tmpReviewComment = null;
        } else {
          _tmpReviewComment = _cursor.getString(_cursorIndexOfReviewComment);
        }
        _item.setReviewComment(_tmpReviewComment);
        final Date _tmpReviewTime;
        final Long _tmp_5;
        if (_cursor.isNull(_cursorIndexOfReviewTime)) {
          _tmp_5 = null;
        } else {
          _tmp_5 = _cursor.getLong(_cursorIndexOfReviewTime);
        }
        _tmpReviewTime = __converters.toDate(_tmp_5);
        _item.setReviewTime(_tmpReviewTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Order> getAllOrders() {
    final String _sql = "SELECT * FROM orders ORDER BY createTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
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
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfReviewComment = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewComment");
      final int _cursorIndexOfReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewTime");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpOrderNo;
        if (_cursor.isNull(_cursorIndexOfOrderNo)) {
          _tmpOrderNo = null;
        } else {
          _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
        }
        _item.setOrderNo(_tmpOrderNo);
        final long _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpRestaurantName;
        if (_cursor.isNull(_cursorIndexOfRestaurantName)) {
          _tmpRestaurantName = null;
        } else {
          _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
        }
        _item.setRestaurantName(_tmpRestaurantName);
        final double _tmpTotalAmount;
        _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.setTotalAmount(_tmpTotalAmount);
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        _item.setDeliveryFee(_tmpDeliveryFee);
        final double _tmpPackingFee;
        _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
        _item.setPackingFee(_tmpPackingFee);
        final double _tmpDiscountAmount;
        _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.setDiscountAmount(_tmpDiscountAmount);
        final double _tmpActualAmount;
        _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
        _item.setActualAmount(_tmpActualAmount);
        final OrderStatus _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        }
        _item.setStatus(_tmpStatus);
        final Date _tmpCreateTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfCreateTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
        }
        _tmpCreateTime = __converters.toDate(_tmp);
        _item.setCreateTime(_tmpCreateTime);
        final Date _tmpPayTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfPayTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfPayTime);
        }
        _tmpPayTime = __converters.toDate(_tmp_1);
        _item.setPayTime(_tmpPayTime);
        final Date _tmpDeliveryTime;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDeliveryTime);
        }
        _tmpDeliveryTime = __converters.toDate(_tmp_2);
        _item.setDeliveryTime(_tmpDeliveryTime);
        final Date _tmpCompleteTime;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfCompleteTime);
        }
        _tmpCompleteTime = __converters.toDate(_tmp_3);
        _item.setCompleteTime(_tmpCompleteTime);
        final long _tmpAddressId;
        _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
        _item.setAddressId(_tmpAddressId);
        final String _tmpAddressDetail;
        if (_cursor.isNull(_cursorIndexOfAddressDetail)) {
          _tmpAddressDetail = null;
        } else {
          _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
        }
        _item.setAddressDetail(_tmpAddressDetail);
        final String _tmpReceiverName;
        if (_cursor.isNull(_cursorIndexOfReceiverName)) {
          _tmpReceiverName = null;
        } else {
          _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
        }
        _item.setReceiverName(_tmpReceiverName);
        final String _tmpReceiverPhone;
        if (_cursor.isNull(_cursorIndexOfReceiverPhone)) {
          _tmpReceiverPhone = null;
        } else {
          _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
        }
        _item.setReceiverPhone(_tmpReceiverPhone);
        final List<OrderItem> _tmpItems;
        final String _tmp_4;
        if (_cursor.isNull(_cursorIndexOfItems)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getString(_cursorIndexOfItems);
        }
        _tmpItems = __converters.toOrderItemList(_tmp_4);
        _item.setItems(_tmpItems);
        final String _tmpRiderName;
        if (_cursor.isNull(_cursorIndexOfRiderName)) {
          _tmpRiderName = null;
        } else {
          _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
        }
        _item.setRiderName(_tmpRiderName);
        final String _tmpRiderPhone;
        if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
          _tmpRiderPhone = null;
        } else {
          _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
        }
        _item.setRiderPhone(_tmpRiderPhone);
        final String _tmpEstimatedDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
          _tmpEstimatedDeliveryTime = null;
        } else {
          _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
        }
        _item.setEstimatedDeliveryTime(_tmpEstimatedDeliveryTime);
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        _item.setRating(_tmpRating);
        final String _tmpReviewComment;
        if (_cursor.isNull(_cursorIndexOfReviewComment)) {
          _tmpReviewComment = null;
        } else {
          _tmpReviewComment = _cursor.getString(_cursorIndexOfReviewComment);
        }
        _item.setReviewComment(_tmpReviewComment);
        final Date _tmpReviewTime;
        final Long _tmp_5;
        if (_cursor.isNull(_cursorIndexOfReviewTime)) {
          _tmp_5 = null;
        } else {
          _tmp_5 = _cursor.getLong(_cursorIndexOfReviewTime);
        }
        _tmpReviewTime = __converters.toDate(_tmp_5);
        _item.setReviewTime(_tmpReviewTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Order getOrderById(final long orderId) {
    final String _sql = "SELECT * FROM orders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
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
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfReviewComment = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewComment");
      final int _cursorIndexOfReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewTime");
      final Order _result;
      if (_cursor.moveToFirst()) {
        _result = new Order();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpOrderNo;
        if (_cursor.isNull(_cursorIndexOfOrderNo)) {
          _tmpOrderNo = null;
        } else {
          _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
        }
        _result.setOrderNo(_tmpOrderNo);
        final long _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
        _result.setRestaurantId(_tmpRestaurantId);
        final String _tmpRestaurantName;
        if (_cursor.isNull(_cursorIndexOfRestaurantName)) {
          _tmpRestaurantName = null;
        } else {
          _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
        }
        _result.setRestaurantName(_tmpRestaurantName);
        final double _tmpTotalAmount;
        _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _result.setTotalAmount(_tmpTotalAmount);
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        _result.setDeliveryFee(_tmpDeliveryFee);
        final double _tmpPackingFee;
        _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
        _result.setPackingFee(_tmpPackingFee);
        final double _tmpDiscountAmount;
        _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _result.setDiscountAmount(_tmpDiscountAmount);
        final double _tmpActualAmount;
        _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
        _result.setActualAmount(_tmpActualAmount);
        final OrderStatus _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        }
        _result.setStatus(_tmpStatus);
        final Date _tmpCreateTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfCreateTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
        }
        _tmpCreateTime = __converters.toDate(_tmp);
        _result.setCreateTime(_tmpCreateTime);
        final Date _tmpPayTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfPayTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfPayTime);
        }
        _tmpPayTime = __converters.toDate(_tmp_1);
        _result.setPayTime(_tmpPayTime);
        final Date _tmpDeliveryTime;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDeliveryTime);
        }
        _tmpDeliveryTime = __converters.toDate(_tmp_2);
        _result.setDeliveryTime(_tmpDeliveryTime);
        final Date _tmpCompleteTime;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfCompleteTime);
        }
        _tmpCompleteTime = __converters.toDate(_tmp_3);
        _result.setCompleteTime(_tmpCompleteTime);
        final long _tmpAddressId;
        _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
        _result.setAddressId(_tmpAddressId);
        final String _tmpAddressDetail;
        if (_cursor.isNull(_cursorIndexOfAddressDetail)) {
          _tmpAddressDetail = null;
        } else {
          _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
        }
        _result.setAddressDetail(_tmpAddressDetail);
        final String _tmpReceiverName;
        if (_cursor.isNull(_cursorIndexOfReceiverName)) {
          _tmpReceiverName = null;
        } else {
          _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
        }
        _result.setReceiverName(_tmpReceiverName);
        final String _tmpReceiverPhone;
        if (_cursor.isNull(_cursorIndexOfReceiverPhone)) {
          _tmpReceiverPhone = null;
        } else {
          _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
        }
        _result.setReceiverPhone(_tmpReceiverPhone);
        final List<OrderItem> _tmpItems;
        final String _tmp_4;
        if (_cursor.isNull(_cursorIndexOfItems)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getString(_cursorIndexOfItems);
        }
        _tmpItems = __converters.toOrderItemList(_tmp_4);
        _result.setItems(_tmpItems);
        final String _tmpRiderName;
        if (_cursor.isNull(_cursorIndexOfRiderName)) {
          _tmpRiderName = null;
        } else {
          _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
        }
        _result.setRiderName(_tmpRiderName);
        final String _tmpRiderPhone;
        if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
          _tmpRiderPhone = null;
        } else {
          _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
        }
        _result.setRiderPhone(_tmpRiderPhone);
        final String _tmpEstimatedDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
          _tmpEstimatedDeliveryTime = null;
        } else {
          _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
        }
        _result.setEstimatedDeliveryTime(_tmpEstimatedDeliveryTime);
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        _result.setRating(_tmpRating);
        final String _tmpReviewComment;
        if (_cursor.isNull(_cursorIndexOfReviewComment)) {
          _tmpReviewComment = null;
        } else {
          _tmpReviewComment = _cursor.getString(_cursorIndexOfReviewComment);
        }
        _result.setReviewComment(_tmpReviewComment);
        final Date _tmpReviewTime;
        final Long _tmp_5;
        if (_cursor.isNull(_cursorIndexOfReviewTime)) {
          _tmp_5 = null;
        } else {
          _tmp_5 = _cursor.getLong(_cursorIndexOfReviewTime);
        }
        _tmpReviewTime = __converters.toDate(_tmp_5);
        _result.setReviewTime(_tmpReviewTime);
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
  public List<Order> getOrdersByStatus(final OrderStatus status) {
    final String _sql = "SELECT * FROM orders WHERE status = ? ORDER BY createTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, __OrderStatus_enumToString(status));
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
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
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfReviewComment = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewComment");
      final int _cursorIndexOfReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewTime");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpOrderNo;
        if (_cursor.isNull(_cursorIndexOfOrderNo)) {
          _tmpOrderNo = null;
        } else {
          _tmpOrderNo = _cursor.getString(_cursorIndexOfOrderNo);
        }
        _item.setOrderNo(_tmpOrderNo);
        final long _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getLong(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpRestaurantName;
        if (_cursor.isNull(_cursorIndexOfRestaurantName)) {
          _tmpRestaurantName = null;
        } else {
          _tmpRestaurantName = _cursor.getString(_cursorIndexOfRestaurantName);
        }
        _item.setRestaurantName(_tmpRestaurantName);
        final double _tmpTotalAmount;
        _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.setTotalAmount(_tmpTotalAmount);
        final double _tmpDeliveryFee;
        _tmpDeliveryFee = _cursor.getDouble(_cursorIndexOfDeliveryFee);
        _item.setDeliveryFee(_tmpDeliveryFee);
        final double _tmpPackingFee;
        _tmpPackingFee = _cursor.getDouble(_cursorIndexOfPackingFee);
        _item.setPackingFee(_tmpPackingFee);
        final double _tmpDiscountAmount;
        _tmpDiscountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.setDiscountAmount(_tmpDiscountAmount);
        final double _tmpActualAmount;
        _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
        _item.setActualAmount(_tmpActualAmount);
        final OrderStatus _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = __OrderStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        }
        _item.setStatus(_tmpStatus);
        final Date _tmpCreateTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfCreateTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfCreateTime);
        }
        _tmpCreateTime = __converters.toDate(_tmp);
        _item.setCreateTime(_tmpCreateTime);
        final Date _tmpPayTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfPayTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfPayTime);
        }
        _tmpPayTime = __converters.toDate(_tmp_1);
        _item.setPayTime(_tmpPayTime);
        final Date _tmpDeliveryTime;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDeliveryTime)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDeliveryTime);
        }
        _tmpDeliveryTime = __converters.toDate(_tmp_2);
        _item.setDeliveryTime(_tmpDeliveryTime);
        final Date _tmpCompleteTime;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfCompleteTime);
        }
        _tmpCompleteTime = __converters.toDate(_tmp_3);
        _item.setCompleteTime(_tmpCompleteTime);
        final long _tmpAddressId;
        _tmpAddressId = _cursor.getLong(_cursorIndexOfAddressId);
        _item.setAddressId(_tmpAddressId);
        final String _tmpAddressDetail;
        if (_cursor.isNull(_cursorIndexOfAddressDetail)) {
          _tmpAddressDetail = null;
        } else {
          _tmpAddressDetail = _cursor.getString(_cursorIndexOfAddressDetail);
        }
        _item.setAddressDetail(_tmpAddressDetail);
        final String _tmpReceiverName;
        if (_cursor.isNull(_cursorIndexOfReceiverName)) {
          _tmpReceiverName = null;
        } else {
          _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
        }
        _item.setReceiverName(_tmpReceiverName);
        final String _tmpReceiverPhone;
        if (_cursor.isNull(_cursorIndexOfReceiverPhone)) {
          _tmpReceiverPhone = null;
        } else {
          _tmpReceiverPhone = _cursor.getString(_cursorIndexOfReceiverPhone);
        }
        _item.setReceiverPhone(_tmpReceiverPhone);
        final List<OrderItem> _tmpItems;
        final String _tmp_4;
        if (_cursor.isNull(_cursorIndexOfItems)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getString(_cursorIndexOfItems);
        }
        _tmpItems = __converters.toOrderItemList(_tmp_4);
        _item.setItems(_tmpItems);
        final String _tmpRiderName;
        if (_cursor.isNull(_cursorIndexOfRiderName)) {
          _tmpRiderName = null;
        } else {
          _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
        }
        _item.setRiderName(_tmpRiderName);
        final String _tmpRiderPhone;
        if (_cursor.isNull(_cursorIndexOfRiderPhone)) {
          _tmpRiderPhone = null;
        } else {
          _tmpRiderPhone = _cursor.getString(_cursorIndexOfRiderPhone);
        }
        _item.setRiderPhone(_tmpRiderPhone);
        final String _tmpEstimatedDeliveryTime;
        if (_cursor.isNull(_cursorIndexOfEstimatedDeliveryTime)) {
          _tmpEstimatedDeliveryTime = null;
        } else {
          _tmpEstimatedDeliveryTime = _cursor.getString(_cursorIndexOfEstimatedDeliveryTime);
        }
        _item.setEstimatedDeliveryTime(_tmpEstimatedDeliveryTime);
        final float _tmpRating;
        _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
        _item.setRating(_tmpRating);
        final String _tmpReviewComment;
        if (_cursor.isNull(_cursorIndexOfReviewComment)) {
          _tmpReviewComment = null;
        } else {
          _tmpReviewComment = _cursor.getString(_cursorIndexOfReviewComment);
        }
        _item.setReviewComment(_tmpReviewComment);
        final Date _tmpReviewTime;
        final Long _tmp_5;
        if (_cursor.isNull(_cursorIndexOfReviewTime)) {
          _tmp_5 = null;
        } else {
          _tmp_5 = _cursor.getLong(_cursorIndexOfReviewTime);
        }
        _tmpReviewTime = __converters.toDate(_tmp_5);
        _item.setReviewTime(_tmpReviewTime);
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

  private String __OrderStatus_enumToString(@NonNull final OrderStatus _value) {
    switch (_value) {
      case PENDING: return "PENDING";
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
      case "PENDING": return OrderStatus.PENDING;
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
