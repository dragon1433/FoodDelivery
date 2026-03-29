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
import com.fooddelivery.app.data.model.Address;
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
        return "INSERT OR REPLACE INTO `addresses` (`id`,`name`,`phone`,`detailAddress`,`province`,`city`,`district`,`street`,`buildingInfo`,`floorInfo`,`doorplateInfo`,`addressTag`,`isDefault`,`latitude`,`longitude`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Address entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPhone());
        }
        if (entity.getDetailAddress() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDetailAddress());
        }
        if (entity.getProvince() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getProvince());
        }
        if (entity.getCity() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCity());
        }
        if (entity.getDistrict() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDistrict());
        }
        if (entity.getStreet() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStreet());
        }
        if (entity.getBuildingInfo() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getBuildingInfo());
        }
        if (entity.getFloorInfo() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getFloorInfo());
        }
        if (entity.getDoorplateInfo() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDoorplateInfo());
        }
        if (entity.getAddressTag() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getAddressTag());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(13, _tmp);
        if (entity.getLatitude() == null) {
          statement.bindNull(14);
        } else {
          statement.bindDouble(14, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(15);
        } else {
          statement.bindDouble(15, entity.getLongitude());
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
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Address entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAddress = new EntityDeletionOrUpdateAdapter<Address>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `addresses` SET `id` = ?,`name` = ?,`phone` = ?,`detailAddress` = ?,`province` = ?,`city` = ?,`district` = ?,`street` = ?,`buildingInfo` = ?,`floorInfo` = ?,`doorplateInfo` = ?,`addressTag` = ?,`isDefault` = ?,`latitude` = ?,`longitude` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Address entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPhone());
        }
        if (entity.getDetailAddress() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDetailAddress());
        }
        if (entity.getProvince() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getProvince());
        }
        if (entity.getCity() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCity());
        }
        if (entity.getDistrict() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDistrict());
        }
        if (entity.getStreet() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStreet());
        }
        if (entity.getBuildingInfo() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getBuildingInfo());
        }
        if (entity.getFloorInfo() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getFloorInfo());
        }
        if (entity.getDoorplateInfo() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDoorplateInfo());
        }
        if (entity.getAddressTag() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getAddressTag());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(13, _tmp);
        if (entity.getLatitude() == null) {
          statement.bindNull(14);
        } else {
          statement.bindDouble(14, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(15);
        } else {
          statement.bindDouble(15, entity.getLongitude());
        }
        statement.bindLong(16, entity.getId());
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
  public void insert(final Address address) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAddress.insert(address);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Address address) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAddress.handle(address);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Address address) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAddress.handle(address);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearDefaultAddress() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearDefaultAddress.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfClearDefaultAddress.release(_stmt);
    }
  }

  @Override
  public List<Address> getAllAddresses() {
    final String _sql = "SELECT * FROM addresses ORDER BY isDefault DESC, id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfDetailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "detailAddress");
      final int _cursorIndexOfProvince = CursorUtil.getColumnIndexOrThrow(_cursor, "province");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "district");
      final int _cursorIndexOfStreet = CursorUtil.getColumnIndexOrThrow(_cursor, "street");
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
        _item = new Address();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _item.setPhone(_tmpPhone);
        final String _tmpDetailAddress;
        if (_cursor.isNull(_cursorIndexOfDetailAddress)) {
          _tmpDetailAddress = null;
        } else {
          _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
        }
        _item.setDetailAddress(_tmpDetailAddress);
        final String _tmpProvince;
        if (_cursor.isNull(_cursorIndexOfProvince)) {
          _tmpProvince = null;
        } else {
          _tmpProvince = _cursor.getString(_cursorIndexOfProvince);
        }
        _item.setProvince(_tmpProvince);
        final String _tmpCity;
        if (_cursor.isNull(_cursorIndexOfCity)) {
          _tmpCity = null;
        } else {
          _tmpCity = _cursor.getString(_cursorIndexOfCity);
        }
        _item.setCity(_tmpCity);
        final String _tmpDistrict;
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _tmpDistrict = null;
        } else {
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
        }
        _item.setDistrict(_tmpDistrict);
        final String _tmpStreet;
        if (_cursor.isNull(_cursorIndexOfStreet)) {
          _tmpStreet = null;
        } else {
          _tmpStreet = _cursor.getString(_cursorIndexOfStreet);
        }
        _item.setStreet(_tmpStreet);
        final String _tmpBuildingInfo;
        if (_cursor.isNull(_cursorIndexOfBuildingInfo)) {
          _tmpBuildingInfo = null;
        } else {
          _tmpBuildingInfo = _cursor.getString(_cursorIndexOfBuildingInfo);
        }
        _item.setBuildingInfo(_tmpBuildingInfo);
        final String _tmpFloorInfo;
        if (_cursor.isNull(_cursorIndexOfFloorInfo)) {
          _tmpFloorInfo = null;
        } else {
          _tmpFloorInfo = _cursor.getString(_cursorIndexOfFloorInfo);
        }
        _item.setFloorInfo(_tmpFloorInfo);
        final String _tmpDoorplateInfo;
        if (_cursor.isNull(_cursorIndexOfDoorplateInfo)) {
          _tmpDoorplateInfo = null;
        } else {
          _tmpDoorplateInfo = _cursor.getString(_cursorIndexOfDoorplateInfo);
        }
        _item.setDoorplateInfo(_tmpDoorplateInfo);
        final String _tmpAddressTag;
        if (_cursor.isNull(_cursorIndexOfAddressTag)) {
          _tmpAddressTag = null;
        } else {
          _tmpAddressTag = _cursor.getString(_cursorIndexOfAddressTag);
        }
        _item.setAddressTag(_tmpAddressTag);
        final boolean _tmpIsDefault;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
        _tmpIsDefault = _tmp != 0;
        _item.setDefault(_tmpIsDefault);
        final Double _tmpLatitude;
        if (_cursor.isNull(_cursorIndexOfLatitude)) {
          _tmpLatitude = null;
        } else {
          _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
        }
        _item.setLatitude(_tmpLatitude);
        final Double _tmpLongitude;
        if (_cursor.isNull(_cursorIndexOfLongitude)) {
          _tmpLongitude = null;
        } else {
          _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
        }
        _item.setLongitude(_tmpLongitude);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Address getAddressById(final long addressId) {
    final String _sql = "SELECT * FROM addresses WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, addressId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfDetailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "detailAddress");
      final int _cursorIndexOfProvince = CursorUtil.getColumnIndexOrThrow(_cursor, "province");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "district");
      final int _cursorIndexOfStreet = CursorUtil.getColumnIndexOrThrow(_cursor, "street");
      final int _cursorIndexOfBuildingInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "buildingInfo");
      final int _cursorIndexOfFloorInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "floorInfo");
      final int _cursorIndexOfDoorplateInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "doorplateInfo");
      final int _cursorIndexOfAddressTag = CursorUtil.getColumnIndexOrThrow(_cursor, "addressTag");
      final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "isDefault");
      final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
      final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
      final Address _result;
      if (_cursor.moveToFirst()) {
        _result = new Address();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _result.setPhone(_tmpPhone);
        final String _tmpDetailAddress;
        if (_cursor.isNull(_cursorIndexOfDetailAddress)) {
          _tmpDetailAddress = null;
        } else {
          _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
        }
        _result.setDetailAddress(_tmpDetailAddress);
        final String _tmpProvince;
        if (_cursor.isNull(_cursorIndexOfProvince)) {
          _tmpProvince = null;
        } else {
          _tmpProvince = _cursor.getString(_cursorIndexOfProvince);
        }
        _result.setProvince(_tmpProvince);
        final String _tmpCity;
        if (_cursor.isNull(_cursorIndexOfCity)) {
          _tmpCity = null;
        } else {
          _tmpCity = _cursor.getString(_cursorIndexOfCity);
        }
        _result.setCity(_tmpCity);
        final String _tmpDistrict;
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _tmpDistrict = null;
        } else {
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
        }
        _result.setDistrict(_tmpDistrict);
        final String _tmpStreet;
        if (_cursor.isNull(_cursorIndexOfStreet)) {
          _tmpStreet = null;
        } else {
          _tmpStreet = _cursor.getString(_cursorIndexOfStreet);
        }
        _result.setStreet(_tmpStreet);
        final String _tmpBuildingInfo;
        if (_cursor.isNull(_cursorIndexOfBuildingInfo)) {
          _tmpBuildingInfo = null;
        } else {
          _tmpBuildingInfo = _cursor.getString(_cursorIndexOfBuildingInfo);
        }
        _result.setBuildingInfo(_tmpBuildingInfo);
        final String _tmpFloorInfo;
        if (_cursor.isNull(_cursorIndexOfFloorInfo)) {
          _tmpFloorInfo = null;
        } else {
          _tmpFloorInfo = _cursor.getString(_cursorIndexOfFloorInfo);
        }
        _result.setFloorInfo(_tmpFloorInfo);
        final String _tmpDoorplateInfo;
        if (_cursor.isNull(_cursorIndexOfDoorplateInfo)) {
          _tmpDoorplateInfo = null;
        } else {
          _tmpDoorplateInfo = _cursor.getString(_cursorIndexOfDoorplateInfo);
        }
        _result.setDoorplateInfo(_tmpDoorplateInfo);
        final String _tmpAddressTag;
        if (_cursor.isNull(_cursorIndexOfAddressTag)) {
          _tmpAddressTag = null;
        } else {
          _tmpAddressTag = _cursor.getString(_cursorIndexOfAddressTag);
        }
        _result.setAddressTag(_tmpAddressTag);
        final boolean _tmpIsDefault;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
        _tmpIsDefault = _tmp != 0;
        _result.setDefault(_tmpIsDefault);
        final Double _tmpLatitude;
        if (_cursor.isNull(_cursorIndexOfLatitude)) {
          _tmpLatitude = null;
        } else {
          _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
        }
        _result.setLatitude(_tmpLatitude);
        final Double _tmpLongitude;
        if (_cursor.isNull(_cursorIndexOfLongitude)) {
          _tmpLongitude = null;
        } else {
          _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
        }
        _result.setLongitude(_tmpLongitude);
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
  public Address getDefaultAddress() {
    final String _sql = "SELECT * FROM addresses WHERE isDefault = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfDetailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "detailAddress");
      final int _cursorIndexOfProvince = CursorUtil.getColumnIndexOrThrow(_cursor, "province");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "district");
      final int _cursorIndexOfStreet = CursorUtil.getColumnIndexOrThrow(_cursor, "street");
      final int _cursorIndexOfBuildingInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "buildingInfo");
      final int _cursorIndexOfFloorInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "floorInfo");
      final int _cursorIndexOfDoorplateInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "doorplateInfo");
      final int _cursorIndexOfAddressTag = CursorUtil.getColumnIndexOrThrow(_cursor, "addressTag");
      final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "isDefault");
      final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
      final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
      final Address _result;
      if (_cursor.moveToFirst()) {
        _result = new Address();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _result.setPhone(_tmpPhone);
        final String _tmpDetailAddress;
        if (_cursor.isNull(_cursorIndexOfDetailAddress)) {
          _tmpDetailAddress = null;
        } else {
          _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
        }
        _result.setDetailAddress(_tmpDetailAddress);
        final String _tmpProvince;
        if (_cursor.isNull(_cursorIndexOfProvince)) {
          _tmpProvince = null;
        } else {
          _tmpProvince = _cursor.getString(_cursorIndexOfProvince);
        }
        _result.setProvince(_tmpProvince);
        final String _tmpCity;
        if (_cursor.isNull(_cursorIndexOfCity)) {
          _tmpCity = null;
        } else {
          _tmpCity = _cursor.getString(_cursorIndexOfCity);
        }
        _result.setCity(_tmpCity);
        final String _tmpDistrict;
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _tmpDistrict = null;
        } else {
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
        }
        _result.setDistrict(_tmpDistrict);
        final String _tmpStreet;
        if (_cursor.isNull(_cursorIndexOfStreet)) {
          _tmpStreet = null;
        } else {
          _tmpStreet = _cursor.getString(_cursorIndexOfStreet);
        }
        _result.setStreet(_tmpStreet);
        final String _tmpBuildingInfo;
        if (_cursor.isNull(_cursorIndexOfBuildingInfo)) {
          _tmpBuildingInfo = null;
        } else {
          _tmpBuildingInfo = _cursor.getString(_cursorIndexOfBuildingInfo);
        }
        _result.setBuildingInfo(_tmpBuildingInfo);
        final String _tmpFloorInfo;
        if (_cursor.isNull(_cursorIndexOfFloorInfo)) {
          _tmpFloorInfo = null;
        } else {
          _tmpFloorInfo = _cursor.getString(_cursorIndexOfFloorInfo);
        }
        _result.setFloorInfo(_tmpFloorInfo);
        final String _tmpDoorplateInfo;
        if (_cursor.isNull(_cursorIndexOfDoorplateInfo)) {
          _tmpDoorplateInfo = null;
        } else {
          _tmpDoorplateInfo = _cursor.getString(_cursorIndexOfDoorplateInfo);
        }
        _result.setDoorplateInfo(_tmpDoorplateInfo);
        final String _tmpAddressTag;
        if (_cursor.isNull(_cursorIndexOfAddressTag)) {
          _tmpAddressTag = null;
        } else {
          _tmpAddressTag = _cursor.getString(_cursorIndexOfAddressTag);
        }
        _result.setAddressTag(_tmpAddressTag);
        final boolean _tmpIsDefault;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
        _tmpIsDefault = _tmp != 0;
        _result.setDefault(_tmpIsDefault);
        final Double _tmpLatitude;
        if (_cursor.isNull(_cursorIndexOfLatitude)) {
          _tmpLatitude = null;
        } else {
          _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
        }
        _result.setLatitude(_tmpLatitude);
        final Double _tmpLongitude;
        if (_cursor.isNull(_cursorIndexOfLongitude)) {
          _tmpLongitude = null;
        } else {
          _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
        }
        _result.setLongitude(_tmpLongitude);
      } else {
        _result = null;
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
