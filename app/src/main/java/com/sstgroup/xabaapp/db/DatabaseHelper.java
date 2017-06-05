package com.sstgroup.xabaapp.db;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sstgroup.xabaapp.models.Category;
import com.sstgroup.xabaapp.models.CategoryDao;
import com.sstgroup.xabaapp.models.Country;
import com.sstgroup.xabaapp.models.CountryDao;
import com.sstgroup.xabaapp.models.County;
import com.sstgroup.xabaapp.models.CountyDao;
import com.sstgroup.xabaapp.models.DaoMaster;
import com.sstgroup.xabaapp.models.DaoSession;
import com.sstgroup.xabaapp.models.Industry;
import com.sstgroup.xabaapp.models.IndustryDao;
import com.sstgroup.xabaapp.models.JoinUsersWithProfessions;
import com.sstgroup.xabaapp.models.JoinUsersWithProfessionsDao;
import com.sstgroup.xabaapp.models.Language;
import com.sstgroup.xabaapp.models.LanguageDao;
import com.sstgroup.xabaapp.models.Profession;
import com.sstgroup.xabaapp.models.ProfessionDao;
import com.sstgroup.xabaapp.models.SubCounty;
import com.sstgroup.xabaapp.models.SubCountyDao;
import com.sstgroup.xabaapp.models.Token;
import com.sstgroup.xabaapp.models.TokenDao;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.UserDao;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.utils.Preferences;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by julianlubenov on 5/10/17.
 */

public class DatabaseHelper {

    private static DatabaseHelper instance;

    private static DaoMaster.DevOpenHelper helper;
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static LanguageDao languageDao;
    private static CountryDao countryDao;
    private static CountyDao countyDao;
    private static SubCountyDao subCountyDao;
    private static IndustryDao industryDao;
    private static CategoryDao categoryDao;
    private static ProfessionDao professionDao;
    private static UserDao userDao;
    private static TokenDao tokenDao;

    private DatabaseHelper() {

    }

