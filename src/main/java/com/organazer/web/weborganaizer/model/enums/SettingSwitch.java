package com.organazer.web.weborganaizer.model.enums;

public enum SettingSwitch {
    EVERYDAY("Каждый день"),
    EVERYWEEK("Каждую неделю");
    private final String setting;
    SettingSwitch(String setting){
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }
}
