package com.sstgroup.xabaapp.db;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sstgroup.xabaapp.models.Category;
import com.sstgroup.xabaapp.models.CategoryDao;
import com.sstgroup.xabaapp.models.CommissionLog;
import com.sstgroup.xabaapp.models.CommissionLogDao;
import com.sstgroup.xabaapp.models.Country;
import com.sstgroup.xabaapp.models.CountryDao;
import com.sstgroup.xabaapp.models.County;
import com.sstgroup.xabaapp.models.CountyDao;
import com.sstgroup.xabaapp.models.Currency;
import com.sstgroup.xabaapp.models.CurrencyDao;
import com.sstgroup.xabaapp.models.DaoMaster;
import com.sstgroup.xabaapp.models.DaoSession;
import com.sstgroup.xabaapp.models.Industry;
import com.sstgroup.xabaapp.models.IndustryDao;
import com.sstgroup.xabaapp.models.JoinCategoriesWithProfessions;
import com.sstgroup.xabaapp.models.JoinCategoriesWithProfessionsDao;
import com.sstgroup.xabaapp.models.JoinUsersWithProfessionsAndIndustries;
import com.sstgroup.xabaapp.models.JoinUsersWithProfessionsAndIndustriesDao;
import com.sstgroup.xabaapp.models.JoinUsersWithPrograms;
import com.sstgroup.xabaapp.models.JoinUsersWithProgramsDao;
import com.sstgroup.xabaapp.models.Language;
import com.sstgroup.xabaapp.models.LanguageDao;
import com.sstgroup.xabaapp.models.Notification;
import com.sstgroup.xabaapp.models.NotificationDao;
import com.sstgroup.xabaapp.models.Profession;
import com.sstgroup.xabaapp.models.ProfessionDao;
import com.sstgroup.xabaapp.models.Program;
import com.sstgroup.xabaapp.models.ProgramDao;
import com.sstgroup.xabaapp.models.ReferredWorker;
import com.sstgroup.xabaapp.models.ReferredWorkerDao;
import com.sstgroup.xabaapp.models.SubCounty;
import com.sstgroup.xabaapp.models.SubCountyDao;
import com.sstgroup.xabaapp.models.Token;
import com.sstgroup.xabaapp.models.TokenDao;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.UserCommissions;
import com.sstgroup.xabaapp.models.UserDao;
import com.sstgroup.xabaapp.utils.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    private static CurrencyDao currencyDao;
    private static CountryDao countryDao;
    private static CountyDao countyDao;
    private static SubCountyDao subCountyDao;
    private static IndustryDao industryDao;
    private static ProgramDao programDao;
    private static CategoryDao categoryDao;
    private static ProfessionDao professionDao;
    private static UserDao userDao;
    private static TokenDao tokenDao;
    private static JoinUsersWithProgramsDao joinUsersWithProgramsDao;
    private static JoinCategoriesWithProfessionsDao joinCategoryProfessionDao;
    private static JoinUsersWithProfessionsAndIndustriesDao joinUsersWithProfessionsAndIndustriesDao;
    private static NotificationDao notificationDao;
    private static CommissionLogDao commissionLogDao;
    private static ReferredWorkerDao referredWorkerDao;

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

    public void dropDb() {
//        daoSession.getLanguageDao().deleteAll();
//        daoSession.getCountryDao().deleteAll();
//        daoSession.getCountyDao().deleteAll();
//        daoSession.getSubCountyDao().deleteAll();
//        daoSession.getIndustryDao().deleteAll();
//        daoSession.getCategoryDao().deleteAll();
//        daoSession.getProfessionDao().deleteAll();
        daoSession.getUserDao().deleteAll();
        daoSession.getTokenDao().deleteAll();
        daoSession.getNotificationDao().deleteAll();
        daoSession.getReferredWorkerDao().deleteAll();
        daoSession.getCommissionLogDao().deleteAll();
    }

    public void deleteLocationTables() {
        daoSession.getLanguageDao().deleteAll();
        daoSession.getCurrencyDao().deleteAll();
        daoSession.getCountryDao().deleteAll();
        daoSession.getCountyDao().deleteAll();
        daoSession.getSubCountyDao().deleteAll();
    }

    public void deleteProfessionTables() {
        daoSession.getIndustryDao().deleteAll();
        daoSession.getCategoryDao().deleteAll();
        daoSession.getProfessionDao().deleteAll();
        daoSession.getProgramDao().deleteAll();
    }

    public void insertOrReplaceLanguages(ArrayList<Language> languages) {
        languageDao = daoSession.getLanguageDao();
        languageDao.insertInTx(languages);
    }

    public void insertOrReplaceCurrencies(ArrayList<Currency> currencies) {
        currencyDao = daoSession.getCurrencyDao();
        currencyDao.insertInTx(currencies);
    }

    public Currency getCurrency(Long id) {
        currencyDao = daoSession.getCurrencyDao();

        return currencyDao.loadByRowId(id);
    }

    public List<String> getCurrencies() {
        List<String> currencies = new ArrayList<>();
        currencyDao = daoSession.getCurrencyDao();

        Cursor cursor = currencyDao.getDatabase().rawQuery("SELECT code FROM currency", null);
        while (cursor.moveToNext()) {
            currencies.add(cursor.getString(0));
        }
        return currencies;
    }

    public List<String> getLanguages() {
        List<String> languages = new ArrayList<>();
        languageDao = daoSession.getLanguageDao();

        Cursor cursor = languageDao.getDatabase().rawQuery("SELECT name FROM language ORDER BY name", null);
        while (cursor.moveToNext()) {
            languages.add(cursor.getString(0));
        }
        return languages;
    }

    public void insertOrReplaceCountries(ArrayList<Country> countries) {
        countryDao = daoSession.getCountryDao();
        countryDao.insertInTx(countries);

        for (Country country : countries) {
            insertOrReplaceCounties(country.getCounties(), country.getCountryId());
        }
    }

    public List<String> getCountries() {
        List<String> countries = new ArrayList<>();
        countryDao = daoSession.getCountryDao();

        Cursor cursor = countryDao.getDatabase().rawQuery("SELECT name FROM country ORDER BY name", null);
        while (cursor.moveToNext()) {
            countries.add(cursor.getString(0));
        }
        return countries;
    }

    private void insertOrReplaceCounties(List<County> counties, long countryId) {
        countyDao = daoSession.getCountyDao();
        for (County county : counties) {
            county.setCountryId(countryId);
        }
        countyDao.insertInTx(counties);

        for (County county : counties) {
            insertOrReplaceSubCounties(county.getSubCounties(), county.getCountyId());
        }
    }

    public List<String> getCounties() {

        List<String> counties = new ArrayList<>();
        countyDao = daoSession.getCountyDao();

        Cursor cursor = countyDao.getDatabase().rawQuery("SELECT name FROM county ORDER BY name", null);
        while (cursor.moveToNext()) {
            counties.add(cursor.getString(0));
        }
        return counties;
    }

    private void insertOrReplaceSubCounties(List<SubCounty> subCounties, long countyId) {
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

        Collections.sort(subCountiesString);
        return subCountiesString;
    }

    public void insertOrReplaceIndustries(List<Industry> industries) {
        industryDao = daoSession.getIndustryDao();
        industryDao.insertInTx(industries);

        for (Industry industry : industries) {
            insertOrReplaceCategory(industry.getCategories(), industry.getIndustryId());
        }
    }

    public void insertOrReplacePrograms(List<Program> programs) {
        programDao = daoSession.getProgramDao();
        programDao.insertInTx(programs);
    }

    public List<String> getPrograms() {
        List<String> programs = new ArrayList<>();
        programDao = daoSession.getProgramDao();

        Cursor cursor = programDao.getDatabase().rawQuery("SELECT name FROM program ORDER BY name", null);
        while (cursor.moveToNext()) {
            programs.add(cursor.getString(0));
        }
        return programs;
    }

    public List<String> getActivePrograms() {
        List<String> programs = new ArrayList<>();
        programDao = daoSession.getProgramDao();

        Cursor cursor = programDao.getDatabase().rawQuery("SELECT name FROM program WHERE status LIKE \"" + Program.STATUS_ACTIVE + "\" ORDER BY name", null);
        while (cursor.moveToNext()) {
            programs.add(cursor.getString(0));
        }
        return programs;
    }

    public List<String> getIndustries() {
        List<String> industries = new ArrayList<>();
        industryDao = daoSession.getIndustryDao();

        Cursor cursor = industryDao.getDatabase().rawQuery("SELECT name FROM industry ORDER BY name", null);
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

        List<Industry> list = industryDao.queryBuilder().where(IndustryDao.Properties.Name.eq(industryName)).list();

        if (list != null && list.size() > 0) {
            for (Category category : list.get(0).getCategories()) {
                if (!categories.contains(category.getName())) {
                    categories.add(category.getName());
                }
            }
        }

        Collections.sort(categories);
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

    public List<Profession> getProfessionsObjects(String categoryName, String industryName) {
        categoryDao = daoSession.getCategoryDao();

        List<Industry> industryList = daoSession.getIndustryDao().queryBuilder().where(IndustryDao.Properties.Name.eq(industryName)).list();
        if (industryList != null && industryList.size() > 0) {
            Industry industry = industryList.get(0);

            List<Category> list = categoryDao.queryBuilder().where(CategoryDao.Properties.Name.eq(categoryName)).where(CategoryDao.Properties.IndustryId.eq(industry.getIndustryId())).list();
            if (list != null && list.size() > 0) {
                return list.get(0).getProfessions();
            }
        }

        return null;
    }

    public List<String> getProfessions(String industryName) {
        Set<String> professions = new TreeSet<>();

        categoryDao = daoSession.getCategoryDao();

        List<Industry> industryList = daoSession.getIndustryDao().queryBuilder().where(IndustryDao.Properties.Name.eq(industryName)).list();
        if (industryList != null && industryList.size() > 0) {
            Industry industry = industryList.get(0);

            List<Category> list = categoryDao.queryBuilder().where(CategoryDao.Properties.IndustryId.eq(industry.getIndustryId())).list();

            for (Category category : list) {
                for (Profession profession : category.getProfessions()) {
                    professions.add(profession.getName());
                }

            }
        }

        return new ArrayList<>(professions);
    }

    public Long getProfessionId(String industryName, String professionName) {
        categoryDao = daoSession.getCategoryDao();

        List<Industry> industryList = daoSession.getIndustryDao().queryBuilder().where(IndustryDao.Properties.Name.eq(industryName)).list();
        if (industryList != null && industryList.size() > 0) {
            Industry industry = industryList.get(0);

            List<Category> list = categoryDao.queryBuilder().where(CategoryDao.Properties.IndustryId.eq(industry.getIndustryId())).list();

            for (Category category : list) {
                for (Profession profession : category.getProfessions()) {
                    if (profession.getName().equals(professionName)){
                        return profession.getProfessionId();
                    }

                }

            }
        }

        return 0l;
    }

    public Long getProfessionIdFor(String professionString, String categoryName, String industryName) {
        List<Profession> professions = getProfessionsObjects(categoryName, industryName);
        for (Profession profession : professions) {
            if (profession.getName().equals(professionString)) {
                return profession.getProfessionId();
            }
        }
        return 0L;
    }

    public List<String> getProfessions(String categoryName, String industryName) {
        List<String> professions = new ArrayList<>();
        categoryDao = daoSession.getCategoryDao();

        List<Industry> industryList = daoSession.getIndustryDao().queryBuilder().where(IndustryDao.Properties.Name.eq(industryName)).list();
        if (industryList != null && industryList.size() > 0) {
            Industry industry = industryList.get(0);

            List<Category> list = categoryDao.queryBuilder().where(CategoryDao.Properties.Name.eq(categoryName)).where(CategoryDao.Properties.IndustryId.eq(industry.getIndustryId())).list();
            if (list != null && list.size() > 0) {
                for (Profession profession : list.get(0).getProfessions()) {
                    if (!professions.contains(profession.getName())) {
                        professions.add(profession.getName());
                    }
                }
            }
        }

        Collections.sort(professions);
        return professions;
    }

//    public List<String> getProfessions(String industryName, String categoryName) {
//        List<String> professions = new ArrayList<>();
//        categoryDao = daoSession.getCategoryDao();
//
//
//        long industryId = 0;
//
//        Cursor cursor = daoSession.getIndustryDao().getDatabase().rawQuery("SELECT " + IndustryDao.Properties.IndustryId.columnName + " FROM industry WHERE name = '" + industryName+ "'", null);
//        while (cursor.moveToNext()) {
//            industryId = Long.parseLong(cursor.getString(0));
//        }
//
//        for (Category category : categoryDao.queryBuilder().where(CategoryDao.Properties.Name.eq(categoryName)).list()) {
//            if (category.getIndustryId() == industryId) {
//
//                for (Profession profession : category.getProfessions()) {
//                    professions.add(profession.getName());
//                }
//                break;
//            }
//        }
//
//        Collections.sort(professions);
//        return professions;
//    }

    public void updateLoggedUser(User user, Token token) {
        joinUsersWithProfessionsAndIndustriesDao = daoSession.getJoinUsersWithProfessionsAndIndustriesDao();

        List<JoinUsersWithProfessionsAndIndustries> list = joinUsersWithProfessionsAndIndustriesDao
                .queryBuilder()
                .where(JoinUsersWithProfessionsAndIndustriesDao.Properties.UserId.eq(user.getId())).list();

        for (JoinUsersWithProfessionsAndIndustries item : list) {
            joinUsersWithProfessionsAndIndustriesDao.delete(item);
        }
//        joinUsersProfessionDao.delete(list);

        user.setTokenId(token.getId());
        for (JoinUsersWithProfessionsAndIndustries profession : user.getProfessions()) {
            Long professionId = profession.getProfessionsId();
            Long industryId = profession.getIndustryId();
            insertJoinUserProfessionsWithIndustries(user.getId(), professionId, industryId);
        }

        UserCommissions userCommissions = user.getUserCommissions();

        user.setCurrentBalance(userCommissions.getCurrentBalance());
        user.setPayoutThreshold(userCommissions.getPayoutThreshold());
        user.setTotalReferrals(userCommissions.getTotalReferrals());
        user.setPerWorker(userCommissions.getPerWorker());
        user.setCurrencyId(userCommissions.getCurrencyId());

        insertOrReplaceUser(user);
        daoSession.getUserDao().detachAll();
        daoSession.getJoinUsersWithProfessionsDao().detachAll();
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
        if (!users.isEmpty()) {
            User user = users.get(0);

            Currency currency = getCurrency(user.getCurrencyId());
            user.setCurrency(currency);

            return user;
        }

        return null;
    }

    public void insertLoggedUser(Context context, User user) {
        Preferences.setLoggedUserId(context, user.getId());
        for (JoinUsersWithProfessionsAndIndustries profession : user.getProfessions()) {
            Long professionId = profession.getProfessionsId();
            Long industryId = profession.getIndustryId();
            insertJoinUserProfessionsWithIndustries(user.getId(), professionId, industryId);
        }

        for (Program program : user.getPrograms()) {
            Long programId = program.getLoggedUserProgramId();
            if (programId == null || programId == 0L) {
                programId = program.getProgramId();
            }
            insertJoinUserPrograms(user.getId(), programId);
        }

//        Country country = getCountry(user.getCountryId());
//        County county = getCounty(user.getCountyId());
//        SubCounty subCounty = getSubCounty(user.getSubcountyId());

//        user.setCountry(country);
//        user.setCounty(county);
//        user.setSubCounty(subCounty);

        UserCommissions userCommissions = user.getUserCommissions();

        user.setCurrentBalance(userCommissions.getCurrentBalance());
        user.setPayoutThreshold(userCommissions.getPayoutThreshold());
        user.setTotalReferrals(userCommissions.getTotalReferrals());
        user.setPerWorker(userCommissions.getPerWorker());
        user.setCurrencyId(userCommissions.getCurrencyId());

        long tokenId = insertOrReplaceToken(user.getTokenFromWS());
        user.setTokenId(tokenId);

        insertOrReplaceUser(user);
        daoSession.getUserDao().detachAll();
        daoSession.getJoinUsersWithProfessionsDao().detachAll();
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

    public void insertJoinUserProfessionsWithIndustries(Long userId, Long professionId, Long industryId) {
        joinUsersWithProfessionsAndIndustriesDao = daoSession.getJoinUsersWithProfessionsAndIndustriesDao();
        List<JoinUsersWithProfessionsAndIndustries> list = joinUsersWithProfessionsAndIndustriesDao
                .queryBuilder()
                .where(JoinUsersWithProfessionsAndIndustriesDao.Properties.UserId.eq(userId))
                .where(JoinUsersWithProfessionsAndIndustriesDao.Properties.ProfessionsId.eq(professionId))
                .where(JoinUsersWithProfessionsAndIndustriesDao.Properties.IndustryId.eq(industryId))
                .list();
        if (list.isEmpty())
            joinUsersWithProfessionsAndIndustriesDao
                    .insertOrReplace(new JoinUsersWithProfessionsAndIndustries(null, userId, professionId, industryId));
    }

    public void insertJoinUserPrograms(Long userId, Long programId) {
        joinUsersWithProgramsDao = daoSession.getJoinUsersWithProgramsDao();
        List<JoinUsersWithPrograms> list = joinUsersWithProgramsDao
                .queryBuilder()
                .where(JoinUsersWithProgramsDao.Properties.UserId.eq(userId))
                .where(JoinUsersWithProgramsDao.Properties.ProgramId.eq(programId)).list();
        if (list.isEmpty())
            joinUsersWithProgramsDao
                    .insertOrReplace(new JoinUsersWithPrograms(null, userId, programId));
    }

    public Category getCategory(long categoryId) {
        categoryDao = daoSession.getCategoryDao();
        List<Category> categories = categoryDao.queryBuilder().where(CategoryDao.Properties.CategoryId.eq(categoryId)).list();
        if (!categories.isEmpty())
            return categories.get(0);

        return null;
    }

    public Country getCountry(long countryId) {
        countryDao = daoSession.getCountryDao();
        List<Country> counties = countryDao.queryBuilder().where(CountryDao.Properties.CountryId.eq(countryId)).list();
        if (!counties.isEmpty())
            return counties.get(0);

        return null;
    }

    public County getCounty(long countyId) {
        countyDao = daoSession.getCountyDao();
        List<County> counties = countyDao.queryBuilder().where(CountyDao.Properties.CountyId.eq(countyId)).list();
        if (!counties.isEmpty())
            return counties.get(0);

        return null;
    }

    public SubCounty getSubCounty(long subCountyId) {
        subCountyDao = daoSession.getSubCountyDao();
        List<SubCounty> subCounties = subCountyDao.queryBuilder().where(SubCountyDao.Properties.SubCountyId.eq(subCountyId)).list();
        if (!subCounties.isEmpty())
            return subCounties.get(0);

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

    public List<Long> getIndustryIds(List<String> industryNames) {
        industryDao = daoSession.getIndustryDao();
        List<Industry> industries = industryDao.queryBuilder().where(IndustryDao.Properties.Name.in(industryNames)).list();
        List<Long> industryIdsList = new ArrayList<>();

        for (Industry industry : industries) {
            industryIdsList.add(industry.getIndustryId());
        }

        if (!industryIdsList.isEmpty()) {
            return industryIdsList;
        }
        return null;
    }

    public List<Long> getProfessionIds(List<String> professionIds) {
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

    public List<Long> getProgramIds(List<String> programNames) {
        programDao = daoSession.getProgramDao();
        List<Program> programs = programDao.queryBuilder().where(ProgramDao.Properties.Name.in(programNames)).list();
        List<Long> professionIdsList = new ArrayList<>();

        for (Program program : programs) {
            professionIdsList.add(program.getProgramId());
        }

        if (!professionIdsList.isEmpty()) {
            return professionIdsList;
        }
        return null;
    }

    public County getCounty(String countyName) {
        countyDao = daoSession.getCountyDao();
        List<County> counties = countyDao.queryBuilder().where(CountyDao.Properties.Name.eq(countyName)).list();
        if (!counties.isEmpty()) {
            return counties.get(0);
        }
        return null;
    }

    public SubCounty getSubCounty(String subCountyName) {
        subCountyDao = daoSession.getSubCountyDao();
        List<SubCounty> subCounties = subCountyDao.queryBuilder().where(SubCountyDao.Properties.Name.eq(subCountyName)).list();
        if (!subCounties.isEmpty()) {
            return subCounties.get(0);
        }
        return null;
    }

    public Profession getProfession(String professionName) {
        professionDao = daoSession.getProfessionDao();
        List<Profession> professions = professionDao.queryBuilder().where(ProfessionDao.Properties.Name.eq(professionName)).list();
        if (!professions.isEmpty()) {
            return professions.get(0);
        }
        return null;
    }

    public Industry getIndustry(String industryName) {
        industryDao = daoSession.getIndustryDao();
        List<Industry> industries = industryDao.queryBuilder().where(IndustryDao.Properties.Name.eq(industryName)).list();
        if (!industries.isEmpty()) {
            return industries.get(0);
        }
        return null;
    }

    public Category getCategory(String categoryName) {
        categoryDao = daoSession.getCategoryDao();
        List<Category> subCounties = categoryDao.queryBuilder().where(CategoryDao.Properties.Name.eq(categoryName)).list();
        if (!subCounties.isEmpty()) {
            return subCounties.get(0);
        }
        return null;
    }

    public void insertOrReplaceNotifications(ArrayList<Notification> notifications) {
        notificationDao = daoSession.getNotificationDao();
        notificationDao.insertOrReplaceInTx(notifications);
    }

    public List<Notification> getAllNotifications() {
        notificationDao = daoSession.getNotificationDao();
        return notificationDao.queryBuilder()
                .orderAsc(NotificationDao.Properties.Date).list();
    }

    public List<Notification> getNotificationsByType(String type) {
        notificationDao = daoSession.getNotificationDao();
        return notificationDao.queryBuilder()
                .where(NotificationDao.Properties.Description.eq(type))
                .orderAsc(NotificationDao.Properties.Date).list();
    }

    public List<ReferredWorker> getAllReferredWorkers() {
        referredWorkerDao = daoSession.getReferredWorkerDao();
        return referredWorkerDao.queryBuilder().orderDesc(ReferredWorkerDao.Properties.CreatedAt).list();
    }

    public void insertOrReplaceReferredWorkers(ArrayList<ReferredWorker> referredWorkers) {
        referredWorkerDao = daoSession.getReferredWorkerDao();
        referredWorkerDao.insertOrReplaceInTx(referredWorkers);
    }

    public void updateToken(Token token) {
        tokenDao = daoSession.getTokenDao();
        tokenDao.update(token);
    }

    public void insertOrReplaceCommissionLogs(ArrayList<CommissionLog> commissionLogs) {
        commissionLogDao = daoSession.getCommissionLogDao();
        commissionLogDao.insertOrReplaceInTx(commissionLogs);
    }

    public List<CommissionLog> getAllCommissionLogs() {
        commissionLogDao = daoSession.getCommissionLogDao();
        return commissionLogDao.queryBuilder()
                .orderAsc(CommissionLogDao.Properties.CreatedAt)
                .orderAsc(CommissionLogDao.Properties.CreatedAt).list();
    }

    public List<CommissionLog> getCommissionLogsByType(String type) {
        commissionLogDao = daoSession.getCommissionLogDao();
        return commissionLogDao.queryBuilder()
                .where(CommissionLogDao.Properties.Description.eq(type))
                .orderAsc(CommissionLogDao.Properties.CreatedAt).list();
    }


    public List<CommissionLog> getCommissionLogsByRange(Date from, Date to) {
        commissionLogDao = daoSession.getCommissionLogDao();
        return commissionLogDao.queryBuilder()
                .where(CommissionLogDao.Properties.CreatedAt.ge(from),
                        CommissionLogDao.Properties.CreatedAt.le(to))
                .orderAsc(CommissionLogDao.Properties.CreatedAt).list();
    }

    public List<CommissionLog> getCommissionLogsByRangeAndType(String type, Date from, Date to) {
        commissionLogDao = daoSession.getCommissionLogDao();
        return commissionLogDao.queryBuilder()
                .where(CommissionLogDao.Properties.CreatedAt.ge(from),
                        CommissionLogDao.Properties.CreatedAt.le(to),
                        CommissionLogDao.Properties.Description.eq(type))
                .orderAsc(CommissionLogDao.Properties.CreatedAt).list();
    }


}
