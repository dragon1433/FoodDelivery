package com.fooddelivery.app.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.fooddelivery.app.data.model.User;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfLogout;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `users` (`id`,`phone`,`password`,`name`,`avatar`,`createTime`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getPhone() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPhone());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPassword());
        }
        if (entity.getName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getName());
        }
        if (entity.getAvatar() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAvatar());
        }
        statement.bindLong(6, entity.getCreateTime());
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`phone` = ?,`password` = ?,`name` = ?,`avatar` = ?,`createTime` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getPhone() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPhone());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPassword());
        }
        if (entity.getName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getName());
        }
        if (entity.getAvatar() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAvatar());
        }
        statement.bindLong(6, entity.getCreateTime());
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfLogout = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfUser.insertAndReturnId(user);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void logout(final long userId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfLogout.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, userId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfLogout.release(_stmt);
    }
  }

  @Override
  public User getUserByPhone(final String phone) {
    final String _sql = "SELECT * FROM users WHERE phone = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (phone == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, phone);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final User _result;
      if (_cursor.moveToFirst()) {
        _result = new User();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _result.setPhone(_tmpPhone);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result.setPassword(_tmpPassword);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        }
        _result.setAvatar(_tmpAvatar);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _result.setCreateTime(_tmpCreateTime);
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
  public User login(final String phone, final String password) {
    final String _sql = "SELECT * FROM users WHERE phone = ? AND password = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (phone == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, phone);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final User _result;
      if (_cursor.moveToFirst()) {
        _result = new User();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _result.setPhone(_tmpPhone);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result.setPassword(_tmpPassword);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        }
        _result.setAvatar(_tmpAvatar);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _result.setCreateTime(_tmpCreateTime);
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
  public LiveData<User> getCurrentUser(final long userId) {
    final String _sql = "SELECT * FROM users WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"users"}, false, new Callable<User>() {
      @Override
      @Nullable
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
          final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
          final User _result;
          if (_cursor.moveToFirst()) {
            _result = new User();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _result.setId(_tmpId);
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            _result.setPhone(_tmpPhone);
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            _result.setPassword(_tmpPassword);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _result.setName(_tmpName);
            final String _tmpAvatar;
            if (_cursor.isNull(_cursorIndexOfAvatar)) {
              _tmpAvatar = null;
            } else {
              _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
            }
            _result.setAvatar(_tmpAvatar);
            final long _tmpCreateTime;
            _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
            _result.setCreateTime(_tmpCreateTime);
          } else {
            _result = null;
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
  public User getUserById(final long userId) {
    final String _sql = "SELECT * FROM users WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final User _result;
      if (_cursor.moveToFirst()) {
        _result = new User();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _result.setPhone(_tmpPhone);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result.setPassword(_tmpPassword);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        }
        _result.setAvatar(_tmpAvatar);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _result.setCreateTime(_tmpCreateTime);
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
