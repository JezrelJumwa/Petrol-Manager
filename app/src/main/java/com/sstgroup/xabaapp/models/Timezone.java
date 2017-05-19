package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

public class Timezone {

    @SerializedName("timezone_id")
    private Long timezoneId;
    @SerializedName("php_code")
    private String phpCode;
    @SerializedName("zone")
    private String zone;
    @SerializedName("territory")
    private String territory;
    @SerializedName("utc_offset")
    private String utcOffset;
    @SerializedName("display_name")
    private String displayName;

    public Long getTimezoneId() {
        return timezoneId;
    }

    public String getPhpCode() {
        return phpCode;
    }

    public String getZone() {
        return zone;
    }

    public String getTerritory() {
        return territory;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public String getDisplayName() {
        return displayName;
    }
}
