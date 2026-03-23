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
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.fooddelivery.app.data.model.Address;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
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
public final class AddressDao_Impl implements AddressDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Address> __insertionAdapterOfAddress;

  private final EntityDeletionOrUpdateAdapter<Address> __deletionAdapterOfAddress;

  private final EntityDeletionOrUpdateAdapter<Address> __updateAdapterOfAddress;

  private final SharedSQLiteStatement __preparedStmtOfClearDefaultAddress;

  public AddressDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAddress = new EntityInsertionAdapter<Address>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `addresses` (`id`,`name`,`phone`,`detailAddress`,`buildingInfo`,`floorInfo`,`doorplateInfo`,`addressTag`,`isDefault`,`latitude`,`longitude`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Address entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getPhone());
        statement.bindString(4, entity.getDetailAddress());
        if (entity.getBuildingInfo() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBuildingInfo());
        }
        if (entity.getFloorInfo() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFloorInfo());
        }
        if (entity.getDoorplateInfo() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDoorplateInfo());
        }
        if (entity.getAddressTag() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAddressTag());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(9, _tmp);
        if (entity.getLatitude() == null) {
          statement.bindNull(10);
        } else {
          statement.bindDouble(10, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getLongitude());
        }
      }
    };
    this.__deletionAdapterOfAddress = new EntityDeletionOrUpdateAdapter<Address>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `addresses` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Address entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAddress = new EntityDeletionOrUpdateAdapter<Address>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `addresses` SET `id` = ?,`name` = ?,`phone` = ?,`detailAddress` = ?,`buildingInfo` = ?,`floorInfo` = ?,`doorplateInfo` = ?,`addressTag` = ?,`isDefault` = ?,`latitude` = ?,`longitude` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Address entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getPhone());
        statement.bindString(4, entity.getDetailAddress());
        if (entity.getBuildingInfo() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBuildingInfo());
        }
        if (entity.getFloorInfo() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFloorInfo());
        }
        if (entity.getDoorplateInfo() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDoorplateInfo());
        }
        if (entity.getAddressTag() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAddressTag());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(9, _tmp);
        if (entity.getLatitude() == null) {
          statement.bindNull(10);
        } else {
          statement.bindDouble(10, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getLongitude());
        }
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfClearDefaultAddress = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE addresses SET isDefault = 0";
        return _query;
      }
    };
  }

  @Override
  public Object insertAddress(final Address address, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAddress.insertAndReturnId(address);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAddress(final Address address, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAddress.handle(address);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAddress(final Address address, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAddress.handle(address);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearDefaultAddress(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearDefaultAddress.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearDefaultAddress.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Address>> getAllAddresses() {
    final String _sql = "SELECT * FROM addresses ORDER BY isDefault DESC, id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"addresses"}, new Callable<List<Address>>() {
      @Override
      @NonNull
      public List<Address> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfDetailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "detailAddress");
          final int _cursorIndexOfBuildingInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "buildingInfo");
          final int _cursorIndexOfFloorInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "floorInfo");
          final int _cursorIndexOfDoorplateInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "doorplateInfo");
          final int _cursorIndexOfAddressTag = CursorUtil.getColumnIndexOrThrow(_cursor, "addressTag");
          final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "isDefault");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final List<Address> _result = new ArrayList<Address>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Address _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpDetailAddress;
            _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
            final String _tmpBuildingInfo;
            if (_cursor.isNull(_cursorIndexOfBuildingInfo)) {
              _tmpBuildingInfo = null;
            } else {
              _tmpBuildingInfo = _cursor.getString(_cursorIndexOfBuildingInfo);
            }
            final String _tmpFloorInfo;
            if (_cursor.isNull(_cursorIndexOfFloorInfo)) {
              _tmpFloorInfo = null;
            } else {
              _tmpFloorInfo = _cursor.getString(_cursorIndexOfFloorInfo);
            }
            final String _tmpDoorplateInfo;
            if (_cursor.isNull(_cursorIndexOfDoorplateInfo)) {
              _tmpDoorplateInfo = null;
            } else {
              _tmpDoorplateInfo = _cursor.getString(_cursorIndexOfDoorplateInfo);
            }
            final String _tmpAddressTag;
            if (_cursor.isNull(_cursorIndexOfAddressTag)) {
              _tmpAddressTag = null;
            } else {
              _tmpAddressTag = _cursor.getString(_cursorIndexOfAddressTag);
            }
            final boolean _tmpIsDefault;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
            _tmpIsDefault = _tmp != 0;
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            _item = new Address(_tmpId,_tmpName,_tmpPhone,_tmpDetailAddress,_tmpBuildingInfo,_tmpFloorInfo,_tmpDoorplateInfo,_tmpAddressTag,_tmpIsDefault,_tmpLatitude,_tmpLongitude);
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
  public Object getAddressById(final long addressId,
      final Continuation<? super Address> $completion) {
    final String _sql = "SELECT * FROM addresses WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, addressId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Address>() {
      @Override
      @Nullable
      public Address call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfDetailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "detailAddress");
          final int _cursorIndexOfBuildingInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "buildingInfo");
          final int _cursorIndexOfFloorInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "floorInfo");
          final int _cursorIndexOfDoorplateInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "doorplateInfo");
          final int _cursorIndexOfAddressTag = CursorUtil.getColumnIndexOrThrow(_cursor, "addressTag");
          final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "isDefault");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final Address _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpDetailAddress;
            _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
            final String _tmpBuildingInfo;
            if (_cursor.isNull(_cursorIndexOfBuildingInfo)) {
              _tmpBuildingInfo = null;
            } else {
              _tmpBuildingInfo = _cursor.getString(_cursorIndexOfBuildingInfo);
            }
            final String _tmpFloorInfo;
            if (_cursor.isNull(_cursorIndexOfFloorInfo)) {
              _tmpFloorInfo = null;
            } else {
              _tmpFloorInfo = _cursor.getString(_cursorIndexOfFloorInfo);
            }
            final String _tmpDoorplateInfo;
            if (_cursor.isNull(_cursorIndexOfDoorplateInfo)) {
              _tmpDoorplateInfo = null;
            } else {
              _tmpDoorplateInfo = _cursor.getString(_cursorIndexOfDoorplateInfo);
            }
            final String _tmpAddressTag;
            if (_cursor.isNull(_cursorIndexOfAddressTag)) {
              _tmpAddressTag = null;
            } else {
              _tmpAddressTag = _cursor.getString(_cursorIndexOfAddressTag);
            }
            final boolean _tmpIsDefault;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
            _tmpIsDefault = _tmp != 0;
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            _result = new Address(_tmpId,_tmpName,_tmpPhone,_tmpDetailAddress,_tmpBuildingInfo,_tmpFloorInfo,_tmpDoorplateInfo,_tmpAddressTag,_tmpIsDefault,_tmpLatitude,_tmpLongitude);
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
  public Object getDefaultAddress(final Continuation<? super Address> $completion) {
    final String _sql = "SELECT * FROM addresses WHERE isDefault = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Address>() {
      @Override
      @Nullable
      public Address call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfDetailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "detailAddress");
          final int _cursorIndexOfBuildingInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "buildingInfo");
          final int _cursorIndexOfFloorInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "floorInfo");
          final int _cursorIndexOfDoorplateInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "doorplateInfo");
          final int _cursorIndexOfAddressTag = CursorUtil.getColumnIndexOrThrow(_cursor, "addressTag");
          final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "isDefault");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final Address _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpDetailAddress;
            _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
            final String _tmpBuildingInfo;
            if (_cursor.isNull(_cursorIndexOfBuildingInfo)) {
              _tmpBuildingInfo = null;
            } else {
              _tmpBuildingInfo = _cursor.getString(_cursorIndexOfBuildingInfo);
            }
            final String _tmpFloorInfo;
            if (_cursor.isNull(_cursorIndexOfFloorInfo)) {
              _tmpFloorInfo = null;
            } else {
              _tmpFloorInfo = _cursor.getString(_cursorIndexOfFloorInfo);
            }
            final String _tmpDoorplateInfo;
            if (_cursor.isNull(_cursorIndexOfDoorplateInfo)) {
              _tmpDoorplateInfo = null;
            } else {
              _tmpDoorplateInfo = _cursor.getString(_cursorIndexOfDoorplateInfo);
            }
            final String _tmpAddressTag;
            if (_cursor.isNull(_cursorIndexOfAddressTag)) {
              _tmpAddressTag = null;
            } else {
              _tmpAddressTag = _cursor.getString(_cursorIndexOfAddressTag);
            }
            final boolean _tmpIsDefault;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
            _tmpIsDefault = _tmp != 0;
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            _result = new Address(_tmpId,_tmpName,_tmpPhone,_tmpDetailAddress,_tmpBuildingInfo,_tmpFloorInfo,_tmpDoorplateInfo,_tmpAddressTag,_tmpIsDefault,_tmpLatitude,_tmpLongitude);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
