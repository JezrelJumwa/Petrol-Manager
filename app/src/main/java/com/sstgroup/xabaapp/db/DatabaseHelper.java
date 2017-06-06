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
import com.sstgroup.xabaapp.models.JoinCategoriesWithProfessions;
import com.sstgroup.xabaapp.models.JoinCategoriesWithProfessionsDao;
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
import com.sstgroup.xabaapp.utils.Preferences;

import java.util.ArrayList;
import java.util.List;

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
    private static JoinUsersWithProfessionsDao joinUsersProfessionDao;
    private static JoinCategoriesWithProfessionsDao joinCategoryProfessionDao;

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
            insertOrReplaceSubCounties(county.getSubCounties(), county.getId());
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

    public void insertOrReplaceSubCounties(List<SubCounty> subCounties, long countyId) {
        subCountyDao = daoSession.getSubCountyDao();
        for (SubCounty subCounty : subCounties) {
            subCounty.setCountyId(countyId);
        }
        subCountyDao.insertInTx(subCounties);
    }

    public List<String> getSubCounties(String countyName) {
        List<String> subCountiesString = new ArrayList<>();
        countyDao = daoSession.getCountyDao();

        for (SubCounty subCounty : countyDao.queryBuilder().where(CountyDao.Properties.Name.eq(countyName)).list().get(0).getSubCounties()) {
            subCountiesString.add(subCounty.getName());
        }
        return subCountiesString;
    }

    public void insertOrReplaceIndustries(List<Industry> industries) {
        industryDao = daoSession.getIndustryDao();
        industryDao.insertInTx(industries);

        for (Industry industry : industries) {
            insertOrReplaceCategory(industry.getCategories(), industry.getIndustryId());
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

    private void insertOrReplaceCategory(List<Category> categories, long industryId) {
        categoryDao = daoSession.getCategoryDao();
        for (Category category : categories) {
            category.setIndustryId(industryId);
        }
        categoryDao.insertInTx(categories);

        for (Category category : categories) {
            insertOrReplaceProfession(category.getProfessions(), category.getCategoryId());
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

    private void insertOrReplaceProfession(List<Profession> professions, long categoryId) {
        professionDao = daoSession.getProfessionDao();
        for (Profession profession : professions) {
            professionDao.insertOrReplace(profession);
            insertJoinCategoryProfessions(categoryId, profession.getProfessionId());
        }
    }

    public Profession getProfession(long id) {
        professionDao = daoSession.getProfessionDao();
        List<Profession> profesions = professionDao.queryBuilder().where(ProfessionDao.Properties.ProfessionId.eq(id)).list();
        if (!profesions.isEmpty())
            return profesions.get(0);

        return null;
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

    public User getLoggedUser(Context context) {
        return getUser(Preferences.getLoggedUserId(context));
    }

    public User getUser(long id) {
        userDao = daoSession.getUserDao();
        List<User> users = userDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).list();
        if (!users.isEmpty())
            return users.get(0);

        return null;
    }

    public void insertLoggedUser(Context context, User user) {
        Preferences.setLoggedUserId(context, user.getId());
        for (Profession profession : user.getProfessions()) {
            insertJoinUserProfessions(user.getId(), profession.getLoggedUserProfessionId());
        }
        long tokenId = insertOrReplaceToken(user.getTokenFromWS());
        user.setTokenId(tokenId);
        insertOrReplaceUser(user);
    }

    public long insertOrReplaceToken(Token token) {
        tokenDao = daoSession.getTokenDao();
        return tokenDao.insertOrReplace(token);
    }

    public void insertJoinCategoryProfessions(Long categoryId, Long professionId) {
        joinCategoryProfessionDao = daoSession.getJoinCategoriesWithProfessionsDao();
        List<JoinCategoriesWithProfessions> list = joinCategoryProfessionDao
                .queryBuilder()
                .where(JoinCategoriesWithProfessionsDao.Properties.CategoryId.eq(categoryId))
                .where(JoinCategoriesWithProfessionsDao.Properties.ProfessionsId.eq(professionId)).list();

        if (list.isEmpty())
            joinCategoryProfessionDao
                    .insertOrReplace(new JoinCategoriesWithProfessions(null, categoryId, professionId));
    }

    public void insertJoinUserProfessions(Long userId, Long professionId) {
        joinUsersProfessionDao = daoSession.getJoinUsersWithProfessionsDao();
        List<JoinUsersWithProfessions> list = joinUsersProfessionDao
                .queryBuilder()
                .where(JoinUsersWithProfessionsDao.Properties.UserId.eq(userId))
                .where(JoinUsersWithProfessionsDao.Properties.ProfessionsId.eq(professionId)).list();
        if (list.isEmpty())
            joinUsersProfessionDao
                    .insertOrReplace(new JoinUsersWithProfessions(null, userId, professionId));
    }

    public Category getCategory(long categoryId) {
        categoryDao = daoSession.getCategoryDao();
        List<Category> categories = categoryDao.queryBuilder().where(CategoryDao.Properties.CategoryId.eq(categoryId)).list();
        if (!categories.isEmpty())
            return categories.get(0);

        return null;
    }

    public Industry getIndustry(long industryId) {
        industryDao = daoSession.getIndustryDao();
        List<Industry> industries = industryDao.queryBuilder().where(IndustryDao.Properties.IndustryId.eq(industryId)).list();
        if (!industries.isEmpty())
            return industries.get(0);

        return null;
    }

    public Country getCountry(String countryName) {
        countryDao = daoSession.getCountryDao();
        List<Country> countries = countryDao.queryBuilder().where(CountryDao.Properties.Name.eq(countryName)).list();
        if (!countries.isEmpty()) {
            return countries.get(0);
        }

        return null;
    }

    public Language getLanguage(String languageName) {
        languageDao = daoSession.getLanguageDao();
        List<Language> languages = languageDao.queryBuilder().where(LanguageDao.Properties.Name.eq(languageName)).list();
        if (!languages.isEmpty()) {
            return languages.get(0);
        }
        return null;
    }

    public List<Long> getProfessionIds(ArrayList<String> professionIds) {
        professionDao = daoSession.getProfessionDao();
        List<Profession> professions = professionDao.queryBuilder().where(ProfessionDao.Properties.Name.in(professionIds)).list();
        List<Long> professionIdsList = new ArrayList<>();

        for (Profession profession : professions) {
            professionIdsList.add(profession.getProfessionId());
        }

        if (!professionIdsList.isEmpty()) {
            return professionIdsList;
        }
        return null;
    }
}