    public static DatabaseHelper getInstance(Application application) {
        if (instance == null) {
            instance = new DatabaseHelper();

            helper = new DaoMaster.DevOpenHelper(application.getApplicationContext(), "xaba-db", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }

        return instance;
    }

    public void deleteLocationTables() {
        daoSession.getLanguageDao().deleteAll();
        daoSession.getCountryDao().deleteAll();
        daoSession.getCountyDao().deleteAll();
        daoSession.getSubCountyDao().deleteAll();
    }

    public void deleteProfessionTables() {
        daoSession.getIndustryDao().deleteAll();
        daoSession.getCategoryDao().deleteAll();
        daoSession.getProfessionDao().deleteAll();
    }

    public void insertOrReplaceLanguages(ArrayList<Language> languages) {
        languageDao = daoSession.getLanguageDao();
        languageDao.insertInTx(languages);
    }

    public List<String> getLanguages() {
        List<String> languages = new ArrayList<>();
        languageDao = daoSession.getLanguageDao();

        Cursor cursor = languageDao.getDatabase().rawQuery("SELECT name FROM language", null);
        while (cursor.moveToNext()) {
            languages.add(cursor.getString(0));
        }
        return languages;
    }

    public void insertOrReplaceCountries(ArrayList<Country> countries) {
        countryDao = daoSession.getCountryDao();
        countryDao.insertInTx(countries);

        for (Country country : countries) {
            insertOrReplaceCounties(country.getCounties());
        }
    }

    public List<String> getCountries() {
        List<String> countries = new ArrayList<>();
        countryDao = daoSession.getCountryDao();

        Cursor cursor = countryDao.getDatabase().rawQuery("SELECT name FROM country", null);
        while (cursor.moveToNext()) {
            countries.add(cursor.getString(0));
        }
        return countries;
    }

    public void insertOrReplaceCounties(List<County> counties) {
        countyDao = daoSession.getCountyDao();
        countyDao.insertInTx(counties);

        for (County county : counties) {
            insertOrReplaceSubCounties(county.getSubCounties());
        }
    }

    public List<String> getCounties() {

        List<String> counties = new ArrayList<>();
        countyDao = daoSession.getCountyDao();

        Cursor cursor = countyDao.getDatabase().rawQuery("SELECT name FROM county", null);
        while (cursor.moveToNext()) {
            counties.add(cursor.getString(0));
        }
        return counties;
    }

    public void insertOrReplaceSubCounties(List<SubCounty> subCounties) {
        subCountyDao = daoSession.getSubCountyDao();
        subCountyDao.insertInTx(subCounties);
    }

   /* public List<String> getSubCounties(String county) {

        List<String> subCounties = new ArrayList<>();

//        Cursor cursor = countyDao.getDatabase().rawQuery("SELECT sub_county.name FROM county JOIN sub_county ON county._id=sub_county.county_id", null);
        String countyId = "";

        Cursor cursor = countyDao.getDatabase().rawQuery("SELECT _id FROM county WHERE name='" + county + "'", null);
        while (cursor.moveToNext()) {
            countyId = cursor.getString(0);
        }
        Timber.d("TEST countyId= " + countyId);

        cursor = subCountyDao.getDatabase().rawQuery("SELECT name FROM sub_county WHERE county_id='" + county + "'", null);
//        cursor = subCountyDao.getDatabase().rawQuery("SELECT name FROM sub_county", null);
        while (cursor.moveToNext()) {
            Timber.d("TEST moveToNext");
            subCounties.add(cursor.getString(0));
        }

        return subCounties;
    }*/

    public List<String> getSubCounties(String countyName) {
        countyDao = daoSession.getCountyDao();

        List<String> subCountiesString = new ArrayList<>();

        for (SubCounty subCounty : countyDao.queryBuilder().where(CountyDao.Properties.Name.eq(countyName)).list().get(0).getSubCounties()) {
            Timber.d("TEST subCounty=");
            subCountiesString.add(subCounty.getName());
        }

        return subCountiesString;
    }

    public void insertOrReplaceIndustries(List<Industry> industries) {
        industryDao = daoSession.getIndustryDao();
        industryDao.insertInTx(industries);

        for (Industry industry : industries) {
            insertOrReplaceCategory(industry.getCategories());
        }
    }

    public List<String> getIndustries() {
        List<String> industries = new ArrayList<>();
        industryDao = daoSession.getIndustryDao();

        Cursor cursor = industryDao.getDatabase().rawQuery("SELECT name FROM industry", null);
        while (cursor.moveToNext()) {
            industries.add(cursor.getString(0));
        }
        return industries;
    }

    private void insertOrReplaceCategory(List<Category> categories) {
        categoryDao = daoSession.getCategoryDao();
        categoryDao.insertInTx(categories);

        for (Category category : categories) {
            insertOrReplaceProfession(category.getProfessions());
        }
    }

    public List<String> getCategories(String industryName) {
        List<String> categories = new ArrayList<>();
        industryDao = daoSession.getIndustryDao();

        for (Category category : industryDao.queryBuilder().where(IndustryDao.Properties.Name.eq(industryName)).list().get(0).getCategories()) {
            categories.add(category.getName());
        }

        return categories;
    }

    private void insertOrReplaceProfession(List<Profession> professions) {
        professionDao = daoSession.getProfessionDao();
        professionDao.insertInTx(professions);
    }

    public List<String> getProfessions(String categoryName) {
        List<String> professions = new ArrayList<>();
        categoryDao = daoSession.getCategoryDao();

        for (Profession profession : categoryDao.queryBuilder().where(CategoryDao.Properties.Name.eq(categoryName)).list().get(0).getProfessions()) {
            professions.add(profession.getName());
        }

        return professions;
    }

    public void insertOrReplaceUser(User user) {
        userDao = daoSession.getUserDao();
        userDao.insertOrReplace(user);
    }

    public User getLoggedUser(Context context){
        return getUser(Preferences.getLoggedUserId(context));
    }

    public User getUser(long id){
        userDao = daoSession.getUserDao();
        List<User> users = userDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).list();
        if (!users.isEmpty())
            return users.get(0);

        return null;
    }

    public void insertLoggedUser(Context context, User user) {
        Preferences.setLoggedUserId(context, user.getId());
        for (Profession profession : user.getProfessions()) {
            insertJoinUserProfessions(user.getId(), profession.getId());
        }
        long tokenId = insertOrReplaceToken(user.getTokenFromWS());
        user.setTokenId(tokenId);
        insertOrReplaceUser(user);
    }

    public long insertOrReplaceToken(Token token){
        tokenDao = daoSession.getTokenDao();
        return tokenDao.insertOrReplace(token);
    }

    public void insertJoinUserProfessions(Long userId, Long professionId) {
        List<JoinUsersWithProfessions> list = daoSession.getJoinUsersWithProfessionsDao()
                .queryBuilder()
                .where(JoinUsersWithProfessionsDao.Properties.UserId.eq(userId))
                .where(JoinUsersWithProfessionsDao.Properties.ProfessionsId.eq(professionId)).list();
        if (list.isEmpty())
            daoSession.getJoinUsersWithProfessionsDao()
                    .insertOrReplace(new JoinUsersWithProfessions(null, userId, professionId));
    }
}
