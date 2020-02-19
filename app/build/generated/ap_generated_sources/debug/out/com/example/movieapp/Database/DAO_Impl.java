package com.example.movieapp.Database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.movieapp.POJO.MovieData;
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class DAO_Impl implements DAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMovieData;

  public DAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieData = new EntityInsertionAdapter<MovieData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Movies`(`popularity`,`vote_count`,`vote_average`,`video`,`adult`,`poster_path`,`backdrop_path`,`original_language`,`original_title`,`title`,`overview`,`release_date`,`id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieData value) {
        if (value.getPopularity() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindDouble(1, value.getPopularity());
        }
        if (value.getVote_count() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindDouble(2, value.getVote_count());
        }
        if (value.getVote_average() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindDouble(3, value.getVote_average());
        }
        final int _tmp;
        _tmp = value.isVideo() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isAdult() ? 1 : 0;
        stmt.bindLong(5, _tmp_1);
        if (value.getPoster_path() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPoster_path());
        }
        if (value.getBackdrop_path() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getBackdrop_path());
        }
        if (value.getOriginal_language() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getOriginal_language());
        }
        if (value.getOriginal_title() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getOriginal_title());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getTitle());
        }
        if (value.getOverview() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getOverview());
        }
        if (value.getRelease_date() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getRelease_date());
        }
        stmt.bindLong(13, value.getId());
      }
    };
  }

  @Override
  public void insertMovie(MovieData movie) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMovieData.insert(movie);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<MovieData>> get_All_Movies() {
    final String _sql = "select * from Movies";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<MovieData>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<MovieData> compute() {
        if (_observer == null) {
          _observer = new Observer("Movies") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfPopularity = _cursor.getColumnIndexOrThrow("popularity");
          final int _cursorIndexOfVoteCount = _cursor.getColumnIndexOrThrow("vote_count");
          final int _cursorIndexOfVoteAverage = _cursor.getColumnIndexOrThrow("vote_average");
          final int _cursorIndexOfVideo = _cursor.getColumnIndexOrThrow("video");
          final int _cursorIndexOfAdult = _cursor.getColumnIndexOrThrow("adult");
          final int _cursorIndexOfPosterPath = _cursor.getColumnIndexOrThrow("poster_path");
          final int _cursorIndexOfBackdropPath = _cursor.getColumnIndexOrThrow("backdrop_path");
          final int _cursorIndexOfOriginalLanguage = _cursor.getColumnIndexOrThrow("original_language");
          final int _cursorIndexOfOriginalTitle = _cursor.getColumnIndexOrThrow("original_title");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfOverview = _cursor.getColumnIndexOrThrow("overview");
          final int _cursorIndexOfReleaseDate = _cursor.getColumnIndexOrThrow("release_date");
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final List<MovieData> _result = new ArrayList<MovieData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MovieData _item;
            _item = new MovieData();
            final Double _tmpPopularity;
            if (_cursor.isNull(_cursorIndexOfPopularity)) {
              _tmpPopularity = null;
            } else {
              _tmpPopularity = _cursor.getDouble(_cursorIndexOfPopularity);
            }
            _item.setPopularity(_tmpPopularity);
            final Double _tmpVote_count;
            if (_cursor.isNull(_cursorIndexOfVoteCount)) {
              _tmpVote_count = null;
            } else {
              _tmpVote_count = _cursor.getDouble(_cursorIndexOfVoteCount);
            }
            _item.setVote_count(_tmpVote_count);
            final Double _tmpVote_average;
            if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
              _tmpVote_average = null;
            } else {
              _tmpVote_average = _cursor.getDouble(_cursorIndexOfVoteAverage);
            }
            _item.setVote_average(_tmpVote_average);
            final boolean _tmpVideo;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfVideo);
            _tmpVideo = _tmp != 0;
            _item.setVideo(_tmpVideo);
            final boolean _tmpAdult;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfAdult);
            _tmpAdult = _tmp_1 != 0;
            _item.setAdult(_tmpAdult);
            final String _tmpPoster_path;
            _tmpPoster_path = _cursor.getString(_cursorIndexOfPosterPath);
            _item.setPoster_path(_tmpPoster_path);
            final String _tmpBackdrop_path;
            _tmpBackdrop_path = _cursor.getString(_cursorIndexOfBackdropPath);
            _item.setBackdrop_path(_tmpBackdrop_path);
            final String _tmpOriginal_language;
            _tmpOriginal_language = _cursor.getString(_cursorIndexOfOriginalLanguage);
            _item.setOriginal_language(_tmpOriginal_language);
            final String _tmpOriginal_title;
            _tmpOriginal_title = _cursor.getString(_cursorIndexOfOriginalTitle);
            _item.setOriginal_title(_tmpOriginal_title);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpOverview;
            _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            _item.setOverview(_tmpOverview);
            final String _tmpRelease_date;
            _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            _item.setRelease_date(_tmpRelease_date);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
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
    }.getLiveData();
  }
}